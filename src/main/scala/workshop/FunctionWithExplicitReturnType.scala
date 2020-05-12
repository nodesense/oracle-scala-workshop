package workshop

object FunctionWithExplicitReturnType extends  App {
  // implicit return type
  val add = (a: Int, b: Int) => a + b

  //explicit return type
  // sum is a immutable
  // (Int, Int) => Int is a type of the function Function2 with return Int
  //  (Int, Int) => Int  =====> Function2[Int, Int, Int]
  // = assignemnt to sum
  // Right to = is (a, b) => a + b function body/implementation
  // sum is a Type Function2
  val sum: (Int, Int) => Int = (a, b) => a + b

  val sum2: Function2[Int, Int, Int] = (a, b) => a + b

  var i: Int = 10;

  println(sum(20, 30));
  println(sum2(20, 30));

}
