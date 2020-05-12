package workshop

// catch itself works like match statement

// Scala Option - two options
//    Some value
//    None value

object OptionsExample extends  App {
  // toInt has two option results
  // some value -- some int value
  // none value
  def toInt(input: String):  Option[Int]  = {
    try {
      Some(input.toInt) // if success, we get number, no problem
    }catch {
      // if failed, we get exception, what should we return

      // if function function fails, we cannot give 0 value
      case t: Throwable =>   None
    }
  }

  println(toInt("100")); // prints Some(100)

  println(toInt("Scala")); // prints None

  val result: Option[Int] = toInt("600")
  println("is Empty ", result.isEmpty);
  println("is isDefined ", result.isDefined);

  if (result.isDefined) {
    val intVal = result.get // returns the actual value inside the option
    println("parsed value is " + intVal)
  }


  val dataPoints = List("10", "20", "30", "nan", "missing", "", "40");

  val results = dataPoints
                .map(toInt)
                .filter(option => option.isDefined)
                .map(option => option.get)

  for (result <- results) {
    println("Result ", result);
  }

  println(" MIN ", results.min)
  println("MAX ", results.max);
  println("Sum ", results.sum)

}
