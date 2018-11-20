/**
  * https://docs.scala-lang.org/ko/tutorials/tour/classes.html
  *
  * 스칼라에서는 class code가 생성자. 생성자 함수가 따로 있지는 않고 클래스 선언시 파라미터를 정의
  * (예: class Point(xc: Int, yc: Int) ...)
  *
  * secondary constructor 방법 가능
  * def this(파라미터들..)
  */
object Chap03Class extends App {

  class Point(xc: Int, yc: Int)  {
    var x: Int = xc
    var y: Int = yc

    def this(xc: Int, yc: Int, zc: Int) {
      this(xc, yc)
      print(zc)
    }

    def move(dx: Int, dy: Int): Unit = {
      x = x + dx
      y = y + dy
    }

    override def toString: String = "(" + x + ", " + y + ")"
  }

  val pt = new Point(1, 2)
  println(pt)
  pt.move(10, 10)
  println(pt)
}