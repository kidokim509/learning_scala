/**
  * https://docs.scala-lang.org/tour/traits.html
  */
object Chap04Traits extends App {
  trait HairColor

  // Defining Traits
  trait Iterator[A] {
    def hasNext: Boolean
    def next(): A
  }

  // Using Traits
  class IntIterator(to: Int) extends Iterator[Int] {
    private var current = 0
    override def hasNext: Boolean = current < to
    override def next(): Int =  {
      if (hasNext) {
        val t = current
        current += 1
        t
      } else 0
    }
  }

  val iterator = new IntIterator(10)
  iterator.next()  // returns 0
  iterator.next()  // returns 1
  println(iterator.next())

  // Subtyping
  import scala.collection.mutable.ArrayBuffer

  trait Pet {
    val name: String
  }

  class Cat(val name: String) extends Pet
  class Dog(val name: String) extends Pet

  val dog = new Dog("Harry")
  val cat = new Cat("Sally")

  val animals = ArrayBuffer.empty[Pet]
  animals.append(dog)
  animals.append(cat)
  animals.foreach(pet => println(pet.name))  // Prints Harry Sally
}
