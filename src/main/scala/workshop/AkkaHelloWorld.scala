package workshop

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object AkkaHelloWorld extends  App {

  // child of HelloActor
  class ChildActor extends  Actor {
    println("ChildActor created");

    println("Child Actor Path",
      akka.serialization.Serialization.serializedActorPath(self));

    // life cycle method
    override def preStart(): Unit = {
      // initialization/db/resources/memory
      // once once per actor instance
      println("ChildActor :: preStart")
    }

    override def postStop(){    // Overriding postStop method
      // uninitialize db/resources/memory
      println("ChildActor:postStop method is called");
    }

    // called when actor is crashed, and restarted the exception
    // arithmatic exception at present
    override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
      println("ChildActor:preRestart method is called " + reason);

      reason match {
        case a: ArithmeticException => println("math error, reinitializing")
        case _ => println("error")
      }
    }

    override def postRestart(reason: Throwable): Unit = {
      super.postRestart(reason)
      println("ChildActor:postRestart")
    }


    def receive = {
      case "devideByZero" => 10 / 0
      case msg: String => println("ChildActor Msg is ", msg)
    }
  }

  class HelloActor extends  Actor {
     println("HelloActor Created")

    println("Actor Path",
      akka.serialization.Serialization.serializedActorPath(self));

    val childActorRef = context.actorOf(Props[ChildActor], "child1")

     // behaviour
    // method is invoked automatically by dispatcher
    // whenever a message arrives in message box
    // a random thread shall be pull and assigned to run receive method
     def receive = {
       case "hello" => println("received hello")
         // actor need to respond to query
         // fetch data from db, store to db, pull from other service
         // sender is member variable, represent who send the message
       case "what is your name?" => sender.tell("Akka Actor", self)
       case msg => {
         println("Unknown message, telling to child1")
         // telling, the message lost the original source it came from
         // not a good practice to tell to child, instead use forward
         // A ----------->       B   -------------> C
         //  ASK                      tell
         //  shall not receive                reply
         // childActorRef ! msg

         // maintain original sender, context
         // good for ask pattern
         // A ----------->   B   ----------> C
         //      ask                  forwarding
         // A <----------------------------C [Answer]
         childActorRef.forward(msg)

       }
     }
  }

  // There must only one actor system per JVM process
  val system = ActorSystem("helloworld")

  // adding a new child actor under helloworld
  // an actor creates its own child actor, become tree strucutre
  val hello1Actor = system.actorOf(Props[HelloActor], name= "hello1")

  // now telling actor a message, means fire/forgot mode, no response intended
  // sender is null
  // the message hello shall be queued into hello1Actor inbox
  hello1Actor.tell("hello", null)
  // tell using operator ! (tell), overloaded for tell method
  hello1Actor.tell("hello", null)
  hello1Actor ! "hello"
  hello1Actor ! "welcome" // default in onReceive

   println("Blow up with exception")
   hello1Actor ! "devideByZero" // default in onReceive



  implicit val timeout = Timeout(5 seconds)
  // Ask for an answer/ack/ using ask
  // while asking, there is timeout, as implicit
  // async
  val future1 = ask(hello1Actor, "what is your name?")
  println(future1)

  // don't use await, instead use onComplete callbacks
  val result = Await.result(future1, 2 second);
  println("result ", result)

  // ? is nothing but ask method
  val future2 = hello1Actor ? "what is your name?"
  //val result2 = Await.result(future1, 2 second);
  //println("result2 ", result2)

  future2.onComplete {
    case Success(value) => println("Got the result " + value)
    case Failure(exception) => println("Got error ", exception)
  }
}
