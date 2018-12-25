/**
  * https://docs.scala-lang.org/tour/extractor-objects.html
  */
object Chap14ExtractorObjects extends App {

  /*
  An extractor object is an object with an unapply method.
  Whereas the apply method is like a constructor which takes arguments and creates an object,
  the unapply takes an object and tries to give back the arguments.
  This is most often used in pattern matching and partial functions.
   */
  import scala.util.Random

  object CustomerID {

    def apply(name: String) = s"$name--${Random.nextLong}"

    def unapply(customerID: String): Option[String] = {
      val stringArray: Array[String] = customerID.split("--")
      if (stringArray.tail.nonEmpty) Some(stringArray.head) else None
    }
  }

  val customer1ID = CustomerID("Sukyoung")  // Sukyoung--23098234908
  customer1ID match {
    case CustomerID(name) => println(name)  // prints Sukyoung
    case _ => println("Could not extract a CustomerID")
  }

  // The apply method creates a CustomerID string from a name. The unapply does the inverse to get the name back.
  val customer2ID = CustomerID("Nico")
  val CustomerID(name) = customer2ID
  println(name)  // prints Nico

  val CustomerID(name2) = "--asdfasdfasdf"

  val CustomerID(name3) = "-asdfasdfasdf" //match error

}
