package ch02

import scala.collection.mutable.{Map}
import scala.io.Source

object CSVReader extends App {
  val csv_filename = "iris_sample.csv"
  val delimiter = ","
  val fp = Source.fromResource(csv_filename)
  val raw_data =
    try {
      val lines = fp.getLines()
      val splitted = lines
        .map(_.split(delimiter).map(_.trim).toList)
      splitted.toList
    } finally {
      fp.close
    }

  val labels = List(
    "sepal length",
    "sepal width",
    "petal length",
    "petal width",
    "class"
  )

  val data = Map[String, List[String]](labels.zip(raw_data): _*)

  println(data)
}
