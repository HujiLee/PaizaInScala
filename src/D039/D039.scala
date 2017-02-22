package D039

import scala.io.StdIn

/**
  * Created by Administrator on 2016/11/5 0005.
  */
object Main extends App{
  val init = StdIn.readInt()
  val dict: Map[Boolean, String] = Map(
    true->"YES",
    false->"NO"

  )
  var bool = true
  for(i<-1 to 2){
    if(init!=StdIn.readInt()){
      bool = false
    }
  }
  println(dict(bool))
  """
    |提出コード結果詳細
    |
    |テスト番号
    |入力ケース番号	ジャッジ結果	実行時間
    |テスト 1
    |ケース 1（基本データ）
    |通過
    |0.31 秒
    |テスト 2
    |ケース 1（基本データ）
    |通過
    |0.31 秒
    |テスト 3
    |ケース 1（基本データ）
    |通過
    |0.43 秒
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
    |0.31 秒
    |テスト 7
    |ケース 1（基本データ）
    |通過
    |0.43 秒
    |テスト 8
    |ケース 1（基本データ）
    |通過
    |0.32 秒
    |テスト 9
    |ケース 1（基本データ）
    |通過
    |0.31 秒
    |テスト 10
    |ケース 1（基本データ）
    |通過
    |0.33 秒
  """.stripMargin


}
