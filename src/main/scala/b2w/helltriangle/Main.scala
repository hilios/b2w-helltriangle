package b2w.helltriangle

import scala.io.StdIn

object Main extends App {
  val r = """(\d+ ?){1,}""".r
  val e = """=""".r
  val q = """Q""".r

  def buildTriangle(triangle: Seq[Seq[Int]], showInstructions: Boolean = false): Unit = {
    if (showInstructions) {
      println(
        """
          |    __         ____   __       _                   __
          |   / /_  ___  / / /  / /______(_)___ _____  ____ _/ /__
          |  / __ \/ _ \/ / /  / __/ ___/ / __ `/ __ \/ __ `/ / _ \
          | / / / /  __/ / /  / /_/ /  / / /_/ / / / / /_/ / /  __/
          |/_/ /_/\___/_/_/   \__/_/  /_/\__,_/_/ /_/\__, /_/\___/
          |                                         /____/
          |
          |Type the numbers to each one of triangle row separated by an space and hit ENTER.
          |To see the solution type = (equal) and hit ENTER  or Q to exit.
        """.stripMargin)
    }

    val cmd = StdIn.readLine(s"Row ${triangle.length + 1} > ")
    cmd match {
      case r(_*) =>
        val row: Seq[Int] = cmd.split("\\s+").map(_.toInt)

        if (row.length == triangle.length + 1) {
          buildTriangle(triangle :+ row)
        } else {
          println(s"Invalid input, row must have ${triangle.length + 1} numbers")
          buildTriangle(triangle)
        }

      case e() =>
        val render = triangle.zipWithIndex.map { case (row, index) =>
          val padding = " " * (triangle.length - index - 1)
          s"$padding${row.mkString(" ")}"
        }.mkString("\n")

        val solution = HellTriangle(triangle).solve
        println(
          s"""
            |$render
            |
            |Path: ${solution.mkString(", ")}
            |Sum: ${solution.sum}
          """.stripMargin)

      case q() =>
        println("Bye, bye...")
      case _ =>
        println("Invalid input, try again...")
        buildTriangle(triangle)
    }
  }

  buildTriangle(Seq.empty, true)
}
