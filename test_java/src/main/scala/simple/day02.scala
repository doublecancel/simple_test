package simple

import java.awt.image.BufferedImage
import java.io.FileNotFoundException
import javax.imageio.ImageIO
import javax.swing.JFrame

import scala.beans.BeanProperty
import scala.collection.SortedSet

/**
  * Created by Administrator on 2017/7/5.
  */
//类
//构造器使用默认值，防止过多的构造器
class day02(var id : Long, var name : String, var age : Int = 10) {

  //在创建对象时会执行所有语句
  println("执行了吗？")



  //字段需要初始化
//  var id : Long = 0L
//  var name : String = ""
//  var age : Int = 0
////  def increment = {count += 1}


//  def this(id : Long, name : String, age : Int){
//    this()//调用主构造器
//    this.id = id
//    this.name = name
//    this.age = age
//  }




}
//伴生类
object day02 {

  def apply(id : Long, name : String): day02 = {
    new day02(id, name)
  }




  def main(args: Array[String]): Unit = {
    val d = new day02(1L, "lxl")
    var d1 = day02(1L, "a")
    println(d1.age)
  }
}

abstract class UndoableAction(desc : String){
  def undo() = Unit
  def redo() = Unit
}

object NothingAction extends UndoableAction ("nothing action"){

  override def redo(): _root_.scala.Unit.type = super.redo()

  override def undo(): _root_.scala.Unit.type = super.undo()

  def main(args: Array[String]): Unit = {
    var a = Array(1, 2, 4, 3)
    println(a(1))
  }
}
object Hello extends App {
  println("hello")
}
//枚举对象，是Value的对象
object trafficLightColor extends Enumeration with App{
  var  GREEN, YELLOW = Value

  var RED = Value("hhh")


//  println(trafficLightColor.RED)
  println(trafficLightColor.withName("hhh"))

}
package simple{
  package test1 {
    package Test2{
      class Employee{

      }
      object Employee{
        def printSelf = {
          println("i am a employee")

        }
      }
    }

  }
}

package simple{
  package test1 {
    class People {
      val a : String = "aaa"
    }
  }
}

package simple{
  package test1 {
    package Test2 {
      object test {
        def main(args: Array[String]): Unit = {
          Employee.printSelf
        }
      }
    }

  }
}

object test {
  def main(args: Array[String]) : Unit = {
    simple.test1.Test2.Employee.printSelf
  }
}

class People {
  override def toString: String = "people"
  def test(a : Any) : Unit = {
  }
}

class creature {
  val range : Int = 10
  val env : Array[Int] =  new Array[Int](range)

  override def equals(obj: scala.Any): Boolean = {
    super.equals(obj)
  }

  override def hashCode(): Int = {
    super.hashCode()
  }


}
class Ant extends creature {
  override val range: Int = 12
}
object who {
  def main(args: Array[String]): Unit = {
    var a = new Ant
    println(a.range)
    println(a.env.length)
  }

  def in(a : AnyRef) : Unit = {
//    val b = a asInstanceOf[who]
//    if(a.isInstanceOf[Ant]){
//
//    }
  }
}
import java.io.File
object filetest {

  def main(args: Array[String]): Unit = {
//    test1
//    input



//    for(d <- dirFiles(new File("D:\\workspace"))) println(d.getName)

    pattern
  }

  def test1 : Unit = {
    var source = scala.io.Source.fromFile("D:\\workspace\\github\\simple_test\\protice\\sources\\test.txt", "UTF-8")
    var lines = source.getLines()
//    for(l <- lines) println(l)
//    var arr = source.getLines().toArray
    var string = source.mkString


//    println("arr：" + arr.mkString(","))
    println("string：" + string)
    source.close()
  }

  def input : Unit = {

    println("你的年龄是多少？")
    var age = scala.io.StdIn.readInt()
    println(age)

    var source = scala.io.Source.fromURL("http://www.baidu.com")
    println(source.mkString)

  }

  /**
    * 遍历指定目录下的所有文件
    * @param file
    * @return
    */
  def dirFiles (file : File) : Iterator[File] = {
    val children = file.listFiles(_.isDirectory)
    children.toIterator ++ children.toIterator.flatMap(dirFiles _)
  }

  def pattern = {

    var num = "[0-9]+".r
    for(a <- num.findAllIn("123lll555,,432").toArray) println(a)
    println(num.findFirstIn("fjiow9fdsa").mkString)

  }
}
//特质
//trait Logger {
//  def log(msg: String){}
//}
//trait FileLogger extends Logger{
//  override def log(msg: String): Unit = {
//    println("file:" + msg)
//  }
//}
//class ConsoleLogger extends Logger{
//  override def log(msg: String): Unit = {
//    super.log(msg)
//  }
//}
//object ConsoleLogger extends App{
//  val t = new ConsoleLogger with FileLogger
//  t.log("aaa")
//}

trait Logger{
  def log = {}
}
trait ShortLogger extends Logger{
  var maxLength = 15

  override def log: Unit = {
    println("short")
  }
}
trait LongLogger extends Logger {
//  var maxLength = 16
  override def log: Unit = {
    println("long")
  }
}
class Account {
  var balance = 0.0
}

class SavingAccount extends Account with Logger{
}

trait FileLogger extends Logger {

