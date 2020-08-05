package ch02

import scala.io.Source

import spray.json.{DefaultJsonProtocol, JsonParser}
import DefaultJsonProtocol.jsonFormat

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import io.circe.optics.JsonPath._


object PlanetsJsonProtocol extends DefaultJsonProtocol {
  implicit val planetFormat = jsonFormat(Planet, "name", "satellites", "mass")
  implicit val planetsFormat = jsonFormat(Planets, "gasGiants", "rockyPlanets")
}

object SprayJsonReader extends App {
  import PlanetsJsonProtocol.{planetFormat, planetsFormat}
  val rawText = Source.fromResource("planets.json").mkString
  val jsonAst = JsonParser(rawText)
  val planets = jsonAst.convertTo[Planets]
  println(planets.gasGiants(0).name)
  println(planets.rockyPlanets(1).satellites.toList)
}

object CirceJsonReader extends App {
  val rawText = Source.fromResource("planets.json").mkString
  val json = parse(rawText).getOrElse(Json.Null)
  val traversal = root.rockyPlanets.each.name.string
  val r = traversal.headOption(json)
  println(r)
}


