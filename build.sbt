import sbt.project

val commonSettings = Seq(
    organization := "org.calcifer",
    name := "scws",
    version := "0.0.1",
    scalaVersion := "2.13.3"
)

lazy val root = (project in file(".")).
    settings(commonSettings: _*)


scalacOptions := Seq("-deprecation")

libraryDependencies ++= Seq(
  "io.spray" %%  "spray-json" % "1.3.5"
)

val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-optics"
).map(_ % circeVersion)

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"


libraryDependencies  ++= Seq(
  "org.scalanlp" %% "breeze" % "1.1",
  "org.scalanlp" %% "breeze-natives" % "1.1",
  "org.scalanlp" %% "breeze-viz" % "1.1"
)

