object Chap01Basics {
  /**
    * 표현식
    */
  println(1) // 1
  println(1 + 1) // 2
  println("Hello!") // Hello!
  println("Hello," + " world!") // Hello, world!

  /**
    * 값
    */
  val x = 1 + 1
  println(x) // 2
  // x = 3 // This does not compile. val은 재할당 불가 immutable.

  val y: Int = 1 + 1 // 타입추론도 되지만, 명식적 지정도 가능

  /**
    * 변수
    */
  var x1 = 1 + 1
  x1 = 3 // This compiles because "x" is declared with the "var" keyword.
  println(x1 * x1) // 9

  var y1: Int = 1 + 1

  /**
    * 블록
    */
  println({
    val x = 1 + 1
    x + 1
  }) // 3

  /**
    * 함수
    */
  (x: Int) => x + 1 // 익명 함수 => 을 기준으로 왼쪽에는 매개변수 목록이고 오른쪽은 매개변수를 포함한 표현식

  // 익명 함수에 이름 붙여주기
  val addOne = (x: Int) => x + 1
  println(addOne(1)) // 2

  // 매개변수가 여러개인 함수
  val add = (x: Int, y: Int) => x + y
  println(add(1, 2)) // 3

  // 매개변수가 없는 함수
  val getTheAnswer = () => 42
  println(getTheAnswer()) // 42

  /**
    * 메소드
    * 함수와 차이: def 키워드로 메소드를 정의하고 이름, 매개변수 목록, 반환 타입 그리고 본문이 뒤따른다.
    */
  def addMethod(x: Int, y: Int): Int = x + y
  println(addMethod(1, 2)) // 3

  // 매개변수가 여러개인 함수
  def addThenMultiply(x: Int, y: Int, multiplier: Int): Int = (x + y) * multiplier
  println(addThenMultiply(1, 2, 3)) // 9

  // 이렇게 매개변수를 여러개의 ()로 나누어 입력 받을 수도 있음
  def addThenMultiply2(x: Int, y: Int)(multiplier: Int): Int = (x + y) * multiplier
  println(addThenMultiply2(1, 2)(3)) // 9

  // 매개변수가 없는 메소드
  def name: String = System.getProperty("user.name")
  println("Hello, " + name + "!")

  // 표현식이 여러줄로 되어있는 메소드
  def getSquareString(input: Double): String = {
    val square = input * input
    square.toString // return은 사용하지 않고 생략
  }

  /**
    *
    * @param args
    */
  def main(args: Array[String]): Unit =
    println("Hello, Scala developer!")
}
