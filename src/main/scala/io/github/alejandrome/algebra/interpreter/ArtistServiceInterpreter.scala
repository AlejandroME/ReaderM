package io.github.alejandrome.algebra.interpreter

import cats.data.Reader
import io.github.alejandrome.algebra.ArtistService
import io.github.alejandrome.model.Artist
import io.github.alejandrome.repository.ArtistRepository

class ArtistServiceInterpreter extends ArtistService[Artist]{

  override def listArtists(): Reader[ArtistRepository, List[Artist]] = Reader {
    repo: ArtistRepository =>
      repo.query()
  }

  override def retrieveArtistById(id: Int): Reader[ArtistRepository, Option[Artist]] = Reader {
    repo: ArtistRepository =>
      repo.query(id)
  }

  override def retrieveArtistByName(name: String): Reader[ArtistRepository, Option[Artist]] = Reader {
    repo: ArtistRepository =>
      repo.query(name)
  }

  override def insertArtist(artist: Artist): Reader[ArtistRepository, Int] = Reader {
    repo: ArtistRepository =>
      repo.insert(artist)
  }

  override def updateArtist(artist: Artist): Reader[ArtistRepository, Int] = Reader {
    repo: ArtistRepository =>
      repo.update(artist)
  }

}

object ArtistServiceInterpreter extends ArtistServiceInterpreter
