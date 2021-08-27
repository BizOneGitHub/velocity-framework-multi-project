import Common._
import Dependencies._

name := "velocity-transformation"

libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.9.9"
) ++ testDependencies

////independent project
lazy val submodule: Project =
  if (sys.props.get("independent").isDefined) (project in file(".")).settings(buildSettings, commonSettings,
    assemblySettings) else null

//independent 'project' specific dependencies (pre-released)
libraryDependencies ++= {
  if (sys.props.get("independent").isDefined) Seq(
    "com.velocity" %% "velocity-connection" % s"${version}"
  ) else Seq()
}
