object Currying extends App {
  def foldLeft[B](z: B)(op: (B, A) => B): B

}
