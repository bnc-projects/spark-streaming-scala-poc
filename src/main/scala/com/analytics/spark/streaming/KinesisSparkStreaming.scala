package com.analytics.spark.streaming

import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming._

object KinesisSparkStreaming {

  def main(args:Array[String]): Unit = {
    if (args.length != 4) {
      System.err.println(
        """
          |Usage: KinesisSparkStreaming <app-name> <stream-name> <region-name> <output-location>
          |
          |    <app-name> is the name of the consumer app
          |    <stream-name> is the name of the Kinesis stream
          |    <region-name> is the region of the Kinesis service
          |                   (e.g. us-east-1)
        """.stripMargin)
      System.exit(1)
    }

    val Array(appName,streamName,regionName,outputLoc) = args

    val checkpointInterval = Seconds(90)
    val batchInterval = Seconds(180)
    val master = "local"
    val initialPosition = InitialPositionInStream.LATEST
    val hdfsOutput = outputLoc
    val s3Bucket = outputLoc

    val streamingConfig = StreamingConfig(streamName,regionName,checkpointInterval,initialPosition,StorageLevel.MEMORY_AND_DISK_2,appName,master,batchInterval,awsProfile = "default",hdfsOutput,s3Bucket,checkpointDirectory = s"hdfs:///$outputLoc/checkpointDirectory")

    StreamingAnalyzer.execute(streamingConfig)

  }


}
