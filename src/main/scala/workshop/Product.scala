package workshop

object ProductApp extends  App {
  class Product(val id: Int,
                var price: Int) {
    println("Product cons " )

    def getPrice = price
    def setPrice(value: Int) = price = value
  }

  class ElectronicProduct(id: Int,
                          price: Int,
                          val voltage: Int) extends  Product(id, price) {
    println("ElectronicProduct cons")

    def applyDiscount = {
      // price represent consructor local arg, immutable type, not base class
      // super represent base class instance
       super.setPrice((getPrice * 20.0 /100).toInt);
    }
  }

  val mobile1 = new ElectronicProduct(1, 5000, 220)
  // mobile1.id, , price, voltage

  val product1 : Product = mobile1;
  // product1 id, price


}
