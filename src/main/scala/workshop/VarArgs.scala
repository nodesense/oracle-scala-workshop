package workshop

object VarArgs extends  App {
  // pass many arguments, 0, 1, 2, ..n/variable number of arguments

  // * - variable number of strings passed
  def printNames(names: String*) = {
    names.foreach(println);
  }


  def sum(numbers: Int*) = {
    var total = 0
    for (n <- numbers) {
      total += n
    }

    total // return total
  }

  printNames("Scala", "JVM", "Course");
  println("sum " + sum());

  println("sum(10) " + sum(10));
  println("sum(10, 20) " + sum(10, 20));
}
