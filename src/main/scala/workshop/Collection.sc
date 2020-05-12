val list = List(10, 21, 30, 43, 51)

for (n <- list) {
  println("N " + n)
}

list.filter ( n => n % 2 == 1)
list.map( n => n * 2)

list.map ( _ * 2)

list
    .filter ( n => n % 2 == 1)
    .map( n => n * 2)

list.partition( n => n % 2 == 0)
var (evenList, oddList) = list.partition( n => n % 2 == 0)

