import Common._
import Dependencies._

name := "velocity-transformation"
version := "0.0.2-SNAPSHOT"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.9"
) ++ testDependencies

//independent project
lazy val transformation: Project =
  if (sys.props.get("independent").isDefined) (project in file(".")).settings(buildSettings) else null
//independent 'project' specific dependencies (pre-released)
libraryDependencies ++= {
  if (sys.props.get("independent").isDefined) Seq(
    "com.velocity" %% "velocity-connection" % "0.0.1"
  ) else Seq()
}