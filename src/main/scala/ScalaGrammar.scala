object ScalaGrammar {
  def max(x: Int, y: Int): Int = {
    if (x > y) {
      x
    }
    else {
      y
    }
  }

  def max2(x: Int, y: Int) = if (x > y) x else y

  def main(args: Array[String]) = {
    // Oh! very first line of a code
    println("Hello World!")

    // Function Literal
    args.foreach(arg => println(arg))
    args.foreach(println(_))
    args.foreach(println)

    // Function
    val res0 = max(10, 20)

    // Array
    val greetStrings: Array[String] = new Array[String](3)
    greetStrings(0) = "Hello"
    greetStrings(1) = " "
    greetStrings(2) = "World!"
    // same as: update method
    greetStrings.update(0, "Hello")
    greetStrings.update(1, " ")
    greetStrings.update(2, "World!")
    // sams as: companion object's factory method
    val greetStrings2 = Array("Hello", " ", "World!")
    val greetStrings3 = Array.apply("Hello", " ", "World!")
    // access
    greetStrings(0)

    // List
    val oneTwoThree = List(1, 2, 3)
    val oneTwo = List(1, 2)
    val threeFour = List(3, 4)
    // concat two lists
    val oneTwoThreeFour = oneTwo ::: threeFour
    // cons: append an element at the head
    val zeroOneTwo = 0 :: oneTwo
    // 일반적으로 연산자는 연산자 좌측항의 메소드이나, :으로 끝나는 연산자는 연산자 우측항의 메소드임
    val zeroOneTwo2 = oneTwo.::(0)
    val zeroOneTwo3 = 0 :: 1 :: 2 :: Nil
    // access
    oneTwoThreeFour(0)
  }
}