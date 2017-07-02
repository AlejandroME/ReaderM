package io.github.alejandrome.api.helpers

import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.http.scaladsl.model.{HttpEntity, HttpResponse, StatusCode}
import akka.http.scaladsl.server.{Directives, StandardRoute}
import io.circe.Json

trait Response extends Directives{

  def createHttpResponse(statusCode: StatusCode, entity: Json): StandardRoute =
    complete(
      HttpResponse(status = statusCode, entity = HttpEntity(`application/json`, entity.toString()))
    )

  def createHttpResponse(statusCode: StatusCode): StandardRoute =
    complete(
      HttpResponse(status = statusCode)
    )
}
