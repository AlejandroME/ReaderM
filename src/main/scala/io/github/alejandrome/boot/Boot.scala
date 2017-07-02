package io.github.alejandrome.boot

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import org.slf4j.LoggerFactory

object Boot extends App{

  implicit val system = ActorSystem("readerm")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val logger = LoggerFactory.getLogger(this.getClass)

  val apiHost = system.settings.config.getString("akka.http.host")
  val apiPort = system.settings.config.getInt("akka.http.port")

  val akkaHttpLogger = Logging(system.eventStream, "readerm")

  Http().bindAndHandle(handler = ???, interface = apiHost, port = apiPort) map {
    binding =>
      akkaHttpLogger.info(s"Readerm API Bound to address ${binding.localAddress}")
  } recover{
    case ex =>
      akkaHttpLogger.error(ex, "Failed to bind Readerm API to {}:{}", apiHost, apiPort)
  }

}
