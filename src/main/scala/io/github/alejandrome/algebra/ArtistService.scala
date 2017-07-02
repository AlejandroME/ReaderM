package io.github.alejandrome.algebra

import cats.data.Reader
import io.github.alejandrome.repository.ArtistRepository

trait ArtistService[T] {

  def listArtists(): Reader[ArtistRepository, List[T]]
  def retrieveArtistById(id: Int): Reader[ArtistRepository, Option[T]]
  def retrieveArtistByName(name: String): Reader[ArtistRepository, Option[T]]
  def insertArtist(artist: T): Reader[ArtistRepository, Int]
  def updateArtist(artist: T): Reader[ArtistRepository, Int]

}
