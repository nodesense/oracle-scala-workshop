import scala.collection.mutable.Map

var map = Map[String, Int] (
  ("KA", 500),
  "TN" -> 8000
)

map += "AP" -> 1500

val t = ("MH", 20000)

map += t


map -= ("TN")

map += "AP" -> 2000


for (x <- map)
    printf("Key %s, value %d\n", x._1, x._2)