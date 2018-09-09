lazy val akkaHttpVersion = "10.0.11"
lazy val akkaVersion     = "2.5.16"

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.example",
      scalaVersion := "2.12.6"
    )),
  name := "akka-http-scala-prototype",
  libraryDependencies ++= Seq(
    // https://mvnrepository.com/artifact/com.datastax.spark/spark-cassandra-connector
    //      "com.datastax.spark" %% "spark-cassandra-connector" % "2.3.1",
    "ch.qos.logback"     % "logback-classic"                % "1.2.3",
    "com.lightbend.akka" %% "akka-stream-alpakka-cassandra" % "0.20",
    "com.lightbend.akka" %% "akka-stream-alpakka-mongodb"   % "0.20",
    "com.typesafe.akka"  %% "akka-http"                     % akkaHttpVersion,
    "com.typesafe.akka"  %% "akka-http-spray-json"          % akkaHttpVersion,
    "com.typesafe.akka"  %% "akka-http-xml"                 % akkaHttpVersion,
    //      "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.89",
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
//      "com.typesafe.akka" %% "akka-stream-alpakka-cassandra" % "0.20",
    "com.typesafe.akka" %% "akka-slf4j"         % akkaVersion,
    "de.heikoseeberger" %% "akka-http-circe"    % "1.20.1",
    "io.circe"          %% "circe-generic"      % "0.9.3",
    "org.mongodb.scala" %% "mongo-scala-driver" % "2.4.1",
    "com.typesafe.akka" %% "akka-http-testkit"  % akkaHttpVersion % Test,
//      "com.typesafe.akka" %% "akka-persistence-cassandra-launcher" % "0.89" % Test,
    "com.typesafe.akka" %% "akka-testkit"        % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "org.scalatest"     %% "scalatest"           % "3.0.1"     % Test
  )
)
