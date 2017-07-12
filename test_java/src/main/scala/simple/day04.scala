package simple

import java.io.FileNotFoundException

import scala.collection.mutable.ArrayBuffer
import scala.reflect.internal.util.StringOps

/**
  * Created by Administrator on 2017/7/6.
  */
class day04 {
  val a : Int = 0

}
object day04 {

  def apply: day04 = new day04()
  
  def main(args: Array[String]): Unit = {

    println(1.toString)
    println(1.to(10).toArray)//RichInt.scala
    println("hello".intersect("world"))//隐式转换SeqLike.scala

    var a : Int = 0
    a += 1
    println(a)
    println(a * 10)


    import scala.math._
    println(sqrt(2))
    println(min(1, 2))

    //一般来讲，没有参数并且不改变当前对象的方法没有参数

    println("afdasfdsa"(2))

    println(testIf(0))
    println(testReturn(0))
//    println(read)
    for(a <- 0 to 10) print(a)
    println
    for(b <- 0 until 10) print(b)
    println
    println(decorate("a"))
    println(sum(1, 2, 3, 4, 5))
    println(sum(1 to 10 : _*))//将Range转换为参数序列处理


//    testArray
    testMap
  }

  def testIf (a : Int) = if(a > 10 ) a else ()
  def testReturn(a : Int) = {
    0
  }
  def read = scala.io.StdIn.readInt()

  def decorate(prefix : String, content : String = "<<<", suffix : String = ">>>") = {
    content + prefix + suffix
  }

  def sum(args : Int*) = {
    var sum : Int = 0
    for(a <- args) sum += a
    sum
  }

  def testLazy() = {
    val words = scala.io.Source.fromFile("").mkString //在定义时执行
    lazy val words1 = scala.io.Source.fromFile("").mkString//在第一次使用时执行
    def words2 = scala.io.Source.fromFile("").mkString//每次使用时执行
  }

  def testException() = {
    try{
      val words = scala.io.Source.fromFile("").mkString //在定义时执行
    }catch {
      case _:FileNotFoundException => println("找不到文件")
      case ex:IndexOutOfBoundsException => println("越界异常" + ex.getMessage)
    }
  }

  def testArray = {
    val arr = Array(1, 2, 3, 4)
    for(a <- arr) print(a)
    println
    val arr1 = ArrayBuffer("a", "b")
    println
    arr1 += "c"

    arr1 += ("c", "d", "d")

    for(b <- arr1) print(b)

    //不可变数组转换为数组缓冲
    var buf = arr.toBuffer
    //数组转换为数组缓冲
    var arr2 = arr1.toArray

    println
    var arr10 = Array(10, 2, 3, 4, 8)
    var buffer10 = ArrayBuffer("a" , "b", "c")
    var arr11 = for(a <- arr10) yield a * 2//转换为新的数组
    var buffer11 = for(b <- buffer10) yield b + "1" //转换为新的数组缓冲
    println(arr11.foreach(print))
    println
    println(buffer11.foreach(print))

    println(arr11.mkString("="))
    println(buffer11.mkString("="))

    scala.util.Sorting.quickSort(arr10)//对原数组排序

    println(arr10.mkString(","))


    var buffer = ArrayBuffer(1, 2, 3, 4)

    import scala.collection.JavaConverters.bufferAsJavaList
    import scala.collection.JavaConverters.asScalaBuffer

//    val pb = new ProcessBuilder(buffer) //scala -> java
//
//    val cmd = pb.command()//java -> scala

  }

  def testMap = {

    val map = scala.collection.immutable.Map("a" -> 1, "b" -> 2)//不可变
    println(map.mkString(","))
    val map1 = scala.collection.mutable.Map("a" -> 1, "b" -> 2)//可变
    map1 += "c" -> 10
    println(map1.mkString(","))
    for ((k ,v) <- map1) yield {k + "a"}
    println(map1.mkString("&"))

    val t = (1, "a", 10L)
    println(t._1)
    println(t._2)
    println(t._3)

    val arr = Array(1, 2, 3)
    val arr1 = Array("a", "b", "c")
    var arr2 = arr.zip(arr1)
    println(arr2.mkString(","))//元组
  }
}
class NetWork (var name : String){outer =>

