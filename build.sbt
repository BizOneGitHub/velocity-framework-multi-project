import Common._


lazy val velocity = project
  .in(file("."))
  .aggregate(vfconnect, vftransformation, vfframeworktest)
  .settings(
    // crossScalaVersions must be set to Nil on the aggregating project
    crossScalaVersions := Nil,
    crossPaths := false,
    publish / skip := false
  )


lazy val vfconnect = projectModule("connection")

lazy val vftransformation = projectModule("transformation").dependsOn(vfconnect)

lazy val vfframeworktest = projectModule("framework-test").dependsOn(vftransformation)


credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
publishTo := {
  if (isSnapshot.value)
    Some(
      "snapshots".at(
        "https://bizonedev.pkgs.visualstudio.com/Demo/_packaging/maven_evaluation/maven/v1/snapshots"
      )
    )
  else
    Some(
      "release".at(
        "https://bizonedev.pkgs.visualstudio.com/Demo/_packaging/maven_sbt_demo/maven/v1/"
      )
    )
}
