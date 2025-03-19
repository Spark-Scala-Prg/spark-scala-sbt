ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16" // Compatible with Spark 3.5.5

lazy val root = (project in file("."))
  .settings(
    name := "spark-scala-sbt",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.5.5" % "compile",
      "org.apache.spark" %% "spark-sql" % "3.5.5" % "compile"
    ),
    fork in run := true,
    Compile / run / javaOptions ++= Seq(
      // ✅ Fix IllegalAccessError by opening internal JDK modules
      "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
      "--add-opens=java.base/java.nio=ALL-UNNAMED",
      "--add-opens=java.base/sun.security.action=ALL-UNNAMED",
      "--add-opens=java.base/java.lang=ALL-UNNAMED",
      "--add-opens=java.base/java.util=ALL-UNNAMED"
    )
  )
