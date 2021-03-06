package io.github.alejandrome.repository.impl

import io.github.alejandrome.model.Artist
import io.github.alejandrome.repository.ArtistRepository
import doobie.imports._
import doobie.hikari.imports._

class ArtistPGRepository extends ArtistRepository{

  import io.github.alejandrome.config.ApplicationConfig._

  def getTransactor[T](q: ConnectionIO[T]): IOLite[T] = {
    for {
      connection     <- HikariTransactor[IOLite](driverClassName, connectionString, userName, password)
      _              <- connection.configure(hx => IOLite.primitive(hx.setAutoCommit(true)))
      databaseAccess <- q.transact(connection) ensuring connection.shutdown
    } yield databaseAccess
  }

  override def query(): List[Artist] = {
    val q = sql"SELECT * FROM ARTISTS".query[Artist].process.list
    val tx: IOLite[List[Artist]] = getTransactor(q)
    tx.unsafePerformIO
  }

  override def query(id: Int): Option[Artist] = {
    val q = sql"SELECT * FROM ARTISTS WHERE ARTISTID = $id".query[Artist].option
    val tx: IOLite[Option[Artist]] = getTransactor(q)
    tx.unsafePerformIO
  }

  override def query(name: String): Option[Artist] = {
    val q = sql"SELECT * FROM ARTISTS WHERE STAGENAME = ${name.toUpperCase()}".query[Artist].option
    val tx: IOLite[Option[Artist]] = getTransactor(q)
    tx.unsafePerformIO
  }

  override def insert(obj: Artist): Int = {
    val q = sql"INSERT INTO ARTISTS(STAGENAME, AGE) VALUES(${obj.stageName}, ${obj.age})".update.run
    val tx: IOLite[Int] = getTransactor(q)
    tx.unsafePerformIO
  }

  override def update(obj: Artist): Int = {
    val q = sql"UPDATE ARTISTS SET STAGENAME = ${obj.stageName}, AGE = ${obj.age} WHERE ARTISTID = ${obj.artistID}".update.run
    val tx: IOLite[Int] = getTransactor(q)
    tx.unsafePerformIO
  }

}
