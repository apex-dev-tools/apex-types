# Apex Types

This provides abstractions for describing types defined in Salesforce's Apex language for use by tooling libraries. The type model is exposed in Scala and can be built for JVM or Node use (via scala.js).

Types are described using an ITypeDeclaration which contains fields for individual parts of the type such as constructors and inner types. For simple elements, such as Modifiers, concrete implementations are provided to ease adoption.

## Getting Started

### Installation

Releases are available from [SonaType](https://s01.oss.sonatype.org). You will need to add the repository to your build tool.

SBT:

  ```scala
  // Add if not present
  ThisBuild / resolvers ++= Resolver.sonatypeOssRepos("releases")

  project.settings(
    // Replace %% with %%% to use ScalaJS build
    libraryDependencies += "io.github.apex-dev-tools" %% "apex-types" % "X.X.X"
  )
  ```

Maven:

  ```xml
  <!-- In <repositories/> -->
  <repository>
    <id>oss.sonatype.org</id>
    <url>https://s01.oss.sonatype.org/content/repositories/releases</url>
    <releases>
      <enabled>true</enabled>
    </releases>
  </repository>

  <!-- In <dependencies/> -->
  <dependency>
      <groupId>io.github.apex-dev-tools</groupId>
      <artifactId>apex-types</artifactId>
      <version>X.Y.Z</version>
  </dependency>
  ```

## Development

### Building

The build is a cross project for JS and JVM; SBT commands are aggregated, but can also be executed separately with `sbt typesJVM/[cmd]` or `sbt typesJS/[cmd]`.

Available build commands:

* `sbt package` - Creates packaged jars for testing. e.g. `jvm/target/scala-2.13/apex-types_2.13-X.Y.Z.jar`
* `sbt pack` / `sbt "pack [version]"` - Do a local published release of the most recent tag or given value.
  * **WARNING:** This can override the remote releases, clear your `~/.ivy2/local` directory to revert.
* `sbt publishLocal` - Same as `pack` except it will generate snapshot versions.
* `sbt test` - Execute full test run.
* `sbt clean` - Removes most build files and artifacts.

### Release

Releases are automated via workflow on publishing a release. Create a `v` prefixed tag at the same time on the commit to be released (e.g. `v1.0.0`).

Snapshot releases can also be created at any time by executing the `Publish` workflow on a branch. The versioning will be in the format `X.X.X+Y-yyyy-SNAPSHOT`; the latest tag followed by recent commit info.
