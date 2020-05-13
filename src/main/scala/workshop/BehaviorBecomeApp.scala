package workshop

// Systems
// Akka - one program
// DB - another process

// Akka is booting up
// Someone send message
// should be stored to db [expected] [not connected] -- what happen to this message
// DB is bootup now
// connected

// when the receive method receive the message, it means,
// the message is removed from message box
// stashing - temporaliry we cannot process the message
//          - keep in a buffer, so that we don't loose the message
// once db connected,/ready to process messages,
//   unstash -- move to queue/message mailbox

import akka.actor.{ Actor, ActorRef, ActorSystem, Props, Stash }
import UserStorage._

case class User(username: String, email: String)
object UserStorage {

  trait DBOperation

  object DBOperation {
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(dBOperation: DBOperation, user: Option[User])

}


class UserStorageActor extends Actor with Stash {

  // behaviour initialize with disconnected
  def receive = disconnected

  // connected behaviour during connected stage
  def connected: Actor.Receive = {
    case Disconnect =>
      println("User Storage Disconnect from DB")
      // roll back to previous behaviour/ disconnnected
      context.unbecome()
    case Operation(op, user) =>
      println(s"User Storage receive ${op} to do in user: ${user}")

  }

  // another behaviour, db is not connected/not ready
  def disconnected: Actor.Receive = {
    case Connect =>
      println(s"User Storage connected to DB")
      // bring back the buffered message came when disconnected mode
      // to the mail box
      println("Bring back messages from buffer to mail box")
      unstashAll()
      context.become(connected)
    case _ =>
      println("Stashing the msg to buffer");
      // if anything other than Connect message comes,
      // put in to temporary queue, so that we will nto loose it
      stash()
  }
}




object BehaviorBecomeApp extends  App {
  import UserStorage._

  val system = ActorSystem("Hotswap-Become")

  val userStorageActor = system.actorOf(Props[UserStorageActor], "userStorage")


  userStorageActor ! Operation(DBOperation.Create, Some(User("Admin", "admin@packt.com")))
  userStorageActor ! Connect

  userStorageActor ! Disconnect

  Thread.sleep(100)

  system.terminate()
}
