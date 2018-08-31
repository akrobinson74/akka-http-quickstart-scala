lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion = "2.5.11"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6"
    )),
    name := "akka-http-quickstart-scala",
    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/com.datastax.spark/spark-cassandra-connector
      //      "com.datastax.spark" %% "spark-cassandra-connector" % "2.3.1",
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
      //      "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.89",
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.1",


      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
//      "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % "0.89" % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test
    )
  )
