package A017

import Ns.GameBord

import scala.io.StdIn

/**
  * Created by Administrator on 2016/11/11 0011.
  */
object Ns {
  val VOID = '.'
  val OCCUPATION = '#'

  def initMatrix(w: Int, h: Int): Array[Array[Char]] = {
    var mat = new Array[Array[Char]](h)
    for (i <- 0 until h) {
      mat(i) = (new Array[Char](w)).map(x => VOID)
    }
    mat
  }

  class GameBord(w: Int, h: Int) {
    private val W = w
    private val H = h
    private val matrix = initMatrix(w, h)

    override def toString: String = {
      var res = ""
      for (i <- 0 until H) {
        res = res + matrix(i).mkString("") + "\n"
      }
      res
    }

    def fall(xi: Int, wi: Int, hi: Int): Unit = {
      val left = xi;
      val right = math.min(xi + wi - 1, W - 1)
      def getBotoom(thiss: GameBord, le: Int, ri: Int): Int = {
        for(i<-0 until thiss.H){
          for(j<-le to right){
            if(thiss.matrix(i)(j)==OCCUPATION){
              return i-1
            }
          }
        }
        thiss.H - 1
      }
      val fallbottom = getBotoom(thiss = this,le = left,ri = right)
      for(i<-(math.max(fallbottom-hi+1,0) to fallbottom).reverse){
        for(j<-left to right){
          this.matrix(i)(j)=OCCUPATION
        }
      }
    }
  }

}

object Main extends App {
  val Array(ih,iw,in) = StdIn.readLine().split(" ").map(c=>c.toInt)
  var gameBord = new GameBord(w = iw, h = ih)
//  println(gameBord)
  for(n<-1 to in){
    val Array(ihi,iwi,ixi) = StdIn.readLine().split(" ").map(c=>c.toInt)
    gameBord.fall(xi = ixi,wi = iwi,hi = ihi)
  }
  println(gameBord)
""""再チャレンジ結果サマリ",
  |"受験結果 受験言語： Scala 獲得ランク： Aランク スコア： 100点",
  |"提出コード結果詳細",
  |"テスト番号",
  |"入力ケース番号	ジャッジ結果	実行時間",
  |"テスト 1",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.42 秒",
  |"テスト 2",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.46 秒",
  |"テスト 3",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.34 秒",
  |"テスト 4",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.50 秒",
  |"テスト 5",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.46 秒",
  |"テスト 6",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.42 秒",
  |"テスト 7",
  |"ケース 1（基本データ）",
  |"通過",
  |"0.45 秒",
  |"テスト 8",
  |"ケース 1（境界値データ）",
  |"通過",
  |"0.35 秒",
  |"ケース 2（基本データ）",
  |"通過",
  |"0.34 秒",
  |"テスト 9",
  |"ケース 1（境界値データ）",
  |"通過",
  |"0.47 秒",
  |"ケース 2（基本データ）",
  |"通過",
  |"0.42 秒",
  |"テスト 10",
  |"ケース 1（境界値データ）",
  |"通過",
  |"0.48 秒",
  |"ケース 2（基本データ）",
  |"通過",
  |"0.42 秒""""


}
