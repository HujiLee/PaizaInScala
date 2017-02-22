package Study.s05

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Administrator on 2016/11/7 0007.
  */


 abstract class SNode() {
  private var index: Int = -1
  val offset: Int
  var target: SNode

  def goLeft(): Boolean = {
    this.isInstanceOf[InitNode]
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

object Jicheng extends App {
  var initNode = new InitNode(5, 3)
  var genNode = new GenNode(initNode)
  var initNode2 = new InitNode(1,4)
  var genNode2 = new GenNode(initNode2)
  var arr = new ArrayBuffer[SNode];
  arr+=(initNode2,initNode,genNode,genNode2)

//  initNode.target=null
  println(arr)
  arr = arr.sortWith((a,b)=>a.offset<b.offset)
  println(arr)
  println(arr(1))

}
