import org.scalafmt.sbt.ScalafmtPlugin.autoImport.scalafmtOnCompile
import sbt.Keys.{fork, publishMavenStyle, publishTo, _}
import sbt._
import sbtassembly.AssemblyKeys._
import sbtassembly._
object Common {
  lazy val scala212 = "2.12.14"
  lazy val scala211 = "2.11.12"
  lazy val supportedScalaVersions = List(scala212, scala211)

  lazy val buildSettings = Seq(
    organization := "com.velocity",
    scalaVersion := scala212
  )

  def projectModule(name: String): Project = {
    if(name.contains("test") || name.contains("connection")){
        Project(id = name, base = file(name))
          .settings(buildSettings, settings)
          .settings(addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17"): _*)
    }else{
      Project(id = name, base = file(name))
        .settings(buildSettings, settings, assemblySettings, publishSbtPlugin)
        .settings(addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17"): _*)
    }

  }

  lazy val settings =
    commonSettings

  lazy val commonSettings = Seq(
    // set to exactly one Scala version
    crossScalaVersions := supportedScalaVersions,
    crossPaths := false,
    Compile / packageBin := baseDirectory.value / "target" / s"${name.value}-${version.value}.jar",
    // disable publishing the main jar produced by `package`
    //    Compile / packageBin / publishArtifact := false,

    // disable publishing the main API jar
    Compile / packageDoc / publishArtifact := false,

    // disable publishing the main sources jar
    Compile / packageSrc / publishArtifact := false,
    scalacOptions ++= compilerOptions,
    fork := true,
    javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
//    publishTo := {
//      if (isSnapshot.value)
//        Some(MavenCache("Sonatype OSS Snapshots", file(Path.userHome.absolutePath + "/.m2/repository/snapshots")))
//      else
//        Some(MavenCache("local-maven", file(Path.userHome.absolutePath + "/.m2/repository")))
//    },

  )
  lazy val noPublishing = Seq(
    publish := (),
    publishLocal := ()
  )
  lazy val publishSbtPlugin = Seq(
    publishMavenStyle := true,
    publishArtifact in Test := false,
    publishTo := {
      val myrepo = "https://bizonedev.pkgs.visualstudio.com/Demo/_packaging/maven_sbt_demo/maven/v1/"
      if (isSnapshot.value) Some("The Realm" at myrepo + "snapshots")
      else Some("The Realm" at myrepo + "releases")
    },
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
  )

  lazy val assemblySettings = Seq(
    assembly / assemblyJarName := s"${name.value}-${version.value}.jar",
    assembly / assemblyMergeStrategy := {
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
    "utf8",
    "-P:linter:printWarningNames:false",
    "-P:linter:enable-only:UseHypot+CloseSourceFile+OptionOfOption"
  )

}
