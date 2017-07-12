package simple

import java.awt.event.ActionEvent
import java.io.File

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2017/7/6.
  */
object day03 {

}

trait Listener[E] {
  def occurred(e : E) : Unit
}
trait Source[E, L <: Listener[E]] {

  private val listeners = new ArrayBuffer[L]()

  def add(l : L) : Unit = {listeners += l}

  def remove(l : L) : Unit = {listeners -= l}

  //触发
  def fire(e : E) : Unit = {
    for(l <- listeners) l.occurred(e)
  }
}
trait ActionListener extends Listener[ActionEvent] {}
class Button extends Source[ActionEvent, ActionListener] {

  def click() : Unit = {
    fire(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "click"))
  }
}


object RichFile {
  //隐式的对象转换
  implicit def RichFile (from : File) = new RichFile(from)
  //隐式的参数转换
  case class Deli (left : String, right : String)

  def quote(who : String) (implicit deli : Deli): Unit ={
    println(deli.left + who + deli.right)
  }
  //隐式的泛型转换
  def smaller[T] (a : T, b : T) (implicit order : T => Ordered[T])
  = if (a < b) a else b

  def smallerM[T <: Ordered[T]] (a : T , b : T) = if( a < b) b else a


  def main(args: Array[String]): Unit = {
    //    import RichFile._
    //    val f = new File("")
    //    f.read
    quote("aaa") (Deli("<<", ">>"))
    println(smaller("1", "2"))
  }

}
class RichFile(val from : File) {
  def read = scala.io.Source.fromFile(from).mkString
}

class Pair[T : Ordering] (val first : T, val second : T){
  //内部默认存在一个Ordering[T]
  def smaller (implicit order : Ordering[T])
   = if(order.compare(first, second) < 0) first else second
}
object Pair {
  def main(args: Array[String]): Unit = {
    val p = new Pair[Int](1, 2)
    println(p.smaller)
    val l = List(1, 2, 3)
//    l.map(_ * 2)
  }
}





