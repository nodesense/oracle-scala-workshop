package workshop

object LazyExample extends  App {
  // lazy means differed initialization/execution of expression until the first use

  val a = 10 // value 10 is initiazlied here
  lazy val b = 20 // value 20 is not initialized to b until its first use

  println("A is " + a);
  println("B is " + b); // b is used here, so it is initialized here first time
  println("B is " + b); // b is already initialized

  // block expression
  // more statements
  // last evaluated expression returned
  val x = {
             println("X");
             100 // return/assign 100 to val x
          }

  lazy val y = {
            println("Y");
            200; // 200 shall be assgiend later, not now
          }

  println("Using Y first time")
  val s = y + 20; // since y is lazy, we use it first time, the block will be executed
                  // 200 + 20

}
