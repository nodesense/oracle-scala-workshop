package workshop

object TypeSystem1 extends  App {
   class Vehicle

   class Car extends  Vehicle
   class Jeeb extends  Vehicle

   // example
   def simpleTypes () = {
      class Parking[A] {
        def park(v: A) = {}
      }

      val carParking = new Parking[Car]
      val car1 = new Car
      carParking.park(car1) // allowed

      var jeeb1 = new Jeeb
      // carParking.park(jeeb1) // not allowed, compile time error

     // variant type/normal in many language
     val vehicleParking: Parking[Vehicle] = new Parking[Vehicle]
     vehicleParking.park(car1)
     vehicleParking.park(jeeb1)

     // NOT  allowed
     // called as invariant in Type System
     // compiler cannot assing Parking[Car] to Parking[Vehicle]
     // val parking2: Parking[Vehicle] = new Parking[Car];
     // refer coVariantExample for answer
   }

   // co-variant +A
   def coVariantExample = {
      // Allow/accept the subtypes of A
      class Parking[+A] {
      }

     // Car/Jeep are sub-types of Vehicle
      // super type = subtype
      val parking: Parking[Vehicle] = new Parking[Car];
   }

  // contra variant - -A
   def contraVariant = {
     // rare instances
     class Parking[-A] {
     }

     // forcing sub type = super type
     //
     val parking: Parking[Car] = new Parking[Vehicle];
   }



}
