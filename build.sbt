import sbtcrossproject.CrossPlugin.autoImport.crossProject

// If you disable this sbt-dynver pulled in by sbt-ci-release will take over
ThisBuild / version := "1.2.0"

inThisBuild(
  List(
    description := "Apex type description traits",
    organization := "io.github.apex-dev-tools",
    homepage := Some(url("https://github.com/apex-dev-tools/apex-types")),
    licenses := List("BSD-3-Clause" -> url("https://opensource.org/licenses/BSD-3-Clause")),
    developers := List(
      Developer(
        "apexdevtools",
        "Apex Dev Tools Team",
        "apexdevtools@gmail.com",
        url("https://github.com/apex-dev-tools")
      )
    ),
    versionScheme := Some("strict"),
    isSnapshot := false,
    scalaVersion := "2.13.10",
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    sonatypeRepository := "https://s01.oss.sonatype.org/service/local"
  )
)

lazy val root = project.in(file(".")).aggregate(types.js, types.jvm)

lazy val types = crossProject(JVMPlatform, JSPlatform)
  .in(file("."))
  .settings(name := "apex-types", scalacOptions += "-deprecation")
  .jvmSettings(
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := false,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    }
  )
