package workshop

object NamedArgs extends  App {
  def sum(a: Int = 0, b: Int = 0) = a + b
  def diff(a: Int, b: Int) = a - b

  // pass the values by argument name
  println(diff (b = 20, a = 10)); // a = 10, b= 20
  println(sum( b = 50)); // b = 50, a = 0
}
