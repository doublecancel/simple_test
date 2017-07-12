package simple

/**
  * Created by Administrator on 2017/7/10.
  */
trait RNG {

  type Rand[+A] = RNG => (A, RNG)


  def nextInt : (Int, RNG)
}

object RNG {

  case class Simple(seed : Long) extends RNG{
    def nextInt : (Int, RNG) = {
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
      val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
      val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
      (n, nextRNG)
    }
  }

  def nonNegativeInt(rng : RNG) : (Int, RNG) = {
    val (i, r) = rng.nextInt
    if(i < 0) (-(i + 1), r) else (i, r)
  }

  def double(rng : RNG) : (Double, RNG) = {
    val (i, r) = rng.nextInt
    (i / Int.MaxValue.toDouble, r)
  }

  def intDouble(rng : RNG) : ((Int, Double), RNG) = {
    val (i, r1) = rng.nextInt
    val (d, r2) = double(r1)
    ((i, d), r2)
  }

  def doubleInt(rng : RNG) : ((Double, Int), RNG) = {
    val (i, r1) = rng.nextInt
    val (d, r2) = double(r1)
    ((d, i), r2)
  }

  def double3 (rng : RNG) : ((Double, Double, Double), RNG) = {
    val (i1, r1) = double(rng)
    val (i2, r2) = double(r1)
    val (i3, r3) = double(r2)
    ((i1, i2, i3), r3)
  }

  def ints(count : Int)(rng : RNG) : (List[Int], RNG) = {
    if(count == 0)
      (List(), rng)
    else{
      val (i, r) = rng.nextInt
      val (j, r1) = ints(count - 1)(r)
      (i :: j, r1)
    }
  }

  type Rand[+A] = RNG => (A, RNG)

  def map[A, B](rand : Rand[A])(f : A => B) : Rand[B] = {
    rng => {
      val (i, r) = rand(rng)
      (f(i), r)
    }
  }

  val _double : Rand[Double] = map(nonNegativeInt)(_/Int.MaxValue.toDouble)


  def main(args: Array[String]): Unit = {
    val s = Simple(1000L)
    println(s.nextInt)//(384748900,Simple(25214903917011))
  }
}
