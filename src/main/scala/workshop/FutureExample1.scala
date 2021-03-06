package workshop

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

// implicits are package level
// scala.concurrent uses execution context

object FutureExample1 extends  App {
  // this feature is queued into execution context
  // executed by execution context by JVM, it has own thread pool

   val future1 = Future { // block expression
      // task code
     // query from db, load csv, process data
     println("Future 1 excuting...")
     // EXAMPLE CODE, DON:T USE FOR PRODUCTION
     Thread.sleep(5000)
    println("Future Thread id " + java.lang.Thread.currentThread().getId())
     100 // return that we return after long computation
   }

  println("App Thread id " + java.lang.Thread.currentThread().getId())

  println("isCompleted ", future1.isCompleted);

  //BAD, we are blocking main thread/caller thread
  // Should not be used in production
  while (!future1.isCompleted) {
    println("waiting ...");
    Thread.sleep(1000);
  }

  println("isCompleted ", future1.isCompleted);
  val valueOption = future1.value;
  println("Result of the future is " + valueOption);
  val tryValue = valueOption.get;
  println("Result of the tryValue is " + tryValue);

  println("isFailure", tryValue.isFailure)
  println("isSuccess", tryValue.isSuccess)

  val intValue = tryValue.get;
  println("Result of the final value is " + intValue);

}
