import Common._
import Dependencies._

name := "velocity-connection"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.1"
) ++ testDependencies

//independent project
lazy val submodule: Project =
  if (sys.props.get("independent").isDefined) (project in file(".")).settings(buildSettings, commonSettings,
    assemblySettings) else null