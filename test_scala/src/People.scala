/**
  * Created by Administrator on 2017/5/23.
  */
class People (var id : String, var name : String) {

  def test = "id:" + id + ",name:" + name

  val a = People.newNumber()


}

object People {
  private var a = 0
  private def newNumber () : Int = {
    a
  }
}
