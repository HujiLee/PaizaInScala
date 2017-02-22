import scala.io.StdIn

/**
  * Created by Administrator on 2016/10/23 0023.
  */
/*
在Paiza里的object名必须是Main才能判定
 */


object Ando {
  def main(args: Array[String]): Unit = {
    val count:Int = StdIn.readInt()
    val ann = "Ann"
    for( i<- 1 to count){
      print(ann)
    }
    """
      |Test case1	成功実行時間: 0.46 秒
      |Test case2	成功実行時間: 0.39 秒
      |Test case3	成功実行時間: 0.33 秒
      |Test case4	成功実行時間: 0.32 秒
      |Test case5	成功実行時間: 0.43 秒
      |100  点 提出言語：Scala
      |提出コードバイト数：264
    """
    "对比一下C#"
    """
      |Hujimiyaさんのチャレンジ結果
      |Test case1	成功実行時間: 0.04 秒
      |Test case2	成功実行時間: 0.03 秒
      |Test case3	成功実行時間: 0.03 秒
      |Test case4	成功実行時間: 0.03 秒
      |Test case5	成功実行時間: 0.03 秒
      |100  点 提出言語：C#
      |提出コードバイト数：588
    """
    "对比一下Node.js"
    """
      |Test case1	成功実行時間: 0.05 秒
      |Test case2	成功実行時間: 0.05 秒
      |Test case3	成功実行時間: 0.05 秒
      |Test case4	成功実行時間: 0.05 秒
      |Test case5	成功実行時間: 0.06 秒
      |100  点 提出言語：JavaScript
      |提出コードバイト数：362
    """

  }

}
