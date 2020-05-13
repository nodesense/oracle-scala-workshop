package workshop

import akka.actor.{Actor, ActorRef, ActorSystem, PoisonPill, Props}
import akka.dispatch._
import com.typesafe.config.{Config, ConfigFactory}
import java.util.concurrent.ConcurrentLinkedQueue

import scala.Option

trait MyUnboundedMessageQueueSemantics




object MyUnboundedMailbox {

  // This is the MessageQueue implementation
  class MyMessageQueue extends MessageQueue
    with MyUnboundedMessageQueueSemantics {

    private final val queue = new ConcurrentLinkedQueue[Envelope]()

    // these should be implemented; queue used as example
    def enqueue(receiver: ActorRef, handle: Envelope): Unit = {
      // insert statement
      queue.offer(handle)
    }


    def dequeue(): Envelope = queue.poll()
    def numberOfMessages: Int = queue.size
    def hasMessages: Boolean = !queue.isEmpty
    def cleanUp(owner: ActorRef, deadLetters: MessageQueue) {
      while (hasMessages) {
        deadLetters.enqueue(owner, dequeue())
      }
    }
  }
}

// This is the Mailbox implementation
class MyUnboundedMailbox extends MailboxType
  with ProducesMessageQueue[MyUnboundedMailbox.MyMessageQueue] {

  println("Custom mail box created");
  import MyUnboundedMailbox._

  // This constructor signature must exist, it will be called by Akka
  def this(settings: ActorSystem.Settings, config: Config) = {
    // put your initialization code here
    this()
  }

  // The create method is called to create the MessageQueue
  final override def create(
                             owner:  Option[ActorRef],
                             system: Option[ActorSystem]): MessageQueue =
    new MyMessageQueue()
}



object CustomUnbounedMailBox  extends  App {

  class MailboxActor extends Actor with  RequiresMessageQueue[MyUnboundedMessageQueueSemantics] {

    def receive = {
      case msg =>
        println(s" Received   ${msg}")
    }
  }


  val config = ConfigFactory.parseString(
    """
      |custom-dispatcher {
      |  mailbox-requirement =
      |  "workshop.MyUnboundedMessageQueueSemantics"
      |}
      |
      |akka.actor.mailbox.requirements {
      |  "workshop.MyUnboundedMessageQueueSemantics" =
      |  custom-dispatcher-mailbox
      |}
      |
      |custom-dispatcher-mailbox {
      |  mailbox-type = "workshop.MyUnboundedMailbox"
      |}
    """.stripMargin)

  // without priority
  val system = ActorSystem("pririty-system", config)

  // no priority
  // every actor has its own mail box
  // we have option to override the default option for each actor if we want
  val mailboxActor = system.actorOf(Props[MailboxActor])

  mailboxActor ! "First"
}
