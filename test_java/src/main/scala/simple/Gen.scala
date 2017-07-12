package simple

/**
  * Created by Administrator on 2017/7/12.
  */
class Gen[+A] {

}

class Prop(name : String){
  def &&(p : Prop) = {}
  def check = {}
}

object Prop{

}


object Gen {


  def main(args: Array[String]) = {

  }

  def listOf[A](a : Gen[A]) = {

  }

  def listOfN[A](m : Int, a : Gen[A]) = {

  }

  def forAll[A](a : Gen[A])(f : A => Boolean) = {

  }


}



class Opter(val name : String){
  def test  = {
    Opter{
      "aaa"
    }
  }
}
object Opter {
  type success = Int
  def apply(name : String) = {
    new Opter(name)
  }
}
