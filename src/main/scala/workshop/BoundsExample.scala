package workshop

object BoundsExample  extends  App {
  class Vehicle

  class FourWheeler extends  Vehicle

  class Car extends  FourWheeler
  class Jeep extends  FourWheeler

  // Upper Bound
  //  Four Wheeler [Vehicle, Motor]
  // Or Sub Type Four Wheeler
  def upperBound = {
     class Parking {
        // <: upper bound
        def park [T <: FourWheeler] (v: T) = {

        }
     }

    val parking = new Parking
    // parking.park(new Vehicle) // not allowed
    parking.park(new FourWheeler) // allowed
    parking.park(new Car) // allowed
  }

  def lowerBound = {
    class Parking {
      // >: lower bound
      def park [T >: FourWheeler] (v: T) = {
      }
    }

    val parking = new Parking
    parking.park(new FourWheeler) // allowed
    parking.park(new Vehicle) // allowed
  }


}
