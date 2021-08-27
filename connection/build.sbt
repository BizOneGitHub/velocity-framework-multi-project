import Common._
import Dependencies._

name := "velocity-connection"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1"
) ++ testDependencies
