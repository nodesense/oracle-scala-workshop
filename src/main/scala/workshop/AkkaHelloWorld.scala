package workshop

import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.{Await, Future}


object AkkaHelloWorld extends  App {

  class HelloActor extends  Actor {
     println("HelloActor Created")
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
       case _ => println("Unknown message")
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

  implicit val timeout = Timeout(5 seconds)
  // Ask for an answer/ack/ using ask
  // while asking, there is timeout, as implicit
  // async
  val future1 = ask(hello1Actor, "what is your name?")
  println(future1)

  val result = Await.result(future1, 2 second);
  println("result ", result)

  // ? is nothing but ask method
  val future2 = hello1Actor ? "what is your name?"
  val result2 = Await.result(future1, 2 second);
  println("result2 ", result2)
}
