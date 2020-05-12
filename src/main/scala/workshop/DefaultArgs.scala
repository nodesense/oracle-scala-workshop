package workshop

object DefaultArgs extends  App {
  // if a/b values not passed, default one is used
  def sum(a: Int = 0, b: Int = 0) = a + b

   println(sum()); // a = 0, b= 0
   println(sum(10)); // a = 10, b = 0
   println(sum(10, 20)); // a = 10, b = 20

}
