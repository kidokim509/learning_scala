/**
  * https://docs.scala-lang.org/tour/lower-type-bounds.html
  */
object Chap17LowerTypeBound extends App {
  // While upper type bounds limit a type to a subtype of another type, lower type bounds declare a type to be a supertype of another type.

  /*
   The class Node and its subtypes are covariant because we have +B.
   However, this program does not compile because the parameter elem in prepend is of type B, which we declared covariant.
   This doesn’t work because functions are contravariant in their parameter types and covariant in their result types.

   To fix this, we need to flip the variance of the type of the parameter elem in prepend.
   We do this by introducing a new type parameter U that has B as a lower type bound.
   */
  /*
  trait Node[+B] {
    def prepend(elem: B): Node[B]
  }

  case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
    def prepend(elem: B): ListNode[B] = ListNode(elem, this)
    def head: B = h
    def tail: Node[B] = t
  }

  case class Nil[+B]() extends Node[B] {
    def prepend(elem: B): ListNode[B] = ListNode(elem, this)
  }*/

  trait Node[+B] {
    def prepend[U >: B](elem: U): Node[U]
  }

  case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {
    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
    def head: B = h
    def tail: Node[B] = t
  }

  case class Nil[+B]() extends Node[B] {
    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
  }

  trait Bird
  case class AfricanSwallow() extends Bird
  case class EuropeanSwallow() extends Bird


  val africanSwallowList= ListNode[AfricanSwallow](AfricanSwallow(), Nil())
  val birdList: Node[Bird] = africanSwallowList
  birdList.prepend(new EuropeanSwallow)
}
