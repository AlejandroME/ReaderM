package io.github.alejandrome

import io.github.alejandrome.algebra.interpreter.ArtistServiceInterpreter
import io.github.alejandrome.model.Artist
import io.github.alejandrome.repository.impl.ArtistPGRepository
import org.scalatest.{FlatSpec, Matchers}

class ArtistPGRepositoryTest extends FlatSpec with Matchers{

  val repo = new ArtistPGRepository()

  "PG Repository" should "list all the artists" in {

    // If you pass the type parameter the compiler will discard it because of erasure.
    ArtistServiceInterpreter.listArtists().run(repo) shouldBe a [List[_]]
  }

  it should "list an artist by its ID" in {
    ArtistServiceInterpreter.retrieveArtistById(1).run(repo) shouldBe an [Option[Artist]]
  }

  it should "return None in case of a non-existent artist ID" in {
    ArtistServiceInterpreter.retrieveArtistById(-3).run(repo) shouldBe None
  }

  it should "find 'DAVID BOWIE' in artists" in {
    ArtistServiceInterpreter.retrieveArtistByName("DAVID BOWIE").run(repo) shouldBe an [Option[Artist]]
  }

  it should "not find 'BILLY CORGAN' in artists" in {
    ArtistServiceInterpreter.retrieveArtistByName("BILLY CORGAN").run(repo) shouldBe None
  }

  it should "insert artist 'JOSEPH MOUNT' in artists table" in {
    noException should be thrownBy ArtistServiceInterpreter.retrieveArtistByName("JOSEPH MOUNT").run(repo)
  }

}
