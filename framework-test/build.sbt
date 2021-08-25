import Common._
import Dependencies._

name := "framework-test"
version := "0.0.2-SNAPSHOT"

libraryDependencies ++= Seq(
) ++ testDependencies

//independent project
lazy val frameworktest: Project =
  if (sys.props.get("independent").isDefined) (project in file(".")).settings(buildSettings) else null
//independent 'project' specific dependencies (pre-released)
libraryDependencies ++= {
  if (sys.props.get("independent").isDefined) Seq(
    "com.velocity" %% "velocity-transformation" % "0.0.1"
  ) else Seq()
}