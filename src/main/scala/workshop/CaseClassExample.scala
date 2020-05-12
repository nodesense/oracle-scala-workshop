package workshop

object CaseClassExample  extends  App {
  // Class
  // Object
  // Immutable
  // Companion Object [Class name + Object name same + Apply func]

  // Scala generate some codes for you
  // It create a class, companion object, also apply methods
  // id, name, price are basically members of the class
  // members are immutable, it should like that
  // Record pattern/fact/should not be changed
  case class Product(id: Int, name: String,  price: Double)

  // below mutable member example, but BAD PRACTICE
  // case class Brand(id: Int, var name: String);
  // val b = Brand(10, "Google")
  // b.name = "Samsung" // BAD practice

  // example: companion object, note with out new keyword, we create object
  val p = Product(10, "Samsung nexus", 15000)
  val p2 = Product(12, "iPhone", 45000)

  // we cannot change values
  // immutable by default
  // p.price = 1000;

  println(p)
  println(p2)

}
