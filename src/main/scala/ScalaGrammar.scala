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
    println("Hello World!")

    args.foreach(arg => println(arg))
    args.foreach(println(_))
    args.foreach(println)

    val res0 = max(10, 20)


  }
}