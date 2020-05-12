package workshop

// Options => Some or None [Only one possible output]
// Either => Left and Right [Two possible output]
object EitherExample  extends  App {
  // Either [Left, Right]
  // Left means something wrong, error reason or exception stack trace
  // Right means well, right value
  // Either[WrongResult/Exception/Error Code, Right Value]
  def safeDiv(a: Int, b: Int): Either[String, Int] = {
    if (b != 0 ) Right(a / b) else Left("Divide by Zero Error")
  }

  def safeDivWithThrowable(a: Int, b: Int): Either[Throwable, Int] = {
    try {
      Right(a / b)
    } catch {
      case t: Throwable => Left(t)
    }
  }

  val x = safeDiv(10, 2)
  println("X ", x);
  println(" isRight ", x.isRight) // true
  println(" isLeft ", x.isLeft) // false
  if (x.isRight) {
    println("Result is " + x.right);
  }

  if (x.isLeft) {
    println(" error is " + x.left)
  }

  val y = safeDivWithThrowable(10, 0)
  println("Y ", y);
  println("y isRight ", y.isRight) // false
  println("y isLeft ", y.isLeft) // true

  if (y.isLeft) {
    println(y.left)
  }

}
