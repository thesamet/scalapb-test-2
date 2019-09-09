package com.ukonnra.scalapb

import com.ukonnra.scalapb.grpc.{Person, PersonServiceGrpc}
import io.grpc.ManagedChannelBuilder

import scala.concurrent.Await
import scala.concurrent.duration._

object Client {
  def main(args: Array[String]): Unit = {
    val channel = ManagedChannelBuilder
      .forAddress("localhost", 8888)
      .usePlaintext()
      .asInstanceOf[ManagedChannelBuilder[_]]
      .build()
    val client = PersonServiceGrpc.stub(channel)
    val res = Await.result(client.hello(Person("name", 10)), 1.second)
    println(s"Client: From Server: $res")
  }
}
