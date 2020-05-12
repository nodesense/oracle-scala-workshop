package workshop

object FunctionN extends  App {
  // 0 or more arguments are input
  // 0 or 1 output as return type

  // this code, convereted into Function0 by Scala compiler/ sugar
  //  val sayHello = () => println("Hello")

  // 0 means, no/zero argument as input , 1 output
  val sayHello = new Function0[Unit] {
    def apply(): Unit = println("Hello")
  }

  // 1 argument, no return type
  //  val sayName = (name: String) => println("Hello " + name)
  // Function1 means, 1 arg, Unit as return type ie void
  val sayName = new Function1[String, Unit] {
    def apply(name: String): Unit = println("Hello " + name)
  }

  // 1 arg as Int, 1 return type as Int
  val sq = new Function1[Int, Int] {
    def apply(n: Int): Int = n * n
  }

  // two input arguments type type
  // 1 return value at last
  val add = new Function2[Int, Int, Int] {
    def apply(a: Int, b: Int): Int = a + b
  }

  val sayGreeting = sayHello

  sayHello() // apply is invoked automatically
  sayHello.apply()
  sayGreeting()

  sayName("Scala")
  println("square 2 = " + sq(2))
  println(" add " + add.apply(10, 20))

}
