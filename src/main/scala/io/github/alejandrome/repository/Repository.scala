package io.github.alejandrome.repository

trait Repository[A, Id] {

  def query(id: Id): Option[A]
  def query(): List[A]
  def insert(obj: A): Unit
  def update(obj: A): Int

}
