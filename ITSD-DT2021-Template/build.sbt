lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := "ITSD Card Game",
    version := "1.0",
    scalaVersion := "2.13.1",
    // https://github.com/sbt/junit-interface
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v"),
    libraryDependencies ++= Seq(
      guice,
      ws,
      "org.webjars" %% "webjars-play" % "2.8.0",
      "org.webjars" % "bootstrap" % "2.3.2",
      "org.webjars" % "flot" % "0.8.3",

      // Testing libraries for dealing with CompletionStage...
      "org.assertj" % "assertj-core" % "3.14.0" % Test,
      "org.awaitility" % "awaitility" % "4.0.1" % Test,
    ),
    dependencyOverrides += "commons-codec" % "commons-codec" % "1.6",
    dependencyOverrides += "commons-io" % "commons-io" % "2.1",
    libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.3",
    libraryDependencies += "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.10.3",
    LessKeys.compress := true,
    javacOptions ++= Seq(
      "-Xlint:unchecked",
      "-Xlint:deprecation",
      "-Werror"
    )
  )
