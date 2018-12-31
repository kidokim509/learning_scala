/**
  * https://docs.scala-lang.org/tour/generic-classes.html
  */
object Chap15GenericClasses extends App {
  /*
  Defining Generic
   */
  class Stack[A] {
    private var elements: List[A] = Nil
    def push(x: A) { elements = x :: elements }
    def peek: A = elements.head
    def pop(): A = {
      var currentTop = peek
      elements = elements.tail
      currentTop
    }
  }

  val stack = new Stack[Int]
  stack.push(1)
  stack.push(2)
  println(stack.pop)
  println(stack.pop)


  class Fruit
  class Apple extends Fruit
  class Banana extends Fruit

  val stack2 = new Stack[Fruit]
  val apple = new Apple
  val banana = new Banana

  stack2.push(apple)
  stack2.push(banana)


  val stack3 = new Stack[Char]
  stack3.push(1) // Char, Int는 모두 AnyVal을 상속 받기에.. 이러한 경우를 방지하고자 Variance 추가

  /*
  Subtyping - Variances
   */
  class Foo[+A] // A covariant class
  class Bar[-A] // A contravariant class
  class Baz[A]  // An invariant class

  // Covariant
  abstract class Animal {
    def name: String
  }
  case class Cat(name: String) extends Animal
  case class Dog(name: String) extends Animal

  object CovarianceTest extends App {
    def printAnimalNames(animals: List[Animal]): Unit = {
      animals.foreach { animal =>
        println(animal.name)
      }
    }

    // The Scala standard library has a generic immutable sealed abstract class List[+A] class,
    // where the type parameter A is covariant.
    val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
    val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

    printAnimalNames(cats)
    // Whiskers
    // Tom

    printAnimalNames(dogs)
    // Fido
    // Rex
  }

  // Contravariance
  abstract class Printer[-A] {
    def print(value: A): Unit
  }

  class AnimalPrinter extends Printer[Animal] {
    def print(animal: Animal): Unit =
      println("The animal's name is: " + animal.name)
  }

  class CatPrinter extends Printer[Cat] {
    def print(cat: Cat): Unit =
      println("The cat's name is: " + cat.name)
  }

  object ContravarianceTest extends App {
    val myCat: Cat = Cat("Boots")

    def printMyCat(printer: Printer[Cat]): Unit = {
      printer.print(myCat)
    }

    val catPrinter: Printer[Cat] = new CatPrinter
    val animalPrinter: Printer[Animal] = new AnimalPrinter

    printMyCat(catPrinter)
    printMyCat(animalPrinter)
  }

  // Invariance
  class Container[A](value: A) {
    private var _value: A = value
    def getValue: A = _value
    def setValue(value: A): Unit = {
      _value = value
    }
  }

  val catContainer: Container[Cat] = new Container(Cat("Felix"))
  val animalContainer: Container[Animal] = catContainer
  animalContainer.setValue(Dog("Spot"))
  val cat: Cat = catContainer.getValue // Oops, we'd end up with a Dog assigned to a Cat

}
