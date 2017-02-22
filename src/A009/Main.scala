package A009

import scala.io.StdIn
/*
3 5
__\_/
___/_
\/\_/
 */
/**
  * Created by Administrator on 2016/11/6 0006.
  */
object Main extends App {
  val DIRECT = Map(
    'N' -> 0,
    'E' -> 1,
    'S' -> 2,
    'W' -> 3,
    0 -> 'N',
    1 -> 'E',
    2 -> 'S',
    3 -> 'W'
  )

  def nextDirect(prevDirect: Any, block: String): Int = {
    block match {
      case "\\" =>
        3 - prevDirect.asInstanceOf[Int].toInt
      case "/" =>
        prevDirect.asInstanceOf[Int].toInt % 2 match {
          case 0 =>
            prevDirect.asInstanceOf[Int].toInt + 1
          case 1 =>
            prevDirect.asInstanceOf[Int].toInt - 1
        }
    }
  }

  val Array(h, w, _*) = StdIn.readLine().split(" ").map(x => x.toInt)
  val Array(goout: String, passed, nextP, nextD) = "goout passed nextPoint nextDirect".split(" ")

  //  println("H is"+h)
  var matrix = new Array[Array[String]](h)
  for (i <- 0 until h) {
    matrix(i) = StdIn.readLine().split("")
  }

  /**
    *
    * @param fromP     Array[x,y]
    * @param direction DIRECT.apply('N')
    * @return
    */
  def go(fromP: Array[Int], direction: Any): Map[String, Any] = {
    //    var ret = Map()
    def DIR(ch: Char): Int = {
      DIRECT(ch).asInstanceOf[Int].toInt
    }
     if (direction.asInstanceOf[Int].toInt == DIR('N')) {
      /*x减小,y不变*/
      for (i <- (0 to fromP(0) - 1).reverse) {
        if ("_" != matrix(i)(fromP(1))) {
          return Map(
            goout -> false,
            passed -> (fromP(0) - i + 1),
            nextP -> Array(i, fromP(1)),
            nextD -> nextDirect(direction, matrix(i)(fromP(1)))
          )
        }
      }
      return Map(goout -> true, passed -> (fromP(0) - 0 + 1))
    }
    else if (DIR('S') == direction.asInstanceOf[Int].toInt) {
      /*x增大,y不变*/
      for (i <- fromP(0) + 1 to h - 1) {
        if (matrix(i)(fromP(1)) != "_") {
          return Map(
            goout -> false,
            passed -> (i - fromP(0) + 1),
            nextP -> Array(i, fromP(1)),
            nextD -> nextDirect(direction, matrix(i)(fromP(1)))
          )
        }
      }
      Map(goout -> true, passed -> ((h - 1) - fromP(0) + 1))
    } else if (direction.asInstanceOf[Int].toInt.equals(DIR('E'))) {
      for (j <- fromP(1) + 1 to w - 1) {
        if (matrix(fromP(0))(j) != "_") {
          return Map(
            goout -> false,
            passed -> (j - fromP(1) + 1),
            nextP -> Array(fromP(0), j),
            nextD -> nextDirect(direction, matrix(fromP(0))(j))
          )
        }
      }
      Map(goout -> true, passed -> ((w - 1) - fromP(1) + 1))
    } else {
      //最后一个向西
      for(j<-(0 to fromP(1)-1).reverse){
        if(matrix(fromP(0))(j)!="_"){
          return Map(
            goout->false,
            passed->(fromP(1)-j+1),
            nextP->Array(fromP(0),j),
            nextD->nextDirect(direction,matrix(fromP(0))(j))
          )
        }
      }
      Map(goout -> true,passed->(fromP(1)-0+1))
    }
  }
//  println(matrix(0)(0) == "_")
  //下面正式开始运行
  val Array(lines,blockesPassed) = "lines passedBlockes".split(" ")
  /**
    * 注意这里要使用可变Map = collection.mutable.Map
    */
  var Rec = collection.mutable.Map(
    lines->0,
    blockesPassed->0
  )




  matrix(0)(0) match {
    case "/"=>
      println(1)
    case "\\"=>
      Rec(lines) = Rec(lines)+1
      Rec(blockesPassed)+=1
      var tmp = Map(goout->false,nextP->Array(0,0),nextD->DIRECT('S'))
      while (tmp(goout)!=true){
        tmp = go(tmp(nextP).asInstanceOf[Array[Int]],tmp(nextD))
        Rec(lines)+=1
        Rec(blockesPassed)+=tmp(passed).asInstanceOf[Int]
      }
      println(Rec(blockesPassed)-(Rec(lines)-1))
    case "_"=>
      var tmp = Map(goout->false,nextP->Array(0,0),nextD->DIRECT('E'))
      while (tmp(goout)!=true){
        tmp = go(tmp(nextP).asInstanceOf[Array[Int]],tmp(nextD))
        Rec(lines)+=1
        Rec(blockesPassed)+=tmp(passed).asInstanceOf[Int]
      }
      println(Rec(blockesPassed)-(Rec(lines)-1))
  }
  """提出コード結果詳細
    |
    |テスト番号
    |入力ケース番号	ジャッジ結果	実行時間
    |テスト 1
    |ケース 1（基本データ）
    |通過
    |0.32 秒
    |テスト 2
    |ケース 1（基本データ）
    |通過
    |0.32 秒
    |テスト 3
    |ケース 1（基本データ）
    |通過
    |0.45 秒
    |テスト 4
    |ケース 1（基本データ）
    |通過
    |0.32 秒
    |テスト 5
    |ケース 1（基本データ）
    |通過
    |0.49 秒
    |テスト 6
    |ケース 1（境界値データ）
    |通過
    |0.33 秒
    |ケース 2（基本データ）
    |通過
    |0.32 秒
    |テスト 7
    |ケース 1（境界値データ）
    |通過
    |0.33 秒
    |ケース 2（境界値データ）
    |通過
    |0.35 秒
    |ケース 3（基本データ）
    |通過
    |0.44 秒
    |テスト 8
    |ケース 1（境界値データ）
    |通過
    |0.32 秒
    |ケース 2（境界値データ）
    |通過
    |0.32 秒
    |ケース 3（基本データ）
    |通過
    |0.46 秒
    |テスト 9
    |ケース 1（中規模データ）
    |通過
    |0.34 秒
    |テスト 10
    |ケース 1（中規模データ）
    |通過
    |0.52 秒"""

}
/*
3 5
\_\_/
___/_
\/\_/
 */