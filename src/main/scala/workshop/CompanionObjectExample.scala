package workshop

object CompanionObjectExample  extends  App {
  // CompanionObject - A Class name + Object name share the same name
  // Builder pattern, factory patterns, extraction pattern

  // can create multiple instance
  class Brand(val id: Int, val name: String) {
    println("ID " + id)
    println("name " + name)
  }

  // singleton, factory pattern to produce object
  // constructor initialize on first use
  object Brand {
    println("Brand object cons")
    def apply() = {
      println("Brand Apply called")
      // create a new Brand object and returns the same
      // ie a factory pattern
      new Brand(0, "unknown")
    }
    // overloaded apply method
    def apply(id: Int) = {
      new Brand(id, "unknown")
    }

    def apply(id: Int, name: String) = {
      new Brand(id, name)
    }

    //TODO:
    // unapply
  }

  // val b1 = new Brand(10, "Samsung"), look at new keyword, it is not functional

  // notice keyword new to create object
  val b1 = new Brand(10, "Samsung");

  //  We are going to CUT DOWN new keyword with companion object
  // call Brand.apply
  val b3: Brand = Brand.apply();
  // Brand() - functional, calls Brand.apply automatically
  val b2 = Brand() //shorthand to Brand.apply,

  val b4 = Brand(100) // apply(100)

  val b5 = Brand(110, "Google") // apply(100, "Google")

  // what we achived? without using new keyword, we could create object

}