  Console.println("filelogger")//属于构造器的一部分

  override def log: Unit = {
    Console.print("aaaaaaa")
  }

}
object SavingAccount {

  def main(args: Array[String]): Unit = {
    val  t = new SavingAccount with ShortLogger with LongLogger with FileLogger
    t.log
  }

}
object TestOp {
  def main(args: Array[String]): Unit = {
//    test
//    fff(aaa _)
//    fff((x : Long) => x * 10)
//    fff(x => x * 100)//类型推断
//    fff(_ * 100)//终极版本，x只出现一次

//    owner()
    def a(f : Double) = (x : Double) => x * f

    val c = a(0.1)//0.1传到f，100传到x，c表示函数
    val b = a(10)
    println(c(100) + "," + b(100))

  }


  def owner() = {
    (1 to 9).map( _ * 0.1).foreach(print(_))
    println
    (0 to 10).filter(_ % 2 == 0).sortWith(_ > _).foreach(print(_))
  }

  def aaa(a : Long) = {
    a * 10
  }

  def test = {
    println("a".mkString)
    //定义一个函数
    var triple = (x : Double) => x * 10
    Console.println(triple(0.1))
    var other = can _//将方法转换为函数
    Console.println(other(10))
  }

  def fff(f : (Long) => Long) = {
    val t = f(1L)
    println(t + 10)
    t + 10
  }

  def can(x : Int) = {
    x * 10
  }

}

object iterable {

  def main(args: Array[String]): Unit = {
//    testImmu
    //不可变列表
//    val list = List(1, 2, 3, 4)
//    println(sum(list))
//    println(list.sum)

    //可变列表

//    testSet
    println(numFrom(10))

//    println(numFrom(10).toArray)


//    println(List(1, 2, 3, 4).par.sum)
//    println(List(1, 2, 3, 4).par.count(_ % 2 == 0 ))

    matcha
  }

  def matcha = {
    val a : String = "a"
    val sign = a match {
      case "+" => 1
      case "-" => -1
      case _ => 2
    }

    println(sign)

    val b : Char = '1'
    val c = b match {
      case '+' => 1
      case '-' => -1
      case _ if Character.isDigit(b) => 2
      case _ => -2
    }

    println(c)

//    val obj
//    var o = obj match {
//      case x: Int => x
//      case y: String => Integer.parseInt(y)
//      case _: BigInt => Int.MaxValue
//      case _ => 0
//    }



  }

  def testSet = {

    val set = Set(1, 2, 3, 4, 5, 6)
    set.foreach(print(_))
    println
    set.foreach(print(_))
    println
    set.foreach(print(_))
    println
    set.foreach(print(_))
    println
    var set1 = SortedSet(1, 2, 3, 4, 5)

    for(a <- set1) println(a)

  }


  /**
    * 构造一个流
    * @param n
    * @return
    */
  def numFrom(n : BigInt) : Stream[BigInt] = {
    n #:: numFrom( n + 1 )
  }


  def linkedList  = {

    val list = scala.collection.mutable.LinkedList

  }


  def testImmu = {
    val map = scala.collection.immutable.Map(1 -> "a", 2 -> "b")
//    println(map(1))
    var digits = List(1, 2, 3)
//    println(digits.head)
//    println(digits.head)
//    println(digits.tail)
//    println(digits.tail.head)
//    println(digits.tail.tail)
//    println(digits.tail.tail.head)
//    println(digits.tail.tail.tail)
    var arr = Array(1, 2, 3)
//    for(a <- arr) println(a)
//    arr.foreach(println(_))
//    for(a <- arr.toStream.filter(_ == 2).toIterator) println(a)

    digits.foreach(println(_))

  }
  def sum(list : List[Int]) : Int = {
      if(list == Nil) 0
      else list.head + sum(list.tail)
  }
}

object testXml extends App{

  //上界
  def Pair [T <: Comparable[T]] ( a : T , b : T): Unit ={
    a.compareTo(b)
  }
}
class Document {
  def setTitle(title : String) : this.type = {this}
  def setAuthor(author : String) : this.type = {this}
}
class Book extends Document {

  type Index = scala.collection.immutable.HashMap[String, (Int, Int)]
  private var sb : String = ""

  def setAdapter(adapter : String) = {this}
  //object 对象不能作为参数传入,只能转换为type
  def test(a : testXml.type) = {

  def append(str : String) : Unit = {
    sb += str
  }


  }
}
object Book {
  def main(args: Array[String]): Unit = {

    val book = new Book
    book.setAuthor("lxl").setTitle("title").setAdapter("adapter")

//    appendLines(book, Array("1", "2"))


  }
  def appendLines(target : {def append(str : String) : Unit}, lines : Iterable[String] ){
    for (a <- lines) target.append(a)
  }
}

trait Logged {
  def log(msg : String) : String
}
trait LoggedException extends Logged {
  this : Exception => //限制实现此特质的类必须是exception的子类型
  override def log(msg: String): String = {
    msg
  }
}
object LoggedException {
  def main(args: Array[String]): Unit = {
    val f = new FileNotFoundException() with LoggedException
    Console.println(f.log("aa"))
  }
}

trait Reader {
  type content
  def read(name : String) : content
}

class FileReader extends Reader {
  override type content = String
  override def read(name: String): String = {
    ""
  }
}























