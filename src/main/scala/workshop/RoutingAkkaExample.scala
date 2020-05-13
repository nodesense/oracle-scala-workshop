package workshop

import akka.actor.{Actor, ActorSystem, Props, Terminated}
import akka.routing.{ActorRefRoutee, RoundRobinRoutingLogic, Router}

object RoutingAkkaExample extends  App {

  //  router --> more email dispatcher
  // load balancer --> actual actor that perform action

  case class Email(to: String,
                   subject: String,
                   body: String) {

  }

  case class StopEmailer();


  // Emailer/SMTP/AWS etc

  class EmailerActor extends Actor  {
    println("EmailerActor created")
    def receive = {

      case Email(to, subject, body) => {
        println("EmailerActor Actor Path",
          akka.serialization.Serialization.serializedActorPath(self));

        println(s"To deliver $to, $subject $body")
      }

      case s: StopEmailer =>  {
        println("Stoping emailer")
        context.stop(self)
      } // // self stop the actor
      //sender() ! EmailReportResponse(true)

      case _ => println("Unknown message")
    }

  }

  // Router
  class EmailRouterActor extends Actor {
    println("EmailRouterActor created");

    var router = {
      // create 5 instance of EmailerActor, load can be shared
      val routees = Vector.fill(5) {
        val actor = context.actorOf(Props[EmailerActor])
        // if the child actor is terminated
        // we need to create another instance
        context watch actor
        ActorRefRoutee(actor)
      }
      Router(RoundRobinRoutingLogic(), routees)
    }

    def receive = {
      case email: Email =>
        // forward to one of the child actor to process the email
        router.route(email, sender())

      case stopMsg: StopEmailer => {
        println("Stop emailer actor");
        router.route(stopMsg, sender())
      }


      case Terminated(a) =>
        println("One email actor terminated ");
        router = router.removeRoutee(a)
        // create new instance for EmailerActor since 1 isntance is terminated
        val actorRef = context.actorOf(Props[EmailerActor])
        // watch will ensure that when the actor terminated, it will fire Terminated message
        context watch actorRef
        router = router.addRoutee(actorRef)
    }
  }

  val system = ActorSystem("helloworld")
  val emailRouter = system.actorOf(Props[EmailRouterActor], name= "emailRouter")


  // router shall forward to child actors
  emailRouter ! Email("someone@example.com", "Msg 1", "Msg 1")
  emailRouter ! Email("someone@example.com", "Msg 2", "Msg 2")
  emailRouter ! Email("someone@example.com", "Msg 3", "Msg 3")
  emailRouter ! Email("someone@example.com", "Msg 4", "Msg 4")
  emailRouter ! Email("someone@example.com", "Msg 5", "Msg 5")
  emailRouter ! Email("someone@example.com", "Msg 6", "Msg 6")

  emailRouter ! StopEmailer()

}
