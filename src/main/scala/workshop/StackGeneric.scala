package workshop

// implement stack using immutable list
object StackGeneric extends  App {
  // in Java, diamand <>
  class Stack[A] {
    var elems: List[A] = Nil
    def push(x: A): Unit = {
      // create a new list, add the x to the top and return
      elems = x :: elems
    }
    def top: A = elems.head
    def pop() { elems = elems.tail }
    def isEmpty = elems.isEmpty
  }

  case class Product(id: String);
  case class Order(amount: Int)
  val stack: Stack[Product] = new Stack;
  val stackOrders: Stack[Order] = new Stack;

  stack.push(Product("phone 1"))
  stack.push(Product("phone 2"))
  println(stack.top)
  stack.pop
  println(stack.top)
  println(stack.isEmpty)
}
