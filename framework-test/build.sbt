import Common._
import Dependencies._

name := "velocity-framework-test"
version := "0.0.2-SNAPSHOT"

libraryDependencies ++= Seq(
) ++ testDependencies

//independent 'project' specific dependencies (pre-released)
libraryDependencies ++= {
  Seq(
    "com.velocity" %% "velocity-transformation" % "0.0.1"
  )

}