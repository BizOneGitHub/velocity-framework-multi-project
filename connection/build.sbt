import Common._
import Dependencies._

name := "velocity-framework-connection"
version := "0.0.2-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.1"
) ++ testDependencies

//independent project
lazy val jobTransformation: Project =
  if (sys.props.get("independent").isDefined) (project in file(".")).settings(buildSettings) else null
