package io.github.alejandrome.api.exception

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.ExceptionHandler
import io.github.alejandrome.model.ApiError
import io.circe.generic.auto._
import io.circe.syntax._
import io.github.alejandrome.api.helpers.Response

trait ExceptionHandlerApi extends Response{

  val exceptionHandlerApi = ExceptionHandler{
    case e: Exception =>

      createHttpResponse(
        statusCode = StatusCodes.InternalServerError,
        entity = ApiError(
          message = "An error has ocurred while processing your request. Please see technicalMessage for more details.",
          technicalMessage = e.getMessage
        ).asJson
      )

  }

}
