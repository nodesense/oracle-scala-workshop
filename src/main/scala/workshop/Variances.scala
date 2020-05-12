package workshop

object Variances extends  App {

    class Tool(val name: String) {
      override def toString: String = s"Tool $name"
    }

    class Hammer(name: String) extends  Tool(name);

    class RockHammer(name: String) extends  Hammer(name);
    class WallHammer(name: String) extends  Hammer(name);

    class Box[+A <: Tool] {
      var tools: List[Tool] = List[Tool]()
      def put(tool: Tool) = tools =  tool :: tools;
      def list = tools.foreach(println(_))
    }

    class WoodenBox[-A <: Hammer] {
      var tools: List[Hammer] = List[Hammer]()
      def put(tool: Hammer) = tools =  tool :: tools;
      def list = tools.foreach(println(_))
    }



    val hammers = new Box[Hammer];
    hammers.put(new Hammer("Hammer"))
    hammers.put(new RockHammer("RockHammer"))
    hammers.put(new WallHammer("WallHammer"))
    hammers.put(new Tool("Tool"))

    hammers.list

    // Box[Tool] is a variant
    // Box[+A] is a co-variance


    val tools: Box[Tool] = hammers;

    val woodenBox = new WoodenBox[WallHammer];
    woodenBox.put(new WallHammer("WallHammer"))




}
