import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2017/5/23.
  */
object Test {

  val a = 10;
  var b = 10;

  val c : String = "aaa"
  var d : Int = 0


  def main(args: Array[String]): Unit = {
//    println("a")
//    b = 20
//    println(1.toString())
//    println(1.to(10))//Range 1-10
//
//    println("hello".intersect("world"))//隐式的转换为StringOps
//    //
//    1.to(10)//隐式的转换为RichInt，RichDouble，RichLong 提供了int和double和long中不具备的方法
//
//    d += 1//++1

//    val a = -11
//    //if
//    val r = if(a > 0) 1 else "fff";//if可以是不同的类型
//
//    println(r)
//
//    val e = if(a > 0) 1 else ();
//    println(e)
//
//
//    //for循环
//    for (i <- 1 to 10) print(i)
//
//
//    val str = "abcdef"
//    for(i <- 1 until str.length) print(i)

//    println(fac1(1))



//    println(decorate("a", "<<<", ">>>"))

//    test()
//      remove(ArrayBuffer[Int](1, 2, -1))
    testmap()
  }



  def fac(n : Int) = {
    var r = 10;
    for(i <- 0 to 10) r += i
    r
  }

  def fac1 ( n : Int ) : Int = {//递归函数必须制定返回值
    1
  }

  def decorate ( str : String, left : String = "[", right : String = "]") : String = {//默认值的参数
    left + str + right
  }

  /**
    * 测试数组
    */
  def test () : Unit = {
    val a = new Array[String](2)//定义一个String类型的数组，初始化为0
//    println(a.length)
    a(0) = "lxl"
//    println(a(1))//赋值和取值
    val s = Array("a", "b")//初始化值不需要new，类型推倒
//    println(s.length)
    val b = new ArrayBuffer[String]()//创建一个可变数组，叫做数组缓冲
    b += "a"//末尾添加
//    println(b(0))
    b += ("b", "c", "d")//追加多个元素
//    println(b)
    b ++= Array("r", "t", "y")//追加一个数组
    b ++= a
//    println(b)
    b.trimEnd(4)//移除最后四个元素
    println(b)
    //遍历集合
//    for (i <- 0 until b.length) print(b(i) + ",")//until不包含
//    for (i <- 0 to(b.length - 1)) print(b(i) + "#")//to包含
//    for (e <- b) print(e + "&")//不需要下标
//
//    var q = for (e <- b) yield e + "0" //创建一个新的数组
//    println(q)
    val p = b.filter(_.eq("a")).map(_.concat("a"))//找到==“a”的元素并且连接“a”
    println(p)

  }

  /**
    * 移除数组的中第一个负数之外的所有负数
    * @param array
    */
  def remove(array: ArrayBuffer[Int]) : Unit = {
    var first = false
    val indexs = for (i <- 0 until(array.length) if first || array(i) >= 0) yield {
      if (array(i) < 0) first = false
      i
    }
    for(i <- indexs) println(i)
  }



  def testmap() : Unit = {

    val map = Map("lxl01" -> 10, "lxl02" -> 20, "lxl03" -> 30)//可变的map集合
    val map2 = scala.collection.immutable.Map("lxl01" -> 10, "lxl02" -> 20, "lxl03" -> 30)//不可变的map集合

    val map3 = new mutable.HashMap[String, Int]()
    println(map2("lxl01"))
    println(map2.getOrElse("lxl04", 100))//getorDefault

//    map("lxl01") = 0




  }



}


