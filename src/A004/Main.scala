package A004


import scala.collection._
import scala.io.StdIn

/**
  * Created by Administrator on 2016/11/9 0009.
  */
abstract class SNode() {
  private var index: Int = -1
  val offset: Int
  var target: SNode

  def goLeft(): Boolean = {
    !this.isInstanceOf[InitNode]
  }

  def setIndex(idx: Int): Unit = {
    index = idx
  }

  def getIndex: Int = {
    index
  }

  def tarIndex: Int = {
    target.getIndex
  }
}

class GenNode(tar: InitNode) extends SNode {
  {
    tar.setTarget(this)
  }
  //  override val target = tar
  override val offset: Int = tar.targetOffset
  override var target: SNode = tar
}

class InitNode(toff: Int, offsets: Int) extends SNode {
  val targetOffset = toff
  override val offset: Int = offsets
  override var target: SNode = null

  def setTarget(tar: GenNode) = {
    target = tar
  }
}


object Main extends App {
  type Buff = mutable.ArrayBuffer[SNode]
  val Array(len, n, m) = StdIn.readLine().split(" ").map(b => b.toInt)
  val graph = mutable.Map[Int, Buff]()
  for (i <- 1 to n) {
    graph += (i -> new Buff())
  }
  for (i <- 1 to m) {
    val Array(lane, loff, roff) = StdIn.readLine().split(" ").map(b => b.toInt)
    graph(lane) += new InitNode(offsets = loff, toff = roff)
  }
  for (i <- graph) {
    //生成所有GenNode
    for (k <- i._2) {
      if (k.isInstanceOf[InitNode]) {
        graph(i._1 + 1) += new GenNode(k.asInstanceOf[InitNode])
      }
    }
  }
  for (i <- graph) {
    graph(i._1) = i._2.sortWith((a, b) => a.offset < b.offset)
    //排序
  }
  for (i <- graph) {
    for (j <- 0 to i._2.length - 1) {
      //      println(i._1+":"+j+"="+i._2(j).offset)
      //编号
      i._2(j).setIndex(j)
    }
  }

  def go(lane: Int, graph: mutable.Map[Int, Buff]): Int = {
    if (graph(lane).length == 0) {
      return lane
    } else {
      var point = graph(lane)(0).target;
      var curlane = if (graph(lane)(0).goLeft()) {
        lane - 1
      } else {
        lane + 1
      }
      while (point.getIndex < graph(curlane).length - 1) {
        if (graph(curlane)(point.getIndex + 1).goLeft()) {
          point = graph(curlane)(point.getIndex + 1).target
          curlane -= 1
        } else {
          point = graph(curlane)(point.getIndex + 1).target
          curlane += 1
        }
      }
      return curlane
    }
  }

  /**
    * 我才反应过来这道题本来就应该在图上反着走
    *
    * @param lane
    * @param graph
    * @return
    */
  def revGo(lane: Int, graph: mutable.Map[Int, Buff]): Int = {
    if (graph(lane).length == 0) {
      return lane
    } else {
      var point = graph(lane)(graph(lane).length - 1).target;
      var curlane = if (graph(lane)(graph(lane).length - 1).goLeft()) {
        lane - 1
      } else {
        lane + 1
      }
      while (point.getIndex > 0) {
        if (graph(curlane)(point.getIndex - 1).goLeft()) {
          point = graph(curlane)(point.getIndex - 1).target
          curlane -= 1
        } else {
          point = graph(curlane)(point.getIndex - 1).target
          curlane += 1
        }
      }
      return curlane
    }
  }


  println(revGo(1, graph))
"""提出コード結果詳細
  |
  |テスト番号
  |入力ケース番号	ジャッジ結果	実行時間
  |テスト 1
  |ケース 1（基本データ）
  |通過
  |0.42 秒
  |テスト 2
  |ケース 1（中規模データ）
  |通過
  |0.35 秒
  |テスト 3
  |ケース 1（中規模データ）
  |通過
  |0.60 秒
  |テスト 4
  |ケース 1（中規模データ）
  |通過
  |0.41 秒
  |テスト 5
  |ケース 1（中規模データ）
  |通過
  |0.74 秒
  |テスト 6
  |ケース 1（条件内の特殊なデータ）
  |通過
  |0.52 秒
  |テスト 7
  |ケース 1（中規模データ）
  |通過
  |0.47 秒
  |テスト 8
  |ケース 1（条件内の特殊なデータ）
  |通過
  |0.42 秒
  |ケース 2（大規模データ）
  |通過
  |0.53 秒
  |テスト 9
  |ケース 1（条件内の特殊なデータ）
  |通過
  |0.65 秒
  |ケース 2（大規模データ）
  |通過
  |0.68 秒
  |テスト 10
  |ケース 1（条件内の特殊なデータ）
  |通過
  |0.37 秒
  |ケース 2（大規模データ）
  |通過
  |0.68 秒"""

}

/*
7 4 5
1 3 1
3 2 2
2 3 5
3 4 4
1 6 6
 */