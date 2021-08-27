import Common._
import Dependencies._

name := "velocity-transformation"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.9"
) ++ testDependencies