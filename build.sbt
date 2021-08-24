import Common._

lazy val core = projectModule("connection")

lazy val submodule = projectModule("transform-data")
  .dependsOn(core)

