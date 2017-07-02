package io.github.alejandrome.model

case class Artist(artistID: Int, stageName: String, age: Int)

case class Band(bandID: Int, bandName: String, bandMembers: Option[List[Artist]])

case class Album(albumID: Int, albumName: String, performer: Band, genre: Genre, releaseYear: Int, duration: Int)