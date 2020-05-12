package workshop

object PartialFunction02 extends  App {
  // other easy way of generating partial function

  // Block statement, with case
  // case statement shall help to validate constraints
  // isDefinedAt is written by Scala compiler automatically
  // based on case match
  val divide:PartialFunction[Int, Int] = {
    case x: Int if x != 0 => 10 / x
  }

  println("Is defined ", divide.isDefinedAt(0))
  println("Is defined ", divide.isDefinedAt(2))

  // check the constraint before calling the method/func
  if (divide.isDefinedAt(2)) {
    println("10/2 = " + divide(2))
  }

}
