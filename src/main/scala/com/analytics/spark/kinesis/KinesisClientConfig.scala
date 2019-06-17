package com.analytics.spark.kinesis

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.services.kinesis.AmazonKinesisClient

object KinesisClientConfig {

  def getShardCount(kinesisClient: AmazonKinesisClient, stream: String): Int =
    kinesisClient
      .describeStream(stream)
      .getStreamDescription
      .getShards
      .size

  /**
    * Finds AWS Credential by provided awsProfile and creates Kinesis Client
    */
  def setupKinesisClientConnection(endpointUrl: String, awsProfile: String): AmazonKinesisClient = {

    val credentials = new DefaultAWSCredentialsProviderChain().getCredentials

    require(credentials != null,
      "No AWS credentials found. Please specify credentials using one of the methods specified " +
        "in http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html")

    val akc = new AmazonKinesisClient(credentials)
    akc.setEndpoint(endpointUrl)
    akc
  }
}