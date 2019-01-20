/**
  * https://docs.scala-lang.org/tour/implicit-parameters.html
  */
object Chap22ImplicitParameters extends App {
  abstract class Monoid[A] {
    def add(x: A, y: A): A
    def unit: A
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def add(x: String, y: String): String = x concat y
    def unit: String = ""
  }

  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def add(x: Int, y: Int): Int = x + y
    def unit: Int = 0
  }

  def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
    if (xs.isEmpty) m.unit
    else m.add(xs.head, sum(xs.tail))


  /*
  sum() 메소드를 호출 할 때 implicit parameter를 직접 넘기지는 않는다
  말 그래도 암시적 파라미터는 함수를 호출할 때에 직접 넘기지 않아도 알아서 생기는 파라미터이다.
  caller가 만들어 넘기지 않아도 코드 안에서 찾아낸다. implicit으로 선언된 것 중에서..
  스칼라는 암시적 파라미터를 생성하기 위한 (찾아내기 위한) 규칙이 있는데 다음과 같다.
  1) Scala will first look for implicit definitions and implicit parameters that can be accessed directly
     (without a prefix) at the point the method with the implicit parameter block is called.
  2) Then it looks for members marked implicit in all the companion objects associated with the implicit candidate type.

  여러가지 응용 케이스가 있을 수 있겠으나, 파라미터에 default 값을 주고, optional하게 입력을 받고 싶을 때 응용할 수 있겠다.
   */
  println(sum(List(1, 2, 3)))       // uses intMonoid implicitly
  println(sum(List("a", "b", "c"))) // uses stringMonoid implicitly
}
