package ch02

case class Planet(
    name: String,
    satellites: Array[String],
    mass: Float
) {

  def toXML =
    <planet>
    <name>{name}</name>
    <satellites>{
      satellites.map(satellite => <satellite> {satellite} </satellite>)
    }
    </satellites>
    <mass>{mass}</mass>
  </planet>
}

case class Planets(
    gasGiants: Array[Planet],
    rockyPlanets: Array[Planet]
) {
  def toXML =
    <planets>
      <gasGiants>
        {gasGiants.map(planet => planet.toXML)}
      </gasGiants>
      <rockyPlanets>
        {rockyPlanets.map(planet => planet.toXML)}
      </rockyPlanets>
    </planets>
}
