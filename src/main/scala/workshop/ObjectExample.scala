package workshop

// Class is blueprint
// object is Instance of Class Blueprint
// using Class, we can produce as many instances

// Singleton - Only one instance of Class blueprint

// two conventions
// Scala object is singleton instance by default, WITHOUT a class
// Scala object is singleton with class - Companion Object/Class - next topic

// static class/ singleton instance
// we cannot create instance for object
// object itself is singleton object
object Logger {
   // Object constructor
   var level = "INFO";
   println(s"Default level $level" )
   def info(msg: String) = println(" Info " + msg)

   // string interpolation
  // special sugar from scala, symbol "s"
  def error(msg: String) = println(s"Error $msg")
}


object ObjectExample extends  App {
  Logger.info("Starting scala App")
  Logger.error("DB Access")
  Logger.level = "ERROR";
}
