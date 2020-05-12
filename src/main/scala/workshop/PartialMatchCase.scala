package workshop

object PartialMatchCase extends  App {
  case class SinglePhase ( watt: Int)
  case class ThreePhase ( watt1: Int, watt2: Int, watt3: Int)
  case class SinglePhaseSpecialScheme ( watt: Int)
  case class IndustryPower ( watt: Int)

  val totalWatt: PartialFunction[Object, Int] = {
    case s: SinglePhase => s.watt
    case t: ThreePhase => t.watt1 + t.watt2 + t.watt3
    // case with guard (if)
    // suitable for partial function to check if defined or not
    case   ss: SinglePhaseSpecialScheme if ss.watt <= 20000 => 0
    // this shall be matched if above condition is not met
    case   ss: SinglePhaseSpecialScheme  => ss.watt
  }

  println("SinglePhase(100000)",  totalWatt.isDefinedAt(SinglePhase(100000)))
  println("SinglePhaseSpecialScheme(15000)",  totalWatt.isDefinedAt(SinglePhaseSpecialScheme(15000)))

   val area1 = List(SinglePhase(10000),
                    ThreePhase(10000, 9999, 10111),
                    IndustryPower(100000000)
   )

  // map function shall not use isDefinedAt
  // cause exception due to IndustryPower match
  // area1.map (r => totalWatt(r))

  // custom code
  val output = area1
      .filter (r => totalWatt.isDefinedAt(r))
      .map (r => totalWatt(r))

  println(output);

  // better approahc is to use collect method
  // collect will call isDefinedAt/PartialFunction
  val output2 = area1
      .collect(totalWatt)

  println(output2);
}
