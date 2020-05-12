package workshop
object TypeSystem extends  App {

  trait Thing;

  class Vehicle extends  Thing {
    var numberPlate: Int = _;
  }

  class Car extends  Vehicle;
  class Jeeb extends  Car;

  class ArmyJeep extends  Jeeb;

  class Motorcycle extends  Vehicle;

  class Animal;

  def simpleTypes() = {
    // Invariant

    class Parking[A]  {
      def park(v: A) = {}
    }

    val parking = new Parking[Car];

    val parking2 = new Parking[String];

    // Invariant, error
    // val parking3:Parking[Vehicle] = new Parking[Car]
  }

  def CoVariantExample = {

    class Parking[+A]  {
      //def park[B >: A](v: B) = {}
    }

    val parking = new Parking[Car];

    // Co-Variant, NO error +A
    val parking3:Parking[Vehicle] = new Parking[Car]
  }


  def ContraVariantExample = {

    class Parking[-A] {
      // def park(v: A) = {}
    }

    val parking = new Parking[Car];

    // Contra-Variant, NO error -A
    // Car - S
    // Vehecle is given T
    val parking3:Parking[Car] = new Parking[Vehicle]

    val p2 = new Parking[Vehicle];
    //p2.park(new MotorCycle)

    val parking4:Parking[Car] = p2;
  }

  // bound: restriction on type
  def UpperBoundExample: Unit = {
    class Parking[A <: Vehicle](val vehicle: A) {
      def show() = vehicle.numberPlate
    }


    new Parking[Car](new Car)
    new  Parking[Jeeb](new Jeeb())

    new  Parking[Motorcycle](new Motorcycle())
    new Parking[Vehicle](new Vehicle)
  }


  def LowerBoundExample: Unit = {
    class Parking[A >: Car](val place: A)

    new Parking[Vehicle](new Vehicle)

    // new Parking[Car](new Jeeb)

    new Parking[Vehicle](new Car)

    // Error, since Car is lower bound,
    // new  Parking[Jeeb](new Jeeb())

  }


  def LowerAndUpperBound: Unit = {
    // Accept all types Vehicle and below and Bicycle and Above
    class Parking[A >: ArmyJeep <: Car](val plaza: A)

    // upper bound issue, error
    // new Parking[Vehicle](new Car)
    new Parking[Car](new Car)

    new Parking[Jeeb](new Jeeb)
    new Parking[ArmyJeep](new ArmyJeep)

  }

}