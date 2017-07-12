package simple

/**
  * Created by Administrator on 2017/7/10.
  */
import Stream._
sealed trait Stream[+A]{

  def empty[A] = Empty

  def headOption : Option[A] = {
    println("do .............")
    this match {
      case Empty => None
      case Cons(f, p) => Some(f())
    }
  }

  def toList : List[A] = {

    def go(stream : Stream[A], list : List[A]) : List[A] = {
      stream match {
        case Cons(h, p) => go(p(), h() :: list)
        case _ => list
      }
    }
    go(this, List.empty).reverse
  }

  def take(n : Int) : Stream[A] = {
    this match {
      case Cons(h, p) if (n > 1) => cons(() => h(), () => p().take(n - 1))
      case Cons(h, _) if (n == 1) => cons(() => h(), () => empty)
      case _ => empty
    }
  }

  def drop(n : Int) : Stream[A] = {
    this match {
      case Cons(_, p) if (n > 0) => p().drop(n - 1)
      case _ => this
    }
  }




}
case object Empty extends Stream[Nothing]
case class Cons[+A](f : () => A, p : () => Stream[A]) extends Stream[A]

object Stream{

  val ones : Stream[Int] = Stream.cons(() => 1, () => ones)

  def main(args: Array[String]): Unit = {
    val s = Stream(1, 2, 3, 4, 5)
    println(s.headOption)

    val s1 = apply2(1, 2, 3, 4, 5)
    println(s1.headOption)
    println(s1.headOption)

    println(s1.toList)

    println(ones.take(10).toList)


  }

  def constant(n : Int): Stream[Int] = {
    cons(() => n, () => constant(n + 1))
  }

  def cons[A](f : () => A, p : () => Stream[A]) : Stream[A] = {
    lazy val a = f;
    lazy val b = p;
    Cons(a, b)
  }

  def apply2[A](a : A*) : Stream[A] = {
    if(a.length == 0) Empty
    else cons(() => a.head, () => apply(a.tail: _*))
  }


  def apply[A](a : A*) : Stream[A] = {
    if(a.length == 0) Empty
    else Cons(() => a.head, () => apply(a.tail: _*))
  }


}
