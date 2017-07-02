package io.github.alejandrome.repository.impl

import io.github.alejandrome.model.Artist
import io.github.alejandrome.repository.ArtistRepository

class ArtistH2Repository extends ArtistRepository{

  // Pending implementation

  override def insert(obj: Artist): Int = ???

  override def query(): List[Artist] = ???

  override def query(id: Int): Option[Artist] = ???

  override def query(name: String): Option[Artist] = ???

  override def update(obj: Artist): Int = ???

}
