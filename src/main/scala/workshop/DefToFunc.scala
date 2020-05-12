package workshop

object DefToFunc extends  App {

  // how to convert method to function
  // why, assign, return, pass as arg, first class citizen
  def add(a: Int, b: Int) = a + b;

  // convert method to function
  // METHOD _ will convert your method to functionN
  val sum = add _
  val sum2: (Int, Int) => Int = add _;
  val sum3: Function2[Int, Int, Int] = add _;

  //add.apply
  sum(10, 20)
  sum.apply(10, 20)
}
