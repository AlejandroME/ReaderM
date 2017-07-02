package io.github.alejandrome.api.helpers

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._
import io.github.alejandrome.model.{Album, Artist, Band, Genre}

trait Marshalling {

  implicit val artistEncoder: Encoder[Artist] = deriveEncoder[Artist]
  implicit val artistDecoder: Decoder[Artist] = deriveDecoder[Artist]

  implicit val bandEncoder: Encoder[Band] = deriveEncoder[Band]
  implicit val bandDecoder: Decoder[Band] = deriveDecoder[Band]

  implicit val genreEncoder: Encoder[Genre] = deriveEncoder[Genre]
  implicit val genreDecoder: Decoder[Genre] = deriveDecoder[Genre]

  implicit val albumEncoder: Encoder[Album] = deriveEncoder[Album]
  implicit val albumDecoder: Decoder[Album] = deriveDecoder[Album]

}
