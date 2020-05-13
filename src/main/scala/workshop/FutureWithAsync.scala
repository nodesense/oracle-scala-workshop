package workshop

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps
import scala.util.{Failure, Success}

object FutureWithAsync extends  App {
  val future1 = Future {
    Thread.sleep(5000)
    // throw new Exception("BOOM")
    100
  }

  println("iscompleted ", future1.isCompleted)
  // unblock the main/caller thread

  future1.onComplete {
    case Success(value) => println("Got the result " + value)
    case Failure(exception) => println("Got error ", exception)
  }


  Thread.sleep(10000);
  println("program exit")
}
