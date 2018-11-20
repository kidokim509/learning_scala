/**
  * https://docs.scala-lang.org/ko/tutorials/tour/unified-types.html
  *
  * 스칼라에서 scala.Any가 모든 클래스의 부모 클래스임. 이를 상속하는 scala.AnyVal과 scala.AnyRef가 파생.
  * 스칼라에서는 모든 것이 객체이며 자바의 원시타입에 해당하는 값 클래스들이 scala.AnyVal을 상속 받는다.
  * (예: scala.Int, scala.Double 등)
  * scala.AnyRef는 나머지 모든 클래스들의 부모이다. 자바에서 java.lang.Object에 해당한다.
  */
object Chap01UnifiedTypes extends App {

  val set = new scala.collection.mutable.LinkedHashSet[Any]
  set += "This is a string"  // 문자열을 추가한다
  set += 732                 // 숫자를 추가한다
  set += 'c'                 // 캐릭터를 추가한다
  set += true                // 불리언 값을 추가한다
  set += main _              // 메인 함수를 추가한다

  val iter: Iterator[Any] = set.iterator
  while (iter.hasNext) {
    println(iter.next.toString())
  }
}
