import sbt.Keys._
import sbt._

object Common{
  lazy val buildSettings = Seq(
    organization := "com.velocity",
    scalaVersion := "2.12.14",
  )

  def projectModule(name: String): Project =
    Project(id = name, base = file(name)).settings(buildSettings)



}