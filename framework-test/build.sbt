import Common._
import Dependencies._

name := "velocity-framework-test"

libraryDependencies ++= Seq(
  ) ++ testDependencies

//independent project
lazy val submodule: Project =
  if (sys.props.get("independent").isDefined) (project in file(".")).settings(buildSettings, commonSettings,
    assemblySettings) else null

//independent 'project' specific dependencies (pre-released)
libraryDependencies ++= {
  if (sys.props.get("independent").isDefined) Seq(
    "com.velocity" %% "velocity-transformation" % s"${_version}"
  ) else Seq()
}