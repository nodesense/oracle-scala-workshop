package workshop

object Methods extends  App {
  // Two ways to defined functions

  // 1. Method, ie def keyword - this session
  // 2. Function, FunctionN, Lambda, =>

  // no args, no return values
  // some arguments, no return value
  // args, returns values +
  // data type

  // left side is function declaration/type contract
  // right side is expression/method block {}
  // two arguements as input, return int as output
  def add(a: Int, b: Int): Int = a + b

  // implicit return type/inference
  def sub(a: Int, b: Int) = a - b;

  // block {}
  def mul(a: Int, b: Int) = {
    // multi line statement here
    // return keyword is optional
    // the last executed expression value is returned automatically
    a * b;
  }

  // return statement, return type is MANDATORY, MUST
  def div(a: Int, b: Int): Int = {
    return a / b
  }

  def fact(n: Int): Int = {
    if (n == 1)  1 // return 1
    else  n * fact(n - 1) // return n * fact(n - 1)
  }

  // syntatical error
  // methods are not objects, not first class citizen
  // we cannot assign method to another method
  // we cannot return method as return type
  // we cannot pass method as argument
  // val diff = sub;

  println("Add " + add(20, 10))
  println("Sub " + sub(20, 10))
  println("Mul " + mul(20, 10))
  println("Div " + div(20, 10))
  println("Fact " + fact(5))

}
