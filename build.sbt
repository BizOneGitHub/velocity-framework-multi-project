import Common._

lazy val vfconnect = projectModule("connection")
lazy val vftransformation = projectModule("transformation").dependsOn(vfconnect)
lazy val vfframeworktest = projectModule("framework-test").dependsOn(vftransformation)