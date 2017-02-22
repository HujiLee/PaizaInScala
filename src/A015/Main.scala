package A015

import NamespaceHuji._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

/**
  * Created by Administrator on 2016/11/11 0011.
  */
object NamespaceHuji {

  object Rotate {
    val $90 = 0
    val $180 = 1
    val $270 = 2
  }

  implicit object PointOrdering extends Ordering[Point] {
    override def compare(x: Point, y: Point): Int = {
      if (x.y != y.y) {
        return (x.y - y.y)
      } else {
        return x.x - y.x
      }
    }
  }

  class Point($x: Int = 0, $y: Int = 0) {

    override def equals(obj: scala.Any): Boolean = {
      x == obj.asInstanceOf[Point].x && y == obj.asInstanceOf[Point].y
    }

    override def hashCode(): Int = {
      (x -> y).hashCode()
    }

    var x = $x
    var y = $y

    /**
      * 获得两个点之间的位移差
      *
      * @param anotherPoint
      * @return
      */
    def delta(anotherPoint: Point): Array[Int] = {
      Array(x - anotherPoint.x, y - anotherPoint.y)
    }

    /**
      * 根据两个点之间的位移差构建位移后的点
      *
      * @param deltaImY
      * @return
      */
    def moveTo(deltaImY: Array[Int]): Point = {
      new Point(x - deltaImY(0), y - deltaImY(1))
    }

    def rotate(rotate: Int): Point = {
      rotate match {
        case Rotate.$90 => new Point(y, -x)
        case Rotate.$180 => new Point(-x, -y)
        case Rotate.$270 => new Point(-y, x)
      }
    }

    def toTuple2: Tuple2[Int, Int] = {
      x -> y
    }

    def this(tuple2: Tuple2[Int, Int]) = {
      this(tuple2._1, tuple2._2)
    }
  }

  /**
    * @note <TESTOVER>
    */
  abstract class Points() {
    val set: mutable.LinkedHashSet[Point]
  }

  /**
    * 拼图里的目标图形类
    */
  class PointPuzzle() extends Points {
    val set = new mutable.LinkedHashSet[Point]()

    def this(matrix: Array[String]) {
      //输入的矩阵是8行的字符串
      this()
      for (i <- 0 until matrix.length) {
        for (j <- 0 until matrix(i).length) {
          if (matrix(i)(j) == '#') {
            set += new Point(j, i)
          }
        }
      }
    }

    private def this(newSet: mutable.LinkedHashSet[Point]) {
      this()
      set ++= newSet
    }

    /**
      * 减去一个PointPart,如果能够减就返回一个新的Puzzle
      *
      * @param youPart
      * @return
      */
    def -(youPart: PointPart): PointPuzzle = {
      if (this.set.size < youPart.set.size) {
        return null
      }
      //确保本Puzzle的点的个数大于another
      val yousetCopy = new mutable.LinkedHashSet[Point]()
      val delta = youPart.set.min.delta(set.min)
      for (i <- youPart.set) {
        yousetCopy += i.moveTo(delta)
      }
      val mysetCopy = set.map(point => new Point(point.x, point.y))
      for (i <- yousetCopy) {
        val tryfind: Option[Point] = mysetCopy.find(p => p.x == i.x && p.y == i.y)
        if (tryfind.isEmpty) {
          return null
        } else {
          mysetCopy.remove(tryfind.last)
        }
      }
      new PointPuzzle(mysetCopy)
    }

    def combinedBy(array: ArrayBuffer[PointPart]): Boolean = {
      if (array.length == 1) {
        for (i <- array(0).$4types()) {
          var tryied = (this.-(i))
          if (tryied.isInstanceOf[PointPuzzle] && tryied.set.isEmpty) {
            return true
          }
        }
        return false
      } else {
        for (i <- array(0).$4types()) {
          var newPuzzle = (this.-(i))
          if (newPuzzle != null) {
            var newArray = array.clone()
            newArray.remove(0, 1)
            if (newPuzzle.combinedBy(newArray)) {
              return true
            }
          }
        }
        return false
      }
      true
    }

