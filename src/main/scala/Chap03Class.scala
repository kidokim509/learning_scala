/**
  * https://docs.scala-lang.org/tour/classes.html
  */
object Chap03Class extends App {

  /**
    * 기본 생성자만 있는 클래스
    */
  class User
  val user1 = new User

  /**
    * scala에서 primary 생성자는 클래스 signature에 포함된다.
    */
  class Point(xc: Int, yc: Int)  {
    var x = xc
    var y = yc

    // secondary 생성자
    def this(xc: Int, yc: Int, zc: Int) {
      this(xc, yc)
      print(zc)
    }

    def move(dx: Int, dy: Int): Unit = {
      x = x + dx
      y = y + dy
    }

    override def toString: String = "(" + xc + ", " + yc + ")"
  }

  val pt = new Point(1, 2)
  println(pt)
  pt.move(10, 10)
  println(pt)


  class OtherPoint(var x: Int, var y: Int) {
    def move(dx: Int, dy: Int): Unit = {
      x = x + dx
      y = y + dy
    }

    override def toString: String =
      s"($x, $y)" // StringContext, The simple string interpolator. s"..."로 문자열 선언. 변수부분은 $변수명으로 표시
  }

  val point1 = new OtherPoint(2, 3)
  point1.x  // 2
  println(point1)  // prints (2, 3)

  /**
    * 생성자의 파라미터에 기본 값 정의
    */
  class YetAnotherPoint(var x: Int = 0, var y: Int = 0)

  val origin = new YetAnotherPoint  // x and y are both set to 0
  val point2 = new YetAnotherPoint(1)
  println(point2.x)  // prints 1

  val point3 = new YetAnotherPoint(y=2)
  println(point3.y)  // prints 2


  /**
    * Private Members and Getter/Setter Syntax
    * 참고: https://www.dustinmartin.net/getters-and-setters-in-scala/
    */
  class PrivatePoint {
    private var _x = 0
    private var _y = 0
    private val bound = 100

    // Getter
    def x = _x

    // Setter
    // Notice the special syntax for the setters: the method has _= appended to the identifier of the getter and the parameters come after.
    def x_= (newValue: Int): Unit = {
      if (newValue < bound) _x = newValue else printWarning
    }

    def y = _y
    def y_= (newValue: Int): Unit = {
      if (newValue < bound) _y = newValue else printWarning
    }

    private def printWarning = println("WARNING: Out of bounds")
  }

  val point4 = new PrivatePoint
  point4.x = 99  // point4.x = (99), point4.x_=(99)
  point4.y = 101 // prints the warning

  // 사실 y_=이 메소드명임. =로 assign을 하는 것 같은 trick을 쓴 것.
  point4.y_=(101)
  println(point4.y)

  /**
    * https://www.scala-lang.org/files/archive/spec/2.11/04-basic-declarations-and-definitions.html#variable-declarations-and-definitions
    *
    * A variable declaration var x: T is equivalent to the declarations of both a getter function x and a setter function x_=:
    *  def x: T
    *  def x_= (y: T): Unit
    */
  class TimeOfDayVar {
    private var h: Int = 0
    private var m: Int = 0
    private var s: Int = 0

    def hours              =  h
    def hours_= (h: Int)   =  if (0 <= h && h < 24) this.h = h else throw new RuntimeException()

    def minutes            =  m
    def minutes_= (m: Int) =  if (0 <= m && m < 60) this.m = m else throw new RuntimeException()

    def seconds            =  s
    def seconds_= (s: Int) =  if (0 <= s && s < 60) this.s = s else throw new RuntimeException()

  }
  val d = new TimeOfDayVar
  d.hours = 8; d.minutes = 30; d.seconds = 0
  d.hours = 25

}