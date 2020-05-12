package workshop

object ForYield extends  App {
   def sq(a : Int) = {
     println("SQ processing ", a)
     a * a
   }

   val numbers = List(1, 2, 3, 4)
   // for statement, that doesn't return value
   val result = for (n <- numbers)  sq(n)

   println("REsult ", result)

  // for expression, returns the value
  val result2 = for (n <- numbers) yield sq(n)

  println("yield result ", result2)

  case class User(id: Int); //
  def getUser(id: Int): Option[User] = {
    // OK, user found in the DB
    Some(User(id))
  }

  def getBalance(user: User): Option[Double] = {
    Some(1000)
  }

  def getOffer(user: User): Option[Double] = {
    Some(1.2)
  }

  def getNotFoundUser(id: Int): Option[User] = {
    None
  }

  val userOption = getUser(1);
  println("userOption ", userOption)
  if (userOption.isDefined) {
    val user = userOption.get
    println("User ", user)
  }

  val result3 = for {
                         user <- getUser(1)
                         balance <- getBalance(user)
                         offer <- getOffer(user)
                      } yield balance * offer;

  println("result ", result3)


  val result4 = for {
    user <- getNotFoundUser(1) // user not found
    balance <- getBalance(user) // will not be executed
    offer <- getOffer(user) // will not be executed
  } yield balance * offer; // will not be executed

  println("result4 ", result4)



}