    def combinedBy4(buffer: ArrayBuffer[PointPart]): Boolean = {
      //确定buffer由4个块组成
      val range = 0 to 3
      for (i <- range) {
        for (j <- range if j != i) {
          for (k <- range if k != j && k != i) {
            var l = 6 - (i + j + k)
            if (combinedBy(ArrayBuffer(buffer(i), buffer(j), buffer(k), buffer(l)))) {
              return true
            }
          }
        }
      }
      false
    }
  }

  /**
    * 拼图用的积木类
    */
  class PointPart extends Points {
    val set = new mutable.LinkedHashSet[Point]()
    private var $4type = new ArrayBuffer[PointPart](0)

    /**
      * 暴露给外界,通过.#组成的字符串数组构建此对象
      *
      * @note <TESTOVER>
      * @param matrix
      */
    def this(matrix: Array[String]) {
      this()
      for (i <- 0 until matrix.length) {
        for (j <- 0 until matrix(i).length) {
          if (matrix(i)(j) == '#') {
            set += new Point(j, i)
          }
        }
      }
      this.$4types()
    }

    /**
      * 仅内部使用,通过一个新的点set来创建此对象
      *
      * @note <TESTOVER>
      * @param newSet
      */
    private def this(newSet: mutable.LinkedHashSet[Point]) {
      this()
      set ++= newSet
    }

    /**
      * 将set里的每个点旋转一个角度 得到个新的pointPart
      *
      * @note <TESTOVER>
      * @param angle
      * @return
      */
    private def rorate(angle: Int): PointPart = {
      var tmpset = new mutable.LinkedHashSet[Point]()
      for (i <- set) {
        tmpset += i.rotate(angle)
      }
      new PointPart(tmpset)
    }

    private def set4Type(arr: ArrayBuffer[PointPart]): PointPart = {
      this.$4type = arr
      this
    }

    /**
      * 目的:判断本Part和对方Part是否形状一致
      * 进一步:判断对方part和我方part的set经过位移能否重合
      * 由于Point已经重载equals函数,可以直接用point==point2的形式判断x和y是否相等
      *
      * @note <TESTOVER>
      * @param you
      * @return
      */
    def ==(you: PointPart): Boolean = {
      if (you.set.size != set.size) {
        return false
      }
      //经过上一步,确保了需要比较的两个点集的点的个数相同
      val yoursetCopy = new mutable.LinkedHashSet[Point]()
      val delta = you.set.min.delta(set.min)
      for (i <- you.set) {
        yoursetCopy += i.moveTo(delta)
      }
      for (i <- yoursetCopy) {
        set += i
        if (set.size > yoursetCopy.size) {
          set.remove(i) //i是异物,千万要记得及时删去
          return false
        }
        //确保yourCopy中的每一个点都在我里面已经有了 否则就是不能匹配
      }
      true
    }

    /**
      * 得到本对象的所有形态 去除重复形状
      *
      * @note <TESTOVER>
      * @return
      */
    def $4types(): ArrayBuffer[PointPart] = {
      if (this.$4type.isEmpty) {
        val candicates = Array(rorate(Rotate.$90).set4Type(this.$4type), rorate(Rotate.$180).set4Type(this.$4type), rorate(Rotate.$270).set4Type(this.$4type))
        var set = new mutable.LinkedHashSet[PointPart]() //要判断一下旋转以后的集合是不是能相减!
        set += this
        for (i <- candicates) {
          var diffrent = true
          for (j <- set) {
            if (j == i) {
              diffrent = false
            }
          }
          if (diffrent) {
            set += i
          }
        }
        this.$4type ++= set.toBuffer
      }
      this.$4type
    }
  }

}
object Main  extends App{
  val bufferForPuzzle = new ArrayBuffer[String]()
  for(i<-1 to 8){
    bufferForPuzzle+=(StdIn.readLine())
  }
  val puzzle = new PointPuzzle(bufferForPuzzle.toArray)

//  println(puzzle)
  val bufferForParts = new ArrayBuffer[PointPart]()
  for(i<-1 to 4){
    var bufferForOnePart = new ArrayBuffer[String]()
    for(j<- 1 to 4){
      bufferForOnePart+=(StdIn.readLine())
    }
    bufferForParts+=new PointPart(bufferForOnePart.toArray)
  }

  val bool = puzzle.combinedBy4(bufferForParts)
  println(
    bool match {
      case true=>"Yes"
      case false=>"No"
    }
  )


}
