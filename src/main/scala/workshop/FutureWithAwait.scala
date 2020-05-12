package workshop

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object FutureWithAwait extends  App {
  val future1 = Future {
    Thread.sleep(5000)
    // throw new Exception("BOOM")
    100
  }

  println("completed ", future1.isCompleted)
  // block the main/caller thread
  // wait up to 6 seconds for the result
  val result = Await.result(future1, 6 second)
  println("result ", result)
}
