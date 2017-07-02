package io.github.alejandrome.repository

import io.github.alejandrome.model.Artist

trait ArtistRepository extends Repository[Artist, Int]{

  def query(): List[Artist]
  def query(id: Int): Option[Artist]
  def query(name: String): Option[Artist]
  def insert(obj: Artist): Unit
  def update(obj: Artist): Int
}
