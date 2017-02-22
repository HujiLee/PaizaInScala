package B035

import scala.collection.mutable
import scala.io.StdIn

/**
  * Created by Administrator on 2016/11/12 0012.
  */
object Main  extends App{
  val Array(sN,sM,sT) = StdIn.readLine().split(" ").map(c=>c.toInt)
  val arrForLast = {
    var map = new mutable.HashMap[String,Int]()
    for(i<-1 to sN){
      val Array(ai,pi) = StdIn.readLine().split(" ")
      map+=(ai->pi.toInt)
    }
    map.toArray.sortWith(lt = (a, b) => {
      if (a._2 > b._2) true
      else if (a._2 < b._2) false
      else a._1 < b._1
    })
  }
  val arrForThis = {
    var map  = new mutable.HashMap[String,Int]()
    for(i<-1 to sM){
      val Array(di,wi,xi) = StdIn.readLine().split(" ")
      if(map.contains(wi)){
        map(wi)+=xi.toInt
      }else{
        map+=(wi->xi.toInt)
      }
    }
    //还要考虑到第二个月没有参与跑步但是上一个月参与了的
    for(i<-arrForLast){
      if(!map.contains(i._1)){
        map+=(i._1->0)
      }
    }
    map.toArray.sortWith(lt = (a, b) => {
      if (a._2 > b._2) true
      else if (a._2 < b._2) false
      else a._1 < b._1
    })

  }
  val mapForLast$T = {
    var map = new mutable.HashMap[String,Int]()
    for(i<-arrForLast.indices if i<sT){
      map+=(arrForLast(i)._1->i)
    }
    map
  }
  for(i<-arrForThis.indices if i<sT){
    print(arrForThis(i)._1+" "+arrForThis(i)._2+" ")
    println(
      if(mapForLast$T.contains(arrForThis(i)._1)){
        if(i<mapForLast$T(arrForThis(i)._1)){
          "up"
        }else if(i>mapForLast$T(arrForThis(i)._1)){
          "down"
        }else{
          "same"
        }
      }else{
        "new"
      }
    )
  }


//  println()
  """
    |再チャレンジ結果サマリ
    |
    |受験結果 受験言語： Scala 獲得ランク： Bランク スコア： 100点
    |提出コード結果詳細
    |
    |テスト番号
    |入力ケース番号	ジャッジ結果	実行時間
    |テスト 1
    |ケース 1（基本データ）
    |通過
    |0.38 秒
    |テスト 2
    |ケース 1（基本データ）
    |通過
    |0.46 秒
    |テスト 3
    |ケース 1（基本データ）
    |通過
    |0.37 秒
    |テスト 4
    |ケース 1（基本データ）
    |通過
    |0.38 秒
    |テスト 5
    |ケース 1（基本データ）
    |通過
    |0.48 秒
    |テスト 6
    |ケース 1（境界値データ）
    |通過
    |0.40 秒
    |ケース 2（基本データ）
    |通過
    |0.35 秒
    |テスト 7
    |ケース 1（境界値データ）
    |通過
    |0.33 秒
    |ケース 2（基本データ）
    |通過
    |0.41 秒
    |テスト 8
    |ケース 1（基本データ）
    |通過
    |0.54 秒
    |テスト 9
    |ケース 1（基本データ）
    |通過
    |0.59 秒
    |テスト 10
    |ケース 1（基本データ）
    |通過
    |0.39 秒
  """.stripMargin





}
