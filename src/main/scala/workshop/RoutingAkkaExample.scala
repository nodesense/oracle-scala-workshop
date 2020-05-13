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

  // Emailer/SMTP/AWS etc

  class EmailerActor extends Actor  {
    println("EmailerActor created")
    def receive = {

      case Email(to, subject, body) => {
        println("EmailerActor Actor Path",
          akka.serialization.Serialization.serializedActorPath(self));

        println(s"To deliver $to, $subject $body")
      }
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

      case Terminated(a) =>
        router = router.removeRoutee(a)
        val r = context.actorOf(Props[EmailerActor])
        context watch r
        router = router.addRoutee(r)
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


}
