package workshop

object ImplicitExample extends  App {
  // js this.something - explicit
  // scala/java/ something ==> compiler will resovle
  // implicit this.something

  // Implicit works with Type strictly

  // SCALA
  // to initialize values in object
  // to pass parameters
  // type casting
  // in place global
  def add(a: Int) (implicit b: Int) = a + b
  def sayName(implicit title: String) = println(s"Name is $title")

  def method1 = {
    // all implicit shall be used only within scope
    implicit  val x: Int = 5
    // ambiguity of same TYPE implicit NOT ALLOWED
    // implicit  val y: Int = 50
    // allowed since it is different TYPE
    implicit  val name: String = "SCALA"

    // now the value of x of Type INT is passed
    // as value to implicit b
    // compiler resolve implicity type Int and pick value 5 for implicit
    val result = add(10)// a is 10, b is 5
    println("result " + result)

    // pass name as implicit
    sayName

    // the parameter value is used, instead of implicit
    sayName("AKKA")

    // you want to access implicit value here,
    // but without mentioning variable name

    // getter function to get the value of implicit by type
    val intVal = implicitly[Int]
    println("Int value is " + intVal)

    val strVal = implicitly[String]
    println("strVal value is " + strVal)
  }

  def method2 = {
    // will not compile since implicits are specific to scope
    // could not find type string implicit value
    // sayName
  }

  method1 // calling method 1

  // 42.0 // double 8 bytes

  // how to use implicit to cast value
  implicit  def doubleToInt(d: Double) = {
    println("doubleToInt called automatically")
    d.toInt
  }

  val a: Int = 42.0 // calls implicit doubleToInt

}
