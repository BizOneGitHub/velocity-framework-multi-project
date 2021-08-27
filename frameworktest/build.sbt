import Common._
import Dependencies._

name := "velocity-framework-test"

libraryDependencies ++= Seq(
  ) ++ testDependencies

//independent 'project' specific dependencies (pre-released)
libraryDependencies ++= {
  Seq(
    "com.velocity" %% "velocity-transformation" % s"${_version}"
  )

}
