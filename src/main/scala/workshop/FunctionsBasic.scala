package workshop

// def has name, method, similar to java class method
// function, annonymous, similar to lambda in java
// functions are called first class citizen in SCALA
// functions are object with apply method
// syntactic sugar
// FAT Arrow =>
//  declaration => expression/block
// (a: Int) => expression
object FunctionsBasic extends  App {
  // annon function
  // SCALA compiler, automatically generate a FunctionN code behind

  // 0 argument, no return type, (Unit type ==> void)
  val sayHello = () => println("Hello")

  // 1 argument, no return type
  val sayName = (name: String) => println("Hello " + name)

  // 1 arg, 1 return type
  val sq = (n: Int) => n * n
  // 2 args, 1 return type
  val add = (a: Int, b: Int) => a + b
  val sub = (a: Int, b: Int) => {
                                  // block, multi line
                                  // last statement is returned
                                  a - b
                                }

  // sum is reference to function , same as add ref
  // pass as argument, higher order function
  // return as  return value, curry
  val sum = add;
  val diff = sub;

  println("SayHello" + sayHello())
  println("SayHello" + sayName("Scala"))
  println("SayHello" + sq(10))

  println("Add " + add(10, 20))
  println("Sub" + sub(10, 20))

  println("Sum " + sum(10, 20))
  println("Diff" + diff(10, 20))

  // syntactic sugar? where is that?
  // scala compiler will change sum(10, 20), change to sum.apply(10, 20)
  println("Sum.apply " + sum.apply(10, 20))

}
