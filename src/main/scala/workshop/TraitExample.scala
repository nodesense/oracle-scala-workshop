package workshop

object TraitExample extends  App {
  // Trait is an interface
  // Trait a contract/polymorphism

  trait Vehicle {
    def start(): Unit
    def stop(): Unit
    def isOn(): Boolean
    // default implementation
    def isOff(): Boolean = !isOn
  }

  trait AC {
    def on(): Unit
  }

  class Motor

  // can do relationship
  // only one trait/class can use extends keyword
  // others trait should use with keyword [implements in java]
  class Car extends  Motor with Vehicle with AC {
    var _engineOn = false;
    def start(): Unit = _engineOn = true
    def stop(): Unit = _engineOn = false;
    def isOn(): Boolean = _engineOn

    // isOff already has default

    def on(): Unit = println("AC on")
  }

  class Bus extends  Vehicle   {
    var _engineOn = false;
    def start(): Unit = _engineOn = true
    def stop(): Unit = _engineOn = false;
    def isOn(): Boolean = _engineOn

    // isOff already has default
  }

   //Vehicle is an abstraction
  val v1: Vehicle = new Car
  val v2: Vehicle = new Bus

  val ac1: AC = new Car;
  println(v1.isOn())
}
