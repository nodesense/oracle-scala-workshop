package workshop
import scala.util.{Try, Success, Failure}

object TrySuccessWithMatch extends  App {
  def safeDiv(a: Int, b: Int) = Try (a/b)

  safeDiv(10, 2) match {
    case Success(value) =>  {
      println("result is " + value)
    }
    case Failure(t) => {
      println("Exception is " + t)
    }
  }


}
