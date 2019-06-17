name := "spark-streaming-market-cap"

version := "0.13.12"

scalaVersion := "2.11.0"

val sparkVersion = "2.4.2"

libraryDependencies ++= Seq(

  "org.apache.spark" % "spark-core_2.11" % sparkVersion % "provided",
  "org.apache.spark" % "spark-sql_2.11" % sparkVersion % "provided",
  "org.apache.spark" % "spark-streaming_2.11" % sparkVersion % "provided",
  "org.apache.spark" % "spark-streaming-kinesis-asl_2.11" % sparkVersion


)