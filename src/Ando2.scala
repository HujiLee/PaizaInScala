import scala.io.StdIn

/**
  * Created by Administrator on 2016/10/23 0023.
  */
object Ando2 extends App {
  var s: String = StdIn.readLine()
  var count = 0
  while (s.indexOf("cat")>=0){
    count+=1
    s = s.replaceFirst("cat","++")
  }
  print(count)
  """
    |Hujimiyaさんのチャレンジ結果
    |Test case1	成功実行時間: 0.46 秒
    |Test case2	成功実行時間: 0.43 秒
    |Test case3	成功実行時間: 0.32 秒
    |Test case4	成功実行時間: 0.37 秒
    |Test case5	成功実行時間: 0.33 秒
    |100  点 提出言語：Scala
    |提出コードバイト数：272
  """
}
