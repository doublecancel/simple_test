package simple

/**
  * Created by Administrator on 2017/7/11.
  */
package Par

import java.util.concurrent.{Callable, ExecutorService, Future}

import scala.concurrent.duration.TimeUnit

object Par {

  type Par[A] = ExecutorService => Future[A]

  /**
    * 在线程中执行该方法
    * @param es
    * @param f
    * @tparam A
    * @return
    */
  def run[A](es : ExecutorService)(f : Par[A]) = f(es)

  /**
    * 将a包装为一个executorservice=>future的过程，并没有执行
    * @param a
    * @tparam A
    * @return
    */
  def unit[A](a : A) : Par[A] = (es : ExecutorService) => UnitFuture(a)

  private case class UnitFuture[A](get : A) extends Future[A]{
    def get(timeout : Long, units : TimeUnit) = get
    def isDone = true
    def isCancelled = false
    def cancel(evenIfRunning : Boolean) = false
  }

  def map2[A, B, C](a : Par[A], b : Par[B])(f : (A, B) => C) : Par[C] = {
    (es : ExecutorService) => {
      val af = a(es).get
      val bf = b(es).get
      UnitFuture(f(af, bf))
    }
  }

  /**
    * a表示正常的从executor=》future的过程
    * fork函数将a包装成为一个用线程执行的过程
    * @param a
    * @tparam A
    * @return
    */
  def fork[A](a : Par[A]) : Par[A] = {
    es => {
      es.submit(new Callable[A] {
        override def call(): A = a(es).get
      })
    }
  }

  /**
    * 返回一个线程执行a的函数
    * :=>表示非严格求值
    * @param a
    * @tparam A
    * @return
    */
  def lazyUnit[A](a : => A) : Par[A] = {
    fork(unit(a))
  }

  /**
    *
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def asyncF[A, B](f : A => B) : A => Par[B] = {
    a => lazyUnit(f(a))
  }

  /**
    * 根据函数f
    * 转换Par[A] => Par[B]
    * unit(())表示创建了一个空的Par
    * (a, _) => f(a)二元函数转一元函数
    * @param a
    * @param f
    * @tparam A
    * @tparam B
    * @return
    */
  def map[A, B](a : Par[A])(f : A => B) : Par[B] = {
//    (es : ExecutorService) => {
//      val af = a(es)
//      UnitFuture(f(af.get))
//    }

      map2(a, unit(()))((a, _) => f(a))

  }

  def sortPar(data : Par[List[Int]]) = map(data)(_.sorted)

  /**
    * 整合多个并行运算到一个运算中
    * @param list
    * @tparam A
    * @tparam B
    * @return
    */
  def parMap[A, B](list : List[A])(f : A => B) : Par[List[B]] = {
    val a : List[Par[B]] = list.map(asyncF(f))
    sequence_simple(a)
  }

  /**
    * 将List[Par[A]]=>Par[List[A]]
    * @param l
    * @tparam A
    * @return
    */
  def sequence_simple[A](l : List[Par[A]]) : Par[List[A]] = {
    l.foldRight[Par[List[A]]](unit(List()))((h, t) => map2(h, t)(_ :: _))
  }

  def delay[A](a : => Par[A]) = {
      e => a(e)
  }

  def choice[A, B](cond : Par[Boolean])(a : Par[A], b : Par[B]) = {
    (e : ExecutorService) => {
      if(run(e)(cond).get) a(e)
      else b(e)
    }
  }

  def chooser[A, B](a : Par[A])(f : A => Par[B]) : Par[B] = {
    e => {
      val t = run(e)(a).get
      run(e)(f(t))
    }
  }

  def flatMap[A, B](p : Par[A])(choices : A => Par[B]) : Par[B] = {
    e => {
      val t = run(e)(p).get
      run(e)(choices(t))
    }
  }

}
