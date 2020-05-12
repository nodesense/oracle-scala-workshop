package workshop

import scala.util.{Try, Success, Failure}

object TrySuccessExample extends  App {
  // Try execute with exception handling inbuilt
  // if result: get the result
  // if exception: get the exception
  // Try either return Success object or Failure object
  def safeDiv(a: Int, b: Int) = Try (a/b)

  val x = safeDiv(10 ,2)

  if (x.isSuccess)
      println("The result is " + x.get)

  val y = safeDiv(10, 0)
  println("IsSuccess ", y.isSuccess)
  println("isFailure ", y.isFailure)

  if (y.isFailure) {
    // accessing .get when the result is failed shall re-throw exception
    // caller function to decide
    // otherwise use failed, which will not throw exception
    println("Exception is " + y.failed);
    try {
      println("Exception " + y.get) //
    }catch  {
      case t:Throwable => println("The exception is " + t)
    }
  }
}
