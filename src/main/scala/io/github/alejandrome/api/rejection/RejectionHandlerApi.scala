package io.github.alejandrome.api.rejection

import akka.http.scaladsl.model.StatusCodes.{BadRequest, MethodNotAllowed, NotFound}
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server._

trait RejectionHandlerApi {

  val genericRejectionHandler: RejectionHandler =
    RejectionHandler.newBuilder()
      .handle {
        case ValidationRejection(msg, _) =>
          complete((BadRequest, msg))
      }
      .handle {
        case MissingQueryParamRejection(param) =>
          complete((BadRequest, "One or more query params are required."))
      }
      .handle {
        case MalformedQueryParamRejection(parameterName, errorMsg, _) =>
          complete((BadRequest, "One of the supplied query params are malformed or invalid"))
      }
      .handle {
        case MalformedRequestContentRejection(message, cause) =>
          complete((BadRequest, "The request sent is malformed."))
      }
      .handleAll[MethodRejection] { methodRejections =>
      complete((MethodNotAllowed, s"Method not allowed"))
    }
      .handleNotFound {
        complete((NotFound, "The resource could not be found"))
      }
      .result()

}
