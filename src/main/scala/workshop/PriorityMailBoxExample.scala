package workshop

import akka.actor.{Actor, ActorSystem, PoisonPill, Props}
import akka.dispatch.PriorityGenerator
import akka.dispatch.UnboundedPriorityMailbox
import com.typesafe.config.{Config, ConfigFactory}

// high priority should be delivered first
// lower number means - high priority
case class Message(msg: String, priority: Int)


// custom mailbox, for prioritize the messaegs
class MyPrioMailbox(settings: ActorSystem.Settings, config: Config)
  extends UnboundedPriorityMailbox(
    PriorityGenerator {
      case Message(_, priority) if priority < 5   => 0 // High

      case Message(_, priority) if priority < 10   => 1 //  Medium

      case Message(_, priority) if priority >= 10   => 2 //  Low

      // to terminate an actor
      case PoisonPill => 3 // lowest priorty

      case _ => 2

    }
  ) {
  println("Priority Inbox created");
}


// How to implement custom mail box, that can apply priority
object PriorityMailBoxExample extends  App {


  // we shall be attaching priority mailbox through config
  class MailboxActor extends Actor {

    def receive = {

      case msg =>
        println(s" Received   ${msg}")
    }
  }

  // application.conf
  val config = ConfigFactory.parseString(
    """
      |prio-dispatcher {
      |  mailbox-type = "workshop.MyPrioMailbox"
      |}
    """.stripMargin)

  // without priority
  val system = ActorSystem("pririty-system", config)

  // no priority
  // every actor has its own mail box
  // we have option to override the default option for each actor if we want
  val mailboxActor = system.actorOf(Props[MailboxActor]
                                    .withDispatcher("prio-dispatcher"))

  mailboxActor ! Message("MSG 20", 20) // low
  mailboxActor ! Message("MSG 20", 8) // medium
  mailboxActor ! PoisonPill // lowest priority, allow other messages to come
  mailboxActor ! Message("MSG 1", 1) // high
  mailboxActor ! Message("MSG 2", 2) // high


}
