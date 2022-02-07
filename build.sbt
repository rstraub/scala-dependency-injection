import Dependencies._
import sbt.Keys.libraryDependencies

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "trains"

lazy val root = (project in file("."))
  .settings(
    name := "Scala Dependency Injection",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.softwaremill.macwire" % "macros_2.13" % "2.5.4"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
