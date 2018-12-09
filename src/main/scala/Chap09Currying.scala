/**
  * https://docs.scala-lang.org/tour/multiple-parameter-lists.html
  * https://docs.scala-lang.org/ko/tutorials/tour/currying.html.html
  */
object Chap09Currying extends App {

  // 메소드에는 파라미터 목록을 여럿 정의할 수 있다.
  // 파라미터 목록의 수 보다 적은 파라미터로 메소드가 호출되면, 해당 함수는 누락된 파라미터 목록을 인수로 받는 새로운 함수를 만든다.

  def filter(xs: List[Int], p: Int => Boolean): List[Int] =
    if (xs.isEmpty) xs
    else if (p(xs.head)) xs.head :: filter(xs.tail, p)
    else filter(xs.tail, p)

  def modN(n: Int)(x: Int) = ((x % n) == 0)

  val nums = List(1, 2, 3, 4, 5, 6, 7, 8)

  println(filter(nums, modN(2)))
  println(filter(nums, modN(3)))

  // modN(4)
  // Error:(22, 7) missing argument list for method modN in object Chap09Currying
  // Unapplied methods are only converted to functions when a function type is expected.
  // You can make this conversion explicit by writing `modN _` or `modN(_)(_)` instead of `modN`.

}
