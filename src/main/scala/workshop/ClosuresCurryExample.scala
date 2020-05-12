package workshop

object ClosuresCurryExample extends  App {
  // Curry function
  // a function returns another function

  // Closure is a design pattern,
  // encapculate state, maintain state within functional programs

  val Seq = (seed: Int, step: Int) => {
     //scope/visiblity/closure
      var counter = seed
      println(s"Seq $seed, $step")

      val incr = () => {
        val result = counter
        counter += step
        println(s"Incr $result")
        result // return result as int
      }

      incr // return a function/curry
  }

  val seq1By1: Function0[Int] = Seq(1, 1)
  val seq100By10 = Seq(100, 10)

  println(" seq1By1 " + seq1By1());
  println(" seq1By1 " + seq1By1());
  println(" seq1By1 " + seq1By1());
  println(" seq100By10 " + seq100By10());
  println(" seq100By10 " + seq100By10());
  println(" seq100By10 " + seq100By10());
}
