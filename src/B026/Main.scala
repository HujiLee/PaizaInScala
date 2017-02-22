package B026

import scala.collection.mutable
import scala.io.StdIn
/**
  * Created by Administrator on 2016/11/8 0008.
  */
object Main extends App {
  type Huji = mutable.Map[Int, Int]

  /**
    *
    * @param n500 =0
    * @param n100 =0
    * @param n50  =0
    * @param n10  =0
    * @return (500 -> n500, 100 -> n100, 50 -> n50, 10 -> n10)
    */
  def coinGen(n500: Int = 0, n100: Int = 0, n50: Int = 0, n10: Int = 0): Huji = {
    mutable.Map(500 -> n500, 100 -> n100, 50 -> n50, 10 -> n10)
  }

  def coinTotal(map: Huji): Int = {
    map(10) * 10 + map(50) * 50 + map(100) * 100 + map(500) * 500
  }

  def coinToString(map: Huji): String = {
    map(500).toString + " " + map(100) + " " + map(50) + " " + map(10)
  }

  def coinCalc(me: Huji, another: Huji, isPlus: Boolean): Huji = {
    var res = mutable.Map[Int, Int]()
    isPlus match {
      case true =>
        me.foreach {
          i => {
            res += i._1.->(me(i._1) + another(i._1))
          }
        }
        res
      case false =>
        for (i <- me) {
          res += i._1.->(me(i._1) - another(i._1))
        }
        res
    }
  }

  class SellMachine(n500: Int, n100: Int, n50: Int, n10: Int) {
    private var coins = coinGen(n500, n100, n50, n10)

    override def toString: String = {
      coinToString(coins)
    }

    def nowTotal(): Int = coinTotal(coins)

    private def goodPlan(plan: Huji): Boolean = {
      for (i <- plan) {
        if (plan(i._1) > coins(i._1)) {
          return false
        }
      }
      return true
    }

    /**
      *
      * @param change 约定change < sellMachine内的钱数总和
      * @return [true,Map()] or [false]
      */
    private def subtry(change: Int): Array[Any] = {
      if (change < 100) {
        if (change < 50) {
          val plan = coinGen(n10 = change / 10)
          goodPlan(plan) match {
            case true => return Array(true, plan)
            case false => return Array(false)
          }
        } else {
          /**
            * [50,100)
            */
          val plan1 = coinGen(n50 = 1, n10 = (change - 50) / 10)
          goodPlan(plan1) match {
            case true => return Array(true, plan1)
            case false => {
              val plan2 = coinGen(n10 = change / 10)
              goodPlan(plan2) match {
                case true => return Array(true, plan2)
                case false => return Array(false)
              }
            }
          }
        }
      } else {
        /**
          * [100...)
          */
        if (change % 100 == 0) {
          if (change < 500) {
            /** *
              * [100,500)之间的整百数
              */
            val plan = coinGen(n100 = change / 100)
            goodPlan(plan) match {
              case true => return Array(true, plan)
              case false => return Array(false)
            }
          } else {
            /**
              * [500,无限大)的整百数
              */
            val n500 = scala.math.min(change / 500, coins(500))
            val n100 = (change - n500 * 500) / 100
            val plan = coinGen(n500 = n500, n100 = n100)
            goodPlan(plan) match {
              case true => return Array(true, plan)
              case false => return Array(false)
            }
          }

        } else {
          val lingtou = change % 100
          val zhengbai = change - lingtou
          val tryLingtou = subtry(lingtou)
          val tryZhengbai = subtry(zhengbai)
          if (tryLingtou(0).asInstanceOf[Boolean] && tryZhengbai(0).asInstanceOf[Boolean]) {
            return Array(true, coinCalc(tryLingtou(1).asInstanceOf[Huji], tryZhengbai(1).asInstanceOf[Huji], true))
          } else {
            return Array(false)
          }
        }
      }

    }

    /**
      *
      * @param price int
      * @param getIn Map(500->?,100->?,50->?,0->?)
      */
    def tryChange(price: Int, getIn: Huji): Unit = {
      val change = coinTotal(getIn) - price
      nowTotal() < change match {
        case true => println("impossible")
        case false => {
          val tried = subtry(change)
          if (tried(0).asInstanceOf[Boolean]) {
            println(coinToString(tried(1).asInstanceOf[Huji]))
            coins = coinCalc(coins, tried(1).asInstanceOf[Huji], false)
            coins = coinCalc(coins, getIn, true)
          }
          else {
            println("impossible")
          }
        }
      }
    }

  }

  val initcoins = StdIn.readLine().split(" ").map(x => x.toInt)
  val sellMachine = new SellMachine(initcoins(0), initcoins(1), initcoins(2), initcoins(3))
  val howManyDeal = StdIn.readInt()
  for (i <- 1 to howManyDeal) {
    val thisDeal = StdIn.readLine().split(" ").map(x => x.toInt)
    sellMachine.tryChange(thisDeal(0), coinGen(thisDeal(1), thisDeal(2), thisDeal(3), thisDeal(4)))
  }
  """受験結果 受験言語： Scala 獲得ランク： Bランク スコア： 100点
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
    |0.34 秒
    |テスト 3
    |ケース 1（基本データ）
    |通過
    |0.36 秒
    |テスト 4
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 5
    |ケース 1（基本データ）
    |通過
    |0.36 秒
    |テスト 6
    |ケース 1（基本データ）
    |通過
    |0.35 秒
    |テスト 7
    |ケース 1（基本データ）
    |通過
    |0.34 秒
    |テスト 8
    |ケース 1（基本データ）
    |通過
    |0.51 秒
    |テスト 9
    |ケース 1（境界値データ）
    |通過
    |0.34 秒
    |テスト 10
    |ケース 1（境界値データ）
    |通過
    |0.36 秒"""
}
