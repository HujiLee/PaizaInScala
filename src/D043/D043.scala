package D043

import java.util.Scanner

/**
  * Created by Administrator on 2016/11/5 0005.
  */
object Main extends App{
  val sc = new Scanner(System.in)
  val rainyRate = sc.nextInt()
  if(rainyRate>=71){
    println("rainy")
  }
  else if(rainyRate>=31){
    println("cloudy")
  }
  else {
    println("sunny")
  }
  """
    |提出コード結果詳細
    |
    |テスト番号
    |入力ケース番号	ジャッジ結果	実行時間
    |テスト 1
    |ケース 1（基本データ）
    |通過
    |0.34 秒
    |テスト 2
    |ケース 1（基本データ）
    |通過
    |0.34 秒
    |テスト 3
    |ケース 1（基本データ）
    |通過
    |0.35 秒
    |テスト 4
    |ケース 1（基本データ）
    |通過
    |0.34 秒
    |テスト 5
    |ケース 1（基本データ）
    |通過
    |0.34 秒
    |テスト 6
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 7
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 8
    |ケース 1（基本データ）
    |通過
    |0.34 秒
    |テスト 9
    |ケース 1（基本データ）
    |通過
    |0.33 秒
    |テスト 10
    |ケース 1（基本データ）
    |通過
    |0.34 秒
  """.stripMargin
}
