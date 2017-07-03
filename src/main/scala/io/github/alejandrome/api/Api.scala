package io.github.alejandrome.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import io.github.alejandrome.api.exception.ExceptionHandlerApi
import io.github.alejandrome.api.rejection.RejectionHandlerApi
import io.github.alejandrome.api.helpers.Marshalling
import io.github.alejandrome.repository.ArtistRepository
import io.circe.syntax._
import io.github.alejandrome.model.Artist
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._

trait Api extends RejectionHandlerApi with ExceptionHandlerApi with Marshalling{

  import io.github.alejandrome.algebra.interpreter._

  def api(repository: ArtistRepository): Route =
    handleExceptions(exceptionHandlerApi){
      handleRejections(genericRejectionHandler){
        pathPrefix("api") {
          pathPrefix("artists") {
              parameter("id".as[Int]){ id =>
                (pathEndOrSingleSlash & get){
                  createHttpResponse(
                    statusCode = StatusCodes.OK,
                    entity = ArtistServiceInterpreter.retrieveArtistById(id).run(repository).asJson
                  )
                }
              } ~
                parameter("name"){ artistName =>
                  (pathEndOrSingleSlash & get){
                    createHttpResponse(
                      statusCode = StatusCodes.OK,
                      entity = ArtistServiceInterpreter.retrieveArtistByName(artistName).run(repository).asJson
                    )
                  }
                } ~ (pathEndOrSingleSlash & get) {
                createHttpResponse(
                  statusCode = StatusCodes.OK,
                  entity = ArtistServiceInterpreter.listArtists().run(repository).asJson
                )
              } ~ (pathEndOrSingleSlash & post) {
                entity(as[Artist]){ artist =>
                  createHttpResponse(
                    statusCode = StatusCodes.OK,
                    entity = ArtistServiceInterpreter.insertArtist(artist).run(repository).asJson
                  )
                }
              } ~ (pathEndOrSingleSlash & put) {
                entity(as[Artist]){ artist =>
                  createHttpResponse(
                    statusCode = StatusCodes.OK,
                    entity = ArtistServiceInterpreter.updateArtist(artist).run(repository).asJson
                  )
                }
              }
          }
        }
      }
  }

}
