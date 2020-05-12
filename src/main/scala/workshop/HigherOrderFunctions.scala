package workshop

object HigherOrderFunctions extends  App {
  // a function 1 (Func1) that accept another function 2 (Func2) as input
  // use the accepted function on need basic
 //  ( Int => Int ) Func2, Accept int as arg and return int as result
  // Func1 ( Int => Int, Int ) => Int , which accept a function as input arg,
  // return int as result
  // func1 is higher order function
  val func1: ( Int => Int, Int ) => Int = (func2, a) => func2(a)

  // func2
  val id = (n: Int) => n
  val sq = (n: Int) => n * n
  val cube = (n: Int) => n * n * n

  println(func1(id, 10)) // // id(10)
  println(func1(sq, 10))  // sq(10)
  println(func1(cube, 10)) // cube(10)

  List(10, 20, 30)
    .map(sq) // map is higher order function
    .foreach(println)

  List(10, 20, 30)
    .map(cube) // map is higher order function
    .foreach(println)


  // calc - higher order function
  // that accept another functions of type Function2[Int, Int, Int] and  a and b values

  // add (a, b) ==> a + b
  // sub (a, b) ==> a - b
  // calc(add, a ,b ) => f(a, b)
  // calc(sub, a ,b ) => f(a, b)

}
