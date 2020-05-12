package workshop

object ParameterGroup extends  App {
  // normal function
  def add(a: Int, b: Int, c: Int) = a +  b + c
  // add(1, 2,3)

  // parameter group
  // a curry function, deep nested
  def sum(a: Int) (b:Int) (c:Int) = a + b + c

  // func code
  val sumF = (a: Int) => {
    (b: Int) => {
      (c: Int) => {
        a + b + c
      }
    }
  }

  println(sumF(10)(5)(2))

  val sumFF = (a: Int) => (b: Int) => (c: Int) => a + b + c

  println(sumFF(10)(5)(2))

  // convert method to func
  val sumFunc = sum _;
  val sum10 = sumFunc(10); // a = 10, sum10 is a func
  val sum10Plus5 = sum10(5) // b = 5, applied sum10 where a is 10

  val result: Int = sum10Plus5(2) // c = 2, applied to sum10Plus5 where a is 10 b is 5
  println("Result " + result)

  println(sum10Plus5(100));

  val sum1000 = sumFunc(1000);
  val sum1000Plus50 = sum1000(50)

 println(sum1000Plus50(2));

}
