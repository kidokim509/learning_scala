import scala.math._

/**
  * https://docs.scala-lang.org/tour/singleton-objects.html
  */
object Chap12SingletonObject extends App {
  // Lazy val: https://www.holaxprogramming.com/2017/11/18/scala-value-naming/

  // Companion Object
  // An object with the same name as a class is called a companion object.
  // Conversely, the class is the object’s companion class.
  // A companion class or object can access the private members of its companion.
  // Use a companion object for methods and values which are not specific to instances of the companion class.
  case class Circle(radius: Double) {
    import Circle._
    def area: Double = calculateArea(radius)
  }

  object Circle {
    private def calculateArea(radius: Double): Double = Pi * pow(radius, 2.0)
  }

  val circle1 = new Circle(5.0)

  circle1.area

  // factory methods
  class Email(val username: String, val domainName: String)

  object Email {
    def fromString(emailString: String): Option[Email] = {
      emailString.split('@') match {
        case Array(a, b) => Some(new Email(a, b))
        case _ => None
      }
    }
  }

  val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
  scalaCenterEmail match {
    case Some(email) => println(
      s"""Registered an email
         |Username: ${email.username}
         |Domain name: ${email.domainName}
     """)
    case None => println("Error: could not parse email")
  }
}
