package workshop

// trait in Scala similar to interface in Java
// App trait, built in trait which has main method defined already
// no need to write custom main function
// { block }- expression

object HelloWorldAppTrait extends  App {
    // this area is called block constructor
    // you can write expression/statements here
   // those are executed automatically
   // main method body
    println("Hello World App");
    println("args " +  args.length);
    // args(0), args(1)

    // Variables [mutable, value/reference can be changed]
    // scopped inside the block/ie default constrcutor of object
   // type is inferenced from right side expression
    var i = 10; // inference
    var j: Int = 20; // explicit
    println("I " + i);
    i = i + 20;
    println("I " + i);

    // value [immutable, value/reference cannot be changed, const ]
    val PI = 3.14;
     // this will not work ==> PI = 2.14;
    // value cannot be changed
    println("PI " + PI);

}
