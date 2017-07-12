package simple

/**
  * Created by Administrator on 2017/7/11.
  */


package day01

import java.util.concurrent.{Callable, ExecutorService, TimeUnit}

class Nonblocking {

}

object Nonblocking{

  trait Future[+A]{
    def apply(f : A => Unit) : Unit
  }

  type Par[+A] = ExecutorService => Future[A]

  object Par{


    /**
      * 接受一个无参函数返回一个A
      * 返回一个函数：接受ExecutorService返回Future
      * @param f
      * @tparam A
      * @return
      */
    def unit[A] (a : A) : Par[A] =
      es => new Future[A] {
        override def apply(f: (A) => Unit) = {
          f(a)
        }
    }

    def run[A](es : ExecutorService)(p : Par[A]) : A = {
      val ref = new java.util.concurrent.atomic.AtomicReference[A]
      val latch = new java.util.concurrent.CountDownLatch(1)
      p(es) { a => ref.set(a); latch.countDown }
      latch.await
      ref.get
    }

    def fork[A](a : => Par[A]) : Par[A] = {
      es => new Future[A] {
        override def apply(f: (A) => Unit): Unit = {
          TimeUnit.SECONDS.sleep(2000)
          eval(es)(a(es)(f))
        }
      }
    }

    def eval[A](es : ExecutorService)(f : => Unit) = {
      es.submit(new Callable[Unit] {
        override def call(): Unit = f
      })
    }

    def lazyUnit[A](a : A) : Par[A] = {
      fork(unit(a))
    }

    /**
      * 接受一个A=>B的函数
      * 返回一个A=>Par[B]的函数
      * @param f
      * @tparam A
      * @tparam B
      * @return
      */
    def asyncF[A, B](f : A => B) : A => Par[B] = {
      a => lazyUnit(f(a))
    }

  }

  import Par._

  def main(args: Array[String]): Unit = {
    println("start")
    fork(unit(100))
    println("end")
  }



  /**
    * 计算
    * @param seq
    * @return
    */
  def sum(seq : IndexedSeq[Int]) : Int = {
    if(seq.size <= 1){
      seq.headOption.getOrElse(0)
    }
    else{
      val (l, r) = seq.splitAt(seq.size / 2)
      sum(r) + sum(l)
    }
  }

  //工具类
  implicit def def2DefOps[A](p : Par[A]) : ParOps[A] = new ParOps(p)

  class ParOps[A](p : Par[A]){
    def map[B](f : A => B) = {}
    def map2[B, C](f : (A, B) => C) = {}
    def flatMap[B](f : A => Par[B]) = {}
    def zip[B](b : Par[B]) = {}
  }


}