  class Member (var name : String){
    def desc = println(name + "," + outer.name)
  }

}
class People1 {
  private[this] var name  = "People1 class"

  def desc = {

  }
}
object People1 {
  var name = "People1 object"

  def desc = {
    println(name)
  }

  def main(args: Array[String]): Unit = {
    desc
  }

}
object TrafficLightColor extends Enumeration {
  val RED, GREEN, YELLOW = Value  //枚举类型的对象是TrafficLightColor Value的对象

  def main(args: Array[String]): Unit = {
    println(TrafficLightColor.RED.id)
  }
}
package com{
  package horst{
    object utils {
      def log = println("log")
    }
  }
}
package com{
  package horst{
    object test {
      def test = utils.log
      def main(args: Array[String]): Unit = {
        test
      }
    }
  }
}
package test1{

  import java.awt.event.ActionEvent
  import javax.swing.JButton

  trait People {
    def log = {println("aaa")}
  }
  class People1 extends People {

  }
  object People {
    def main(args: Array[String]): Unit = {
      val p = new People1
      p.log
    }
  }

  trait Logged {
    def log : Unit
  }
  class FileLogged extends Logged{
    override def log: Unit = {
      println("file")
    }
  }
  class ConsoleLogged extends Logged {
    override def log: Unit = {
      println("console")
    }
  }

  trait ConsoleLogged1 extends Logged {

    val t : Int = -10

    override def log: Unit = {
      println("console")
    }
  }

  trait FileLogged1 extends Logged {

    val t : Int = 10

    override def log: Unit = {
      println("file")
    }
  }

  object Logged {
    def main(args: Array[String]): Unit = {
      val file = new FileLogged
      val console = new ConsoleLogged
      file.log
      console.log

//      val f = new FileLogged with ConsoleLogged1 with FileLogged1
//      f.log
//      println(f.t)
    }
  }

  case class Currency (value : Double, unit : String)
  object Currency{
    def main(args: Array[String]): Unit = {
      val c = Currency(100.0, "CHN")
//      case Currency(amount, "CHN") => println("$" + amount)
    }
  }

  object testFunction {
    def main(args: Array[String]): Unit = {

//      val a = Array(1, 2, 3, 4).map((a : Int) => a * 2)
//      println(a.mkString(","))
//
//      val b = Array(1, 2, 3, 4).map(a => a * 2)
//      println(b.mkString(","))
//
//      val c = Array(1, 2, 3, 4).map(_ * 2)
//      println(c.mkString(","))
//
//      val m1 = multiBy(1.0)
//      val m2 = multiBy(2.0)
//      println(m1(10) + "," + m2(10))
//
//      val c1 = Array("hello", "world")
//      val c2 = Array("hello", "world")
//
//      c1.corresponds[String](c2)(_.equalsIgnoreCase(_))
//
//      runInThread{() => println("a") ;println("b")}
//
//      var count = 10
//      until(count > 0)(() => count -= 1)
//      println("count:" + count)


      println(numFrom(10).tail)


      println(testSwitch('a'))
      println(testSwitch('2'))



    }

    implicit def makeAction(action : ActionEvent => Unit) =
    new ActionListener {
      override def occurred(e: ActionEvent): Unit = {
        action(e)
      }
    }

    def multiBy(a : Double) = (b : Int) => b * a


    def testListener = {
      val button = new JButton()
//      button.addActionListener(new ActionListener {
//        override def occurred(e: ActionEvent): Unit = {
//
//        }
//      })
//      button.addActionListener((e : ActionEvent) => println())
    }

    def runInThread(a : () => Unit): Unit ={
      new Thread(new Runnable {
        override def run(): Unit = {
          a()
        }
      }).start()
    }

    def until (condition : => Boolean) (action : () => Unit): Unit ={
      if(condition){
//        action
//        until(condition) (action)
      }
    }

    def numFrom(n : BigInt) : Stream[BigInt] = n #:: numFrom(n + 1)


    def testSwitch(a : Char) = {
      val b = a match {
        case 'A' => 0
        case 'B' => 1
        case 'C' => 2
        case _ if Character.isDigit(a) => 3
        case ch => ch + 'B'
      }
      b
    }

    def typeMatch(a : AnyVal) = {
      a match {
        case x : Int => "int:" + x
        case y : Double => "double:" + y
        case z : Long => "long:" + z
        case _ => 4
      }
    }

  }

  abstract class Account {
     def log
  }

  case class Dollar(a : String) extends Account{
    override def log = {

    }
  }

  case class Currency1(a : String, b : String) extends Account {
    override def log : Unit= {

    }
  }

  object Account {
    def main(args: Array[String]): Unit = {
      val a = Dollar("lxl")
      testMatch(a)
      val b = Currency1("a" ,"b")
      testMatch(b)
//      smaller(1, 2)
      println(smaller2("a", "b"))


      val p1 = new Pair[Papper]

      val p2 = new Pair[Papper1]

      pair(p1)
      pair(p2)






    }


    def smaller [T <: Ordered[T]] (a : T, b : T) = {
      if( a < b ) println(b) else println(a)
    }

    def smaller1[T <: Comparable[T]] (a : T, b : T): Int ={
      if(a.compareTo(b) < 0) 1 else -1
    }
    //隐式转换
    def smaller2[T <% Ordered[T]] (a : T, b : T) = {
      if(a < b) a else a
    }

    def testMatch(a : Account) = {
        val b = a match {
          case Dollar(a) => println(a)
          case Currency1(a, b) => println(a + "," + b)
          case _ => println("nothing")
        }
    }

    def pair(t : Pair[Papper]) = {

    }

  }


  trait Friend [T]{
    def beFriend(someone:T)
  }


  class Pair[+T]{

  }

  class Papper{

  }
  class Papper1 extends Papper {

  }
}

