package simple
package fun1


sealed trait List[+A]
case object Nil extends List[Nothing]
case class Constru[+A](header : A, tail : List[A]) extends List[A]


object List{
  def main(args: Array[String]): Unit = {
    val data = List(0, 1, 2, 3)
    println(sum(data))
    println(findFirst(Array("1", "2", "3", "4"), _ == "2"))
    println(tail(List(1, 2, 3, 4)))
    println(setHeader(List(1, 2, 3, 4), 10))

    println(dropWhile(List(1, 2, 3, 4), (a : Int) => a == 2))


    println(foldRight2(List(1, 2, 3, 4), 0)((a : Int, b : Int) => a + b))
    println(foldRight2(List(0.1, 0.2, 0.3), 0.0)((a : Double, b : Double) => a * b))

  }

  def sum[A](list : List[Int]) : Int = {
      list match {
        case Nil => 0
        case Constru(h, l) => h + sum(l)
      }
  }

  def foldRight[A, B](list : List[A], z : B, p : (A, B) => B) : B = {
    list match {
      case Nil => z
      case Constru(x, xa) => p(x, foldRight(xa, z, p))
    }
  }
  def foldRight2[A, B](list : List[A], z : B)(p : (A, B) => B) : B = {
    list match {
      case Nil => z
      case Constru(header, tail) => p(header, foldRight2(tail, z)(p))
    }
  }

  def product[A](list : List[Double]) : Double = {
    list match {
      case Nil => 0.0
      case Constru(x, xa) => x * product(xa)
    }
  }

  def apply[A](a : A*) : List[A] = {
    if(a.length < 1) Nil
    else Constru(a.head, apply(a.tail:_*))
  }

  def findFirst[A](list : Array[String], h : String => Boolean) = {
    def loop(n : Int) : Int = {
      if(n >= list.length) -1
      else if(h(list(n))) n
      else loop(n + 1)
    }
    loop(0)
  }

  def tail[A](list : List[A]) = {
    val t = list match {
      case Nil => Nil
      case Constru(x, xa) => xa
    }
    t
  }

  def setHeader[A](list : List[A], header : A) = {
    val t = list match {
      case Nil => Nil
      case Constru(x, xa) => Constru(header, xa)
    }
    t
  }

  def dropWhile[A](list : List[A], h : A => Boolean) : List[A]= {
    val t = list match {
      case Nil => Nil
      case Constru(x, xa) if h(x) => dropWhile(xa, h)
      case _ => list
    }
    t
  }
}

sealed trait Tree[+A]
case class Leaf[A](value : A) extends Tree[A]
case class  Node[A](left : Tree[A], right : Tree[A]) extends Tree[A]

object Tree{
  def main(args: Array[String]): Unit = {

    println(size(Node(Node(Leaf(1), Leaf(2)), Node(Leaf(3), Leaf(4)))))

    println(map(Node(Node(Leaf(1), Leaf(2)), Node(Leaf(3), Leaf(4))))((a : Int) => a + 1))

    println(maxValue(Node(Node(Leaf(1), Leaf(2)), Node(Leaf(3), Leaf(4)))))

    mapViaFold(Node(Node(Leaf(1), Leaf(2)), Node(Leaf(3), Leaf(4))))((a : Int) => a + 1)


  }

  def mapViaFold[A, B](tree : Tree[A])(f : A => B) : Tree[B] = {
    foldRight(tree)(a => Leaf(f(a)) : Tree[B])(Node(_, _))
  }


  def size[A](tree : Tree[A]) : Int = {
    tree match {
      case Leaf(_) => 1
      case Node(l, r) => 1 + size(l) + size(r)
    }
  }

  def depth[A] (tree : Tree[A]) : Int = tree match {
    case Leaf(_) => 1
    case Node(l, r) => 1 +  (depth(l) max depth(r))
  }

  def maxValue[A](tree : Tree[Int]) : Int = tree match {
    case Leaf(a) => a
    case Node(l, r) => maxValue(l) max maxValue(r)
  }

  def map[A](tree : Tree[A])(p : A => A) : Tree[A] = tree match {
    case Leaf(a) => Leaf(p(a))
    case Node(l, r) => Node(map(l)(p), map(r)(p))
  }

  def foldRight[A, B](tree : Tree[A])(f : A => B)(p : (B, B) => B) : B = tree match {
    case Leaf(a) => f(a)
    case Node(l, r) => p(foldRight(l)(f)(p), foldRight(r)(f)(p))
  }



}

object test {
  def main(args: Array[String]): Unit = {
//    fall
    println(fail)
  }

  def fall = {
    val y : Int = throw new Exception("fail")
    try{
        val x = 10 + y

    } catch {
      case e : Exception => 43
    }
  }

  def fail = {
    try{
      val x = 10 + ((throw new Exception("fail")) : Int)
    }catch {
      case a : Exception => 43
    }
  }
}

sealed trait Option[+A] {
  def map[B](f : A => B) : Option[B] = {
    this match {
      case None => None
      case Some(a) => Some(f(a))
    }
  }
  def flatMap[B](f : A => Option[B]) : Option[B] = {
    map(f).getOrElse(None)
  }
  def getOrElse[B >: A](default: => B) : B = {
    this match {
      case None => default
      case Some(v) => v
    }
  }
  def orElse[B >: A](ob: => Option[B]) : Option[B] = {
    map(Some(_)).getOrElse(ob)
  }
  def filter(f : A => Boolean) : Option[A] = {
    this match {
      case Some(v) if (f(v)) => this
      case _ => None
    }
  }






}
case object None extends Option[Nothing]
case class Some[+A](get : A) extends Option[A]
object Option {
  def main(args: Array[String]): Unit = {
    val some = Some("hello")
    val some2 = Some("world")
    println(some.map((a : String) => a + "a"))

    println(some.getOrElse("aa"))

    println(map2(some, some2)((a, b) => a + "," + b))

  }

  def map2[A, B, C](a : Option[A], b : Option[B])(f : (A, B) => C) : Option[C] = {
    a.flatMap(aa => b.map(bb => f(aa, bb)))
  }

}
sealed trait Either[+A, +B] {

}
case class Left[+A](a : A) extends Either[A, Nothing]
case class Right[+B](b : B) extends Either[Nothing, B]

object Either{
  def main(args: Array[String]): Unit = {
    println(mean(Array.emptyIntArray))
  }
  def mean[T](arr : Array[T]) : Either[String, Int] = {
    if(arr.length == 0) Left("出错了")
    else Right(arr.length)
  }
  def Try[A](a : => A) : Either[Exception, A] = {
    try Right(a)
    catch {
      case e : Exception => Left(e)
    }
  }
  def mean2[T](arr : Array[T]) : Either[Exception, Int] = {
    if(arr.length == 0) Left(new Exception(""))
    else Right(arr.length)
  }
}





