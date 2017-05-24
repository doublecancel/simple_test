/**
  * Created by Administrator on 2017/5/23.
  */
object Main {
  def main(args: Array[String]): Unit = {
    val c = new Counter()
    c.increment()
    println(c.current)

    println(c.age)

    c.age = 10
    println(c.age)

//    c.time = 10;
//    println(c.t)



    c.setName("")



    val p = new People("147852", "lxl")//有参的构造函数
    print(p.test)

    println(p.id)

    println(p.a)
  }

}
