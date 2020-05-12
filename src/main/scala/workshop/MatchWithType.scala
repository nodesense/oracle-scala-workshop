package workshop

object MatchWithType extends  App {

  case class SinglePhase ( watt: Int)
  case class ThreePhase ( watt1: Int, watt2: Int, watt3: Int)

  case class SinglePhaseSpecialScheme ( watt: Int)

  // Match Case with Type, Extraction (unapply)

  def totalWatt(obj: Object): Int = obj match {
    case s: SinglePhase => s.watt
    case t: ThreePhase => t.watt1 + t.watt2 + t.watt3

    // case with guard (if)
    // suitable for partial function to check if defined or not
    case   ss: SinglePhaseSpecialScheme if ss.watt <= 20000 => 0
    // this shall be matched if above condition is not met
    case   ss: SinglePhaseSpecialScheme  => ss.watt

    // traditional way
//    case ss: SinglePhaseSpecialScheme => {
//      if (ss.watt <= 20000) return 0
//      ss.watt
//    }

    case _ => 0 // default
  }

  // Extraction example
  def totalWatt2(obj: Object): Int = obj match {
    case  SinglePhase(watt) => watt
    case  ThreePhase(watt1, watt2, watt3) => watt1 + watt2 + watt3
    case _ => 0 // default
  }

  var reading1 = SinglePhase(100);

  var reading2 = ThreePhase(1000, 1200, 990);

  println("reading1 " + totalWatt (reading1));
  println("reading2 " + totalWatt (reading2));

  println("helloworld default " + totalWatt ("helloworld"));



  println("reading1 " + totalWatt2 (reading1));
  println("reading2 " + totalWatt2 (reading2));


  println("<= 20K, chargeable " + totalWatt(SinglePhaseSpecialScheme(15000)))
  println("> 30K, chargeable " + totalWatt(SinglePhaseSpecialScheme(30000)))

}
