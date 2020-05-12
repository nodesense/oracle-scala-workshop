import scala.collection.mutable.ListBuffer

// mutable, add/remove

val list = ListBuffer(10, 20)

println(list)

list += 30;
list += 40;
list -= 20;
println(list)

list.addOne(50)
list.exists( x => x == 40)