package testT {

  import java.io.File

  trait Friend[-T]{
    def beFriend(someone : T): Unit = {
      println("befriend")
    }
  }

  class People extends Friend[People]{}
  class Student extends People{}



  object test{
    def main(args: Array[String]): Unit = {

      val susan = new Student
      val fred = new People

      makeFriendWith(susan, fred)
    }
    def makeFriendWith(s : Student, f : Friend[Student]) = {
      f.beFriend(s)
    }
  }



  class Documnet{
    def addTitle : this.type  = {
      this
    }
    def addContent : this.type  = {
      this
    }
  }

  class FileDocument extends Documnet{
    def addAuthor : this.type = {
      this
    }
  }
  object FileDocument {
    def main(args: Array[String]): Unit = {
      val d = new Documnet
      d.addContent.addTitle
      val t = new FileDocument
      t.addAuthor.addContent
    }
  }

  class Network {

    class Member {

    }

    def join = {
      new Member
    }

  }
  object Network {
    implicit def file2RichFile (from : File) = new RichFile(from)
    def main(args: Array[String]): Unit = {

      val t = new Network
      var m = t.join
      val t1 = new Network
      var m1 = t1.join
      var arr = ArrayBuffer[Network # Member](m)
      arr += m1

      val f = new File("")
      f.read

      println(smaller(1, 2))

      Array(1, 2, 3, 4).map(_* 2)

    }

    def smaller [T]( a : T, b : T)(implicit order : T => Ordered[T]) = {
      if(order(a) < b) a else b
    }


  }

  class RichFile (file : File) {
    def read = println(file.getAbsoluteFile.getName)
  }















}

