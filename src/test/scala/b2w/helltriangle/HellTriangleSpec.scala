package b2w.helltriangle

import b2w.helltriangle.HellTriangle.Vertex
import org.scalatest.{FlatSpec, Matchers}

class HellTriangleSpec extends FlatSpec with Matchers {
  ".apply" should "create a proper graph like structure from a list of list" in {
    val ht = HellTriangle(Seq(
      Seq(6),
      Seq(3, 5),
      Seq(9, 7, 1),
      Seq(4, 6, 8, 4)
    ))

    ht.toSeq should contain allOf (
      Vertex(6, 0, 0),
      Vertex(3, 0, 1), Vertex(5, 1, 1),
      Vertex(9, 0, 2), Vertex(7, 1, 2), Vertex(1, 2, 2),
      Vertex(4, 0, 3), Vertex(6, 1, 3), Vertex(8, 2, 3), Vertex(4, 3, 3)
    )
    ht.toSeq.length shouldBe 10
  }

  it should "throw an exception if the input is not a triangle like list of list" in {
    an [IllegalArgumentException] should be thrownBy HellTriangle(Seq(
      Seq(6),
      Seq(9, 7, 1),
      Seq(3, 5),
      Seq(4, 6, 8, 4)
    ))
  }

  "#solve" should "return the correct path and sum" in {
    val ht = HellTriangle(Seq(
      Seq(6),
      Seq(3, 5),
      Seq(9, 7, 1),
      Seq(4, 6, 8, 4)
    ))

    ht.solve shouldBe Seq(6, 5, 7, 8)
    ht.solve.sum shouldBe 26
  }

  it should "do it again" in {
    val ht = HellTriangle(Seq(
      Seq(5),
      Seq(1, 3),
      Seq(4, 7, 10),
      Seq(0, 3, 2, 1),
      Seq(10, 13, 11, 5, 15)
    ))

    ht.solve shouldBe Seq(5, 3, 10, 1, 15)
    ht.solve.sum shouldBe 34
  }

  it should "handle empty inputs" in {
    val ht = HellTriangle(Seq.empty)
    ht.solve shouldBe Seq.empty
  }
}