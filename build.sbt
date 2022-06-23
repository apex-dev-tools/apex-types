import sbtcrossproject.CrossPlugin.autoImport.crossProject

ThisBuild / version := "1.0.0"
ThisBuild / organization := "io.github.apex-dev-tools"
ThisBuild / scalaVersion := "2.13.3"
ThisBuild / isSnapshot := true

lazy val root = project.in(file(".")).aggregate(types.js, types.jvm)

lazy val types = crossProject(JVMPlatform, JSPlatform)
  .in(file("."))
  .settings(
    name := "apex-types",
    scalacOptions += "-deprecation",
  )
  .jvmSettings(
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := false,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    }
  )
