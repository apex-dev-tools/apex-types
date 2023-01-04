inThisBuild(
  List(
    description  := "Apex type description traits",
    organization := "io.github.apex-dev-tools",
    homepage     := Some(url("https://github.com/apex-dev-tools/apex-types")),
    licenses     := List("BSD-3-Clause" -> url("https://opensource.org/licenses/BSD-3-Clause")),
    developers := List(
      Developer(
        "apexdevtools",
        "Apex Dev Tools Team",
        "apexdevtools@gmail.com",
        url("https://github.com/apex-dev-tools")
      )
    ),
    versionScheme          := Some("strict"),
    isSnapshot             := false,
    scalaVersion           := "2.13.10",
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    sonatypeRepository     := "https://s01.oss.sonatype.org/service/local"
  )
)

lazy val pack = inputKey[Unit]("Publish specific local version")

// Don't publish root
publish / skip := true

lazy val types = crossProject(JVMPlatform, JSPlatform)
  .in(file("."))
  .settings(name := "apex-types", scalacOptions += "-deprecation")
  .jvmSettings()
  .jsSettings(
    scalaJSUseMainModuleInitializer := false,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.CommonJSModule)
    }
  )

// Command to do a local release under a specific version
// Defaults to last reachable tag (ignoring current commit) or 0.0.0
// e.g. sbt "pack 1.2.3-SNAPSHOT" / sbt pack
pack := {
  import sbt.complete.Parsers.spaceDelimited
  val args: Seq[String] = spaceDelimited("<arg>").parsed
  val v                 = args.headOption.getOrElse(previousStableVersion.value.getOrElse("0.0.0"))

  val newState =
    Project.extract(state.value).appendWithoutSession(Seq(ThisBuild / version := v), state.value)
  val proj = Project.extract(newState)

  proj.runTask(types.jvm / publishLocal, newState)
  proj.runTask(types.js / publishLocal, newState)
}
