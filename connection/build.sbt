import Common._
import Dependencies._

name := "velocity-connection"
version := "0.0.2-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.1"
) ++ testDependencies
//lazy val connection = project
//  .settings(
//    crossPaths := false,
//    autoScalaLibrary := false,
//    Compile / packageBin := baseDirectory.value / "target" / s"${name.value}-${version.value}.jar",
//    assembly / assemblyJarName := s"${name.value}-${version.value}.jar"
//  )
