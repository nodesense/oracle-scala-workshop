package workshop

object PartialFunctions  extends  App {
  val divide = new PartialFunction[Int, Int] {
    def apply(x: Int) = 10 / x

    // return true if x is non zero
    override def isDefinedAt(x: Int): Boolean = x != 0;
  }

  val sqrt:PartialFunction[Double, Double] = {
    case d:Double if d > 0 => Math.sqrt(d)

    //case d:Double if d > 0 => Math.sqrt(d)

    //case d:Double if d == 0 => 0

  }

  println("sqrt is 0 defined ", sqrt.isDefinedAt(0))

  println("sqrt is -2 defined ", sqrt.isDefinedAt(-2))

  println("sqrt is 4 defined ", sqrt.isDefinedAt(4))

  //println("sqrt(-2)", sqrt(-2))

  if (sqrt.isDefinedAt(4))
    println("sqrt ", sqrt(4))


  // implicit divide, where case works as isDefined
  val divide2: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }


  //divide(0)

  if (divide.isDefinedAt(0)) {
    divide(0)
  } else {
    println("0 not supported")
  }

  if (divide.isDefinedAt(2))
    println(divide(2))


  // map shall not check isDefinedAt
  println(List(2,4,8).map( k => sqrt(k)))

  println(List(2,4,8, -4)
    .filter(n => sqrt.isDefinedAt(n))
    .map( k => sqrt(k)))

  // shall respect isDefinedAt
  println("Collect ", List(2.0,4.0,8.0, -4.0).collect(sqrt))


  class Order(val amount: Int) {

  }

  class PrimeOrder(a: Int) extends  Order(a);

  class SpecialOrder(a: Int) extends  Order(a);

  class MyOrder[-T <: Order] (val order: Order) {

  }

  case class Result(total: Int)

  val orderFunc: PartialFunction[PrimeOrder, Result] = {
    case order if order.amount >= 10000 => Result(8000)
    case o: Order => Result(7000)
  }

  val orderFunc2: PartialFunction[MyOrder[Order], Result] = {
    case myOrder if myOrder.order.amount >= 10000 => Result(8000)
    case o: Order => Result(7000)
  }


  val myOrder1 = new MyOrder[Order] (new Order(10000));
  val myOrder2 = new MyOrder[Order] (new PrimeOrder(10000));
  val myOrder3 = new MyOrder[Order] (new SpecialOrder(10000));


  println(orderFunc2(myOrder1))
  println(orderFunc2(myOrder2))
  println(orderFunc2(myOrder3))

  println(orderFunc2.isDefinedAt(myOrder1))
  //println(orderFunc(new Order(10000)))


}