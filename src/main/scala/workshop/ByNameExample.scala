package workshop

// by name - lazy evaluation
object ByNameExample extends  App {
   def nano() = {
     println("Getting System Time ")
     System.nanoTime() // return nano seconds
   }

  // t is a long value
  def withoutByName(t: Long) = {
    println("withoutByName");
    println("Time is " + t)
  }

  // t is a code block
  // code block returns a long value
  def delayed( t: => Long) = {
    println("delayed");
    println("Time is " + t )
    println("Time2 is " + t )
    println("Time3 is " + t )
  }

  // normal way of calling
  // how below statement shall be executed
  // first nano() shall be called, get long nano seconds
  // output of nano() shall be passed to withoutByName as input
  // print withoutByName
  // time is 4312412432432423
  withoutByName(nano())
  println("-------------")
  delayed(nano()) // nano() is a expression taken as block, but  NOT executed

  delayed({
            println("This is coming from block")
            nano()
          }) // block expression, should return long data

  // example that can implement if else part
  // if (predicate) truePart else elsePart
  // predicate is block, return true/false/boolean
  // truePart is block, return  Unit/void
  // elsePart is block, return Unit/void

  def ifElse( predicate: => Boolean,
              truePart: => Unit,
              elsePart: => Unit) = {

     if (predicate) truePart else elsePart
  }

  ifElse(true, println("True"), println("False"))
  ifElse({
            10 % 2 == 0
          }, {
              println("Even")
              }, {
                println("Odd")
          })

}
