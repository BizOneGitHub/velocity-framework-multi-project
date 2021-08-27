import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys._
import sbtassembly._
object Common{
  lazy val scala212 = "2.12.14"
  lazy val scala211 = "2.11.12"
  lazy val supportedScalaVersions = List(scala212, scala211)

  lazy val buildSettings = Seq(
    organization := "com.velocity",
    scalaVersion := scala212,
  )

  def projectModule(name: String): Project =
    Project(id = name, base = file(name)).settings(buildSettings, commonSettings, assemblySettings)

  lazy val commonSettings = Seq(
    // set to exactly one Scala version
    crossScalaVersions := supportedScalaVersions,
    crossPaths := false,

    Compile / packageBin  := baseDirectory.value / "target" / s"${name.value}-${version.value}.jar",
    scalacOptions ++= compilerOptions
  )


  lazy val assemblySettings = Seq(
    assembly / assemblyJarName := s"${name.value}-${version.value}.jar",
    assembly / assemblyMergeStrategy  := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case "application.conf"            => MergeStrategy.concat
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    }
  )

  lazy val compilerOptions = Seq(
    "-unchecked",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:postfixOps",
    "-deprecation",
    "-encoding",
    "utf8"
  )

}