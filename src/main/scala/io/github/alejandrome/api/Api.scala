package io.github.alejandrome.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import io.github.alejandrome.api.exception.ExceptionHandlerApi
import io.github.alejandrome.api.rejection.RejectionHandlerApi
import io.github.alejandrome.api.helpers.Marshalling
import io.github.alejandrome.repository.ArtistRepository
import io.circe.syntax._

trait Api extends RejectionHandlerApi with ExceptionHandlerApi with Marshalling{

  import io.github.alejandrome.algebra.interpreter._

  def api(repository: ArtistRepository): Route = {
    pathPrefix("api") {
      pathPrefix("artists") {
        (pathEndOrSingleSlash & get) {
          createHttpResponse(
            statusCode = StatusCodes.OK,
            entity = ArtistServiceInterpreter.listArtists().run(repository).asJson
          )
        }
      }
    }
  }

}
