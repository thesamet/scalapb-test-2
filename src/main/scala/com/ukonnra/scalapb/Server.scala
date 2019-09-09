package com.ukonnra.scalapb

import com.ukonnra.scalapb.grpc.{Person, PersonServiceGrpc}
import com.ukonnra.scalapb.grpc.PersonServiceGrpc.PersonService
import io.grpc.ServerBuilder

import scala.concurrent.Future

case class Service() extends PersonService {
  override def hello(request: Person): Future[Person] = {
    println(s"Server: From Client: $request")
    Future.successful(Person(s"from server: ${request.name}", request.age + 1))
  }
}

object Server {
  import scala.concurrent.ExecutionContext.global
  def main(args: Array[String]): Unit = {
    println("start server")
    val service = PersonServiceGrpc.bindService(Service(), global)
    val server = ServerBuilder.forPort(8888).addService(service).asInstanceOf[ServerBuilder[_]].build().start()
    server.awaitTermination()
  }
}