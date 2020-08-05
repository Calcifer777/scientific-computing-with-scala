package ch02

import scala.io.Source

object XmlReader extends App {

  private def resource(filename:String) = 
    getClass.getResourceAsStream(filename)

  val xml = scala.xml.XML.load(resource("/planets.xml"))
  println(xml)
}
