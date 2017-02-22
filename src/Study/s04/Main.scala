package Study.s04

/**
  * Created by Administrator on 2016/11/5 0005.
  */
class Circle(r: Double = 0) {
  private var radius: Double = r

  def setR(r: Double): Unit = {
    radius = r
  }

  def getArea(): Double = {
    math.Pi * math.pow(radius, 2)
  }
}

class MoreCircle(color: String, r: Double) extends Circle {
  private var colo = color

}


object Main extends App {
  var moreCircle = new MoreCircle("5", 2)
  var anyArray = Array(0, false)

  var circle1 = new Circle()
  var circle2 = new Circle(9)

  def x_n(x: Double, n: Int): Double = {
    if (n > 0 && n % 2 == 0) {
      return  x_n(x, n / 2) * x_n(x, n / 2)
    }
    else if (n > 0 && n % 2 == 1) {
      return x * x_n(x, n - 1)
    }
    else if (n == 0) {
      return 1
    }
    else {
      return 1 / x_n(x, -n)
    }
  }


  var a = 'l'.toInt
  println(a)
  //a is 108 int
  var b = if (a > 108) 1 else "Not > 108"
  println(b)
  //b
  var n = 10
  while (n < 100) {
    n = n + 10
    println(n)
  }
  for (i <- 1 to 7) {
    //[1,7]
    println(i.toInt)
  }
  for (i <- 1 until 7) {
    //半开半闭区间[1,7)
    println(i.toInt)
  }
  for (ch <- "abc") {
    print(ch + " ") //"a b c "
  }
  println()
  for (i <- 1 to 3; j <- 2 to 4) {
    print(i + " " + j + "||")
  }
  println()

  def fac(n: Int) = {
    var r = 1
    for (i <- 1 to n) {
      r = r * i
    }
    r: Int
  }

  println(fac(9))

  def Hello(str: String, left: String = "[") = {
    left + str
  }


}
