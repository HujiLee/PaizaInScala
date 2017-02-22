package D037

import scala.io.StdIn

/**
  * Created by Administrator on 2016/11/5 0005.
  */
object Main extends App{
  val M = StdIn.readInt().toDouble
  //这里需要注意int/int会默认得到一个int,必须先把其中一个运算的数字变成double
  val N = StdIn.readInt()
  println(
    math.ceil(N/M).toInt
  )

}
