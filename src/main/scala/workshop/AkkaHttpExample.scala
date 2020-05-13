package workshop

// Akka-Http
// Build on Akka framework, library, very low level library
// naively integrate with akka.

// Web Services, productivity ==> Akka/Play Framework /high level web mvc framework
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.delete
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpRequest, HttpResponse, Uri}
import akka.http.scaladsl.model.HttpMethods.{DELETE, GET, OPTIONS, POST, PUT}

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import akka.pattern.ask
import akka.util.Timeout
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport


case class Product(id: Int,
                   name: String );


final case class Products(products: Seq[Product])

// GET /products
final case object GetProducts;

// GET /produts/123
final case class GetProduct(id: Int)

// POST /products
// {id: 1m "name": "Galaxy"}
final case class CreateProduct(product: Product)
// PUT /products/123
// {id: 123, "name": "Galaxy"}
final case class UpdateProduct(product: Product)
// dELETE /products/123
final case class DeleteProduct(id: Int)

final case class ProductActionPerformed(description: String)
final case class ActionFailed(statusCode: Int, message: String)


trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import DefaultJsonProtocol._

  implicit val productJsonFormat = jsonFormat2(Product)
  implicit val productsJsonFormat = jsonFormat1(Products)

  implicit  val productActionPerformed = jsonFormat1(ProductActionPerformed)
}



trait Routes extends JsonSupport {
  //#user-routes-class

  // we leave these abstract, since they will be provided by the App
  implicit def system: ActorSystem

  // lazy val log = Logging(system, classOf[Routes])

  // other dependencies that ProductRoutes use
  def productActor: ActorRef

  // Required by the `ask` (?) method below
  implicit lazy val timeout = Timeout(5.seconds) // usually we'd obtain the timeout from the system's configuration

  val homeRoute =
    path("") {
      // get
      // GET /
      get {
        // response to server
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Welcome to Akka </h1>"))
      }
    }

  val route =
    path("hello") {
      // get
      // GET /hello
      get {
        // response to server
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
      }
    }

  // both are starting with /products, but method is different
  // POST /products
  // GET  /products


  lazy val productRoutes: Route =
    pathPrefix("products") {
      concat(
        //#products-get-delete
        pathEnd {
          concat(
            // GET /products
            get {
              // the get list of products from productActor
              // ask ?, ask the productActor to give data
              // mapTo is serization/cast
              val products: Future[Products] =
                (productActor ? GetProducts).mapTo[Products]
              // respond to client
              complete(products)
            },
            // POST /products
            // {"id": 1, "name" : "Galaxy"}
            post {
              // convert the JSON from client to Java POJA class Product
              entity(as[Product]) { product =>
                // ask the productActor to create a product either in db/memory
                val productCreated: Future[ProductActionPerformed] =
                  (productActor ? CreateProduct(product)).mapTo[ProductActionPerformed]
                // FIXME: OnFailure
                onSuccess(productCreated) { performed =>
                  println(s" $product.name ")
                  //log.info("Created product [{}]: {}", product.name, performed.description)
                  // FIXME
                  // send 201/ CREATED to client
                  complete((StatusCodes.Created, performed))
                }
              }
            }
          )
        },
        //#products-get-post
        //#products-get-delete

        //GET /products/1
        // PUT /products/1
        // DELETE /products/1

        // Segment is products
        // products/id

        path(Segment) { id =>
          concat(
            get {
              //#retrieve-product-info
              // ask productActor to get the product by ID
              val maybeProduct: Future[Option[Product]] =
                (productActor ? GetProduct(id.toInt)).mapTo[Option[Product]]
              rejectEmptyResponse {
                complete(maybeProduct)
              }
              //#retrieve-product-info
            },

            put {
              entity(as[Product]) { product =>
                val productUpdated: Future[ProductActionPerformed] =
                  (productActor ? UpdateProduct(product)).mapTo[ProductActionPerformed]
                onSuccess(productUpdated) { performed =>
                  println(s"Update product $product.name " +  performed.description)
                  // FIXME
                  // 200 OK
                  complete((StatusCodes.OK, performed))
                }
              }
            },

            delete {
              //#products-delete-logic
              val productDeleted: Future[ProductActionPerformed] =
                (productActor ? DeleteProduct(id.toInt)).mapTo[ProductActionPerformed]
              onSuccess(productDeleted) { performed =>
                println("Deleted product [{}]: {}" + id)
                complete((StatusCodes.OK, performed))
              }
              //#products-delete-logic
            }
          )
        }
      )
      //#products-get-delete
    }
  //#all-routes
}

// responsible for working with/DAL
class ProductActor extends Actor with ActorLogging {
  // Hardcoded product ids, instead of using database
  var products = Set.empty[Product]
  products += Product(1, "Galaxy 1")
  products += Product(2, "Moto G")
  products += Product(3, "iPhone")

  def receive: Receive = {
    case GetProducts => {
      //sender() ! Products(products.toSeq)
      val senderRef = sender();
      println("Querying products")
      senderRef ! Products(products.toSeq)
    }
    case CreateProduct(product) => {
      val senderRef = sender();
      println("creating  products")
      products += product
      sender() ! ProductActionPerformed(s"Product ${product.name} created.")

    }


    case UpdateProduct(product) => {
      val senderRef = sender();
      println("creating  products")
      products += product
      sender() ! ProductActionPerformed(s"Product ${product.name} created.")
    }

    case GetProduct(id) => {
      sender() ! products.find(_.id == id)
    }

    case DeleteProduct(id) => {
      products.find(_.id == id) foreach { product => products -= product }
      sender() ! ProductActionPerformed(s"Product ${id} deleted.")
    }
  }
}

object ProductActor {

  def props: Props = Props[ProductActor]
}

object AkkaHttpExample  extends  App with Routes {

  implicit val system: ActorSystem = ActorSystem("actors")

  // Materialize is basically connector for flow
  // akka-http is a akka stream, has many flows
  // receive, data, headers, send response,
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val productActor: ActorRef = system.actorOf(ProductActor.props, "productActor")

  // logs/filename
  // http://localhost:8080/logs/test
  // http://localhost:8080/logs/hello

  // ensure that home directory has hello.log and test.log

  val fileRoutes = path("logs" / Segment) { name =>
    println("Log path is", name)
    getFromFile(s"$name.log") // uses implicit ContentTypeResolver
  }

  // http://localhost:8080/data/hello.log
  // http://localhost:8080/data/test.log
  // http://localhost:8080/data/build.sbt

  var dirRoutes = pathPrefix("data") {
    println("Serving directory content");
    getFromDirectory("/Users/krish/workshops/oracle-scala-workshop")
  }

  // ~ concat a special operator by akka/http
  Http()
    .bindAndHandle(homeRoute ~ route ~ productRoutes ~fileRoutes ~dirRoutes, "localhost", 8080)

  println(s"Server online at http://localhost:8080/")
  Await.result(system.whenTerminated, Duration.Inf)

}
