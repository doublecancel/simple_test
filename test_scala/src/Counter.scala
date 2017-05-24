import scala.beans.BeanProperty

/**
  * Created by Administrator on 2017/5/23.
  */
class Counter {
  private var value = 0;

  var age = 0


  val time = 0


  private[this] var t = 0

  @BeanProperty var name : String = ""



  def increment () {value += 1}
  def current() = value


}