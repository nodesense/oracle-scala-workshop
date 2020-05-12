package workshop

// In scala, match an expression (keyword), similar to switch statement in Java

// expression - evaluate (execute) and returns value
// statement - execute and does not return value

object MatchExample extends  App {
  // match block/expression returns an output, either Even or Odd
  val oddOrEven = 10 % 2 match {
    case 0 => "Even"
    case 1 => "Odd"
  }

  println(oddOrEven);

  var gender = "it"
  val g = gender match {
    case "she" => "Female"
    case "he" => "Male"
    // default keyword? there is no default keyword
    // convention, use _ as default
    // case x => "Unknown"
    case _ => "Unknown"
  }

  println("Gender " + g);
}
