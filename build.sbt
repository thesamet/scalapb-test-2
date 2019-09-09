name := "scalapb-test"

scalaVersion := "2.12.8"

scalacOptions in ThisBuild ++= Seq("-Xfatal-warnings", "-Xlint")

PB.targets in Compile := Seq(
  PB.gens.java -> (sourceManaged in Compile).value,
  scalapb.gen() -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
)

mainClass in assembly := Some("com.ukonnra.scalapb.Server")
test in assembly := {}
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeDependency = true)
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _                             => MergeStrategy.first
}