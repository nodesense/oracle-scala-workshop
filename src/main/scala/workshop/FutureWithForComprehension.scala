package workshop

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps

object FutureWithForComprehension extends  App {
  case class User(id: Int);

  def getUser(id: Int) = Future {
      println("fetch user " + id + " from db")
      User(id)
  }

  def getBalance(user: User) = Future {
    println("fetch balance   from db")
    1000
  }

  def getOffer(user: User) = Future {
    println("fetch getOffer   from db")
    1.2
  }

  val future1 = for {
          user <- getUser(1)
          balance <- getBalance(user)
          offer <- getOffer(user)
  } yield balance * offer;

  val result = Await.result(future1, 2 seconds)
  println("result ", result)

}
