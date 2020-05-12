package workshop

// Scala - Object - Singleton instance
object HelloWorld {

  // def - scala - method, Java Methods
  // main is called very first of program execution
  // args - command line arguments, passed from external command line tools
  // Unit - void,  return nothing
  // { is a block } - code/expression is written inside
  // ; is not needed, optional
  def main(args: Array[String]): Unit = {
    println("Hello Scala")

    println("length " + args.length);

    println("Args" +  args(0)); // first argument
    println("Args1", args(1)); // second argument
    println("Args2", args(2)); // 3rd argument

  }

}
