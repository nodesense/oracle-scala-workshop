package workshop

object PartialFunctionExample extends  App {
  // function....that takes args, compute/boundary check
  // return result/exception/Option/Either
  // to know the outcome, we need to invoke the function
  // div(10, 0) => 10 / 0 throw exception

  // partial functions are incomplete function with respect to args passed
  // constrainted/guarded functions, may not execute all the arguments passed

  val divide = new PartialFunction[Int, Int] {
    def apply(x: Int) = 10 / x

    // guard function/constraint/pre-condition
    override  def isDefinedAt(x: Int): Boolean = x != 0
  }

  println("Is defined ", divide.isDefinedAt(0))
  println("Is defined ", divide.isDefinedAt(2))

  // check the constraint before calling the method/func
  if (divide.isDefinedAt(2)) {
    println("10/2 = " + divide(2))
  }


}
