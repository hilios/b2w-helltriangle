package b2w.helltriangle

import b2w.helltriangle.HellTriangle.Vertex

case class HellTriangle(graph: Map[Vertex, Seq[Vertex]]) {

  /**
    * Returns all graph vertices as a sequence.
    */
  def toSeq: Seq[Vertex] = graph.flatMap(x => x._1 +: x._2).toSet.toSeq

  /**
    * Returns the values of the path that added are the maximum value in the triangle
    * @return
    */
  def solve: Seq[Int] = {
    /**
      * Traverse through the graph structure using a depth-first search algorithm to collect the
      * path of the vertices in the base of the triangle and select the one with max sum of its
      * values.
      * @param current the vertex to start the search
      * @param path the path traveled till this this vertex
      * @return a path that provides the max sum of its values
      */
    def traverse(current: Vertex, path: Seq[Vertex]): Seq[Vertex] = {
      val children = graph.getOrElse(current, Seq.empty)
      val currentPath = path :+ current
      if (children.nonEmpty) {
        children.map(traverse(_, currentPath)).maxBy(_.map(_.value).sum)
      } else {
        currentPath
      }
    }
    // Find root node then traverse
    graph.find(_._1.y == 0).map { case (node, _) =>
      traverse(node, Seq.empty).map(_.value)
    }.getOrElse {
      Seq.empty
    }
  }
}

object HellTriangle {
  case class Vertex(value: Int, x: Int, y: Int)

  def apply(triangle: Seq[Seq[Int]]) = {
    triangle.zipWithIndex.foreach { case (row, index) =>
      if(row.length != index + 1)
        throw new IllegalArgumentException("Input can not be arranged like a triangle")
    }

    /**
      * Iterates through the list of a list generating a from->to tuple to each available path of
      * the triangle.
      * @param list the triangle in the format of a list of a list
      * @return a sequence of tuple to each from->to path
      */
    def nodesOf(list: Seq[Seq[Int]]): Set[(Vertex, Vertex)] = list match {
      case Nil => Set.empty
      case head::tail =>
        val nodes = if (tail.headOption.nonEmpty) {
          val y = head.length - 1
          val headNodes = head.zipWithIndex.map(x => (x._1, x._2, y)).toIterator
          tail.head.zipWithIndex.sliding(2, 1).zip(headNodes).flatMap { case (children, h) =>
            val from = Vertex(h._1, h._2, h._3)
            children.map({ case (v, x) => Vertex(v, x, y + 1) }).map((from, _))
          }.toSet
        } else {
          Set.empty
        }
        nodes ++ nodesOf(tail)
    }

    val graph = nodesOf(triangle).toSeq.groupBy(_._1).map { case (k, v) => (k, v.map(_._2)) }
    new HellTriangle(graph)
  }
}
