import Common._
import sbtrelease.ReleaseStateTransformations._

ThisBuild / organization := "com.velocity"

lazy val velocity = project
  .in(file("."))
  .settings(settings: _*)
  .disablePlugins(AssemblyPlugin)
  .aggregate(vfconnect, vftransformation, vfframeworktest)
  .settings(
    // crossScalaVersions must be set to Nil on the aggregating project
    crossScalaVersions := Nil,
    crossPaths := false,
    publish / skip := true,
  )


lazy val vfconnect = projectModule("connection")

lazy val vftransformation = projectModule("transformation").dependsOn(vfconnect)

lazy val vfframeworktest = projectModule("framework-test")
  .disablePlugins(AssemblyPlugin)
  .dependsOn(vftransformation)



releaseIgnoreUntrackedFiles := true

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies, // : ReleaseStep
  inquireVersions, // : ReleaseStep
  runClean, // : ReleaseStep
  runTest, // : ReleaseStep
  setReleaseVersion,
  commitReleaseVersion,
  pushChanges, //to make sure develop branch is pulled && will merge into master and push
  tagRelease,
  //  setNextVersion,
  //  commitNextVersion,
  pushChanges
)

autoCompilerPlugins := true
