// pair 1, 2, 3, .....
// why use tuple when I have set

// set is generic, accept any type Product, Brand, ...
// set can have only one type at a time it can be either Product or Brand

// tuple, each entry can have its own type

// use paranthesis for tuple

val t1 = (10, 20)
val t2 = (30, "Engineer")

println(t1._1) ;; //first element
println(t2._2) ;; //first element

// error _4 is not present
// println(ts._4)

//destructing with tuple
val (a, b) = t1;
// a = 10, b = 20

val (age, profession) = t2
println(age, profession)

val t3 = "d2" -> 20