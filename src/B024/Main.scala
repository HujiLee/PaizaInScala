package B024

import Ns._

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

object Ns {

  /**
    * 约定x>=0,y>=x
    *
    * @param $x
    * @param $y
    */
  class Grid($x: Int, $y: Int) {
    val x = $x
    val y = $y
    private var upG: Grid = null

    def minRadius: Double = {
      math.sqrt(x * x + y * y)
    }

    def maxRadius: Double = {
      math.sqrt(math.pow(x + 1, 2) + math.pow(y + 1, 2))
    }

    def isTouched(r: Double): Boolean = {
      r > minRadius
    }

    def isIn(r: Double): Boolean = {
      r > maxRadius
    }

    def UpG: Grid = {
      if (this.upG == null) {
        this.upG = new Grid(x, y + 1)
      }
      this.upG
    }

  }

  def specialGrid(x$y: Int): Grid = {
    new Grid(x$y, x$y)
  }

  def spG(x$y: Int) = specialGrid(x$y: Int)

}

/**
  * Created by Administrator on 2016/11/13 0013.
  */
object Main extends App {
  def count(sr: Double): Int = {
    val r = sr
    val r$i = math.ceil(r).toInt //这个值没有被用到
    val specialGrids = {
      var ab = new ArrayBuffer[Grid]()
      var xy = 0
      var tmpGrid: Grid = specialGrid(xy)
      do {
        ab.+=(tmpGrid)
        xy += 1
        tmpGrid = specialGrid(xy)
      } while (tmpGrid.isTouched(r))
      ab
    }
    val otherGrids = new Array[ArrayBuffer[Grid]](specialGrids.length).map(b => new ArrayBuffer[Grid]())
    for (i <- otherGrids.indices) {
      var thisG = new Grid(specialGrids(i).x, specialGrids(i).y)
      while (thisG.UpG.isTouched(r)) {
        otherGrids(i) += (thisG.UpG)
        thisG = thisG.UpG
      }
    }
    var otherCount = {
      var res = 0
      for (i <- otherGrids) {
        res += i.length
      }
      res
    }

    return otherCount * 8 + specialGrids.length * 4
  }

  for (i <- 1 to 22) {
    println("r=" + i / 2.0 + " then covered:" + count(i / 2.0))
  }


  val r = StdIn.readDouble()
  val r$i = math.ceil(r).toInt
  //这个值没有被用到
  val specialGrids = {
    var ab = new ArrayBuffer[Grid]()
    var xy = 0
    var tmpGrid: Grid = specialGrid(xy)
    do {
      ab.+=(tmpGrid)
      xy += 1
      tmpGrid = specialGrid(xy)
    } while (tmpGrid.isTouched(r))
    ab
  }
  val otherGrids = new Array[ArrayBuffer[Grid]](specialGrids.length).map(b => new ArrayBuffer[Grid]())
  for (i <- otherGrids.indices) {
    //    var thisG = new Grid(specialGrids(i).x, specialGrids(i).y)
    var thisG = specialGrids(i)
    while (thisG.UpG.isTouched(r)) {
      otherGrids(i) += (thisG.UpG)
      thisG = thisG.UpG
    }
  }
  var otherCount = {
    var res = 0
    for (i <- otherGrids) {
      res += i.length
    }
    res
  }

  println(otherCount * 8 + specialGrids.length * 4)
  """
    |提出コード結果詳細
    |
    |テスト番号
    |入力ケース番号	ジャッジ結果	実行時間
    |テスト 1
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 2
    |ケース 1（基本データ）
    |通過
    |0.39 秒
    |テスト 3
    |ケース 1（基本データ）
    |通過
    |0.31 秒
    |テスト 4
    |ケース 1（基本データ）
    |通過
    |0.32 秒
    |テスト 5
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 6
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 7
    |ケース 1（基本データ）
    |通過
    |0.50 秒
    |テスト 8
    |ケース 1（基本データ）
    |通過
    |0.35 秒
    |テスト 9
    |ケース 1（条件内の特殊なデータ）
    |通過
    |0.45 秒
    |テスト 10
    |ケース 1（条件内の特殊なデータ）
    |通過
    |0.42 秒
  """.stripMargin


}
