/**
  * https://docs.scala-lang.org/tour/implicit-conversions.html
  * https://stackoverflow.com/questions/2861501/can-someone-explain-me-implicit-conversions-in-scala
  */
object Chap23ImplicitConversion extends App {
  // define a class
  case class Person(firstName: String, lastName: String)

  // must import this to enable implicit conversions
  import scala.language.implicitConversions

  // define the implicit conversion. String to Person in this case
  implicit def stringToPerson(name:String) = {
    val fields = name.split(" ");
    Person(fields(0), fields(1))
  }

  /*
  implicit def fakeStringToPerson(name:String) = {
    Person("hello", "world")
  }*/

  // method using the implicit conversion
  def getPerson(fullName:String): Person = fullName

  val fooBar = getPerson("foo bar")
  println(fooBar.getClass())  // class Person
  println(fooBar.firstName)  // foo
  println(fooBar.lastName)  // bar

  // scala에서 implicit이 무엇인지 단적으로 보여주는 예
  // "full name"이라는 String에는 firstName이라는 member field가 없다.
  // 그러나 implicit def stringToPerson이 정의되어 있기 떄문에 (implicit function for string => Person)
  // 컴파일러가 이걸 찾아내서는 암시적으로 string을 Person으로 변환해서 사용
  println("full name".firstName) // full

  // 만일 위 코드에서 fakeStringToPerson을 주석 해제하고 나면
  // 위 35번 라인 코드는 컴파일 에러가 발생한다.
  // 왜냐하면 string => Person을 변환하는 implicit function이 여러개가 있어 컴파일러가 무엇을 사용해야 할지 모르는 상태가 되어버림.
}
