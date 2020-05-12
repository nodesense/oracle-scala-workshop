package workshop

object AndThenExample extends  App {
  // similar to compose, BUT IN REVERSE ORDER
  // compose --> right to left
  // andThen --> left to right

  def f(str: String) = s"f($str)"
  def g(str: String) = s"g($str)"

  //g(f(x))
  val f_andThen_g = (f _) andThen (g _);
  println(f_andThen_g("x"))

  val applyDiscount = (amount: Double ) => {
    amount - ( amount * .10 )
  }

  val applyTax = (amount: Double) => {
    amount * 1.18
  }

  val applyDiscountAndThenTax = applyDiscount andThen applyTax
  println(applyDiscountAndThenTax(100));


}
