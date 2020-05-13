name := "oracle-scala-workshop"

version := "0.1"

scalaVersion := "2.13.2"


lazy val akkaVersion = "2.6.5"
lazy val akkaHttpVersion  = "10.2.0-M1"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % akkaVersion
libraryDependencies += "com.typesafe" % "config" % "1.3.3"
// https://mvnrepository.com/artifact/com.typesafe.akka/akka-http
libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.2.0-M1"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.0-M1"
libraryDependencies += "com.typesafe.akka" %% "akka-stream"     % akkaVersion


