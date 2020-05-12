package workshop


object OOP extends  App {

  //blueprint
  // default consrutctor argument
  // id is not member variable
  // how to define member variable?
  // two options
      // constructor declaration [var, val keywords should be used]
      // class body

  // val is member variable, public access specifier
  // private only this class
  class Order(id: Int,
              val custId: Int,
              private var amount: Int,
              d: Int
             )   {
    // declare member variables in class body

    // Java Like getter
    def getAmount = amount
    def setAmount (value: Int): Unit = amount = value

    //scala style getter /setter
    // member variable, private scope
    private var _discount = d;
    def discount = _discount

    // tricky part in scala style setter
    // speicial scala method, no space between = _ name
    // getterName_=
    def discount_= (value: Int) = _discount = value;


    // id a constrcutor arg, by default it is immutable, NOT CLASS Inst member
    //id = 200;
    // block, expression
    // the whole body expression block, is a default constructor
    println("default cons " + id);

    // custId, amount is member variable of public
    println("custId " + custId);
    println("amount " + amount);

  }

  // instance
  var order1 = new Order(0, 100, 1000, 10);

  // order1.custId = 150; //error due to val/immutable
  order1.setAmount(1500);

  println("Get amount " + order1.getAmount )

  var order2 = new Order(1, 200, 2000, 20);
  // order1.id is not accessible, because id is local variable to constructor
  println("Customer id 0 " + order1.custId);

  println("Order 1 discount " + order1.discount);
  // change value through setter
  // calls method discount_=
  order1.discount = 50
  println("Order 1 new discount " + order1.discount);


}
