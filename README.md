# ReaderM


This is a _toy project_ which I made for demonstrate some concepts in [my blog](https://alejandrome.github.io). It will be evolving with new functionalities that I'm going to explore.

This is a runnable _microservice_ built with the following stack of technologies:

* [Akka Http](http://doc.akka.io/docs/akka-http/current/scala/http/) (10.0.9)
* [cats](http://typelevel.org/cats/) (0.9.0)
* [doobie](https://github.com/tpolecat/doobie) (0.4.1)
* [Circe](https://github.com/circe/circe) (0.8.0)
* [spray Revolver](https://github.com/spray/sbt-revolver) (0.8.0)

Of course, on top of Scala (2.12.2).

This MS models a **_music domain_**: it documents my favourite artists, bands (not the same as artists), albums and songs. This because of my lack of _ideas_ for a domain :sweat_smile:, but it'll be enough for demonstration purposes.

You can run the API typing the following commands in your terminal (using revolver):

```shell
$ sbt
> reStart
```

With this command you are starting a forked JVM which can be killed or `reStart`'d without killing your `sbt` session. That means that you can type in any moment `reStart` for re-starting your application if you've made any change.

Also, you can run the application in the old-fashioned way:

```shell
$ sbt
> run
```

The service will be listening on port **7101**.

The requests (in the actual version) that you can issue are:

* `[GET]` `http://localhost:7101/api/artists` **to list all the artists available in the database.**
* `[GET]` `http://localhost:7101/api/artists?id=<id>` **to query an artist by his/her ID in the database.**
* `[GET]` `http://localhost:7101/api/artists?name=<name>` **to query an artist by his/her _EXACT_ name.**
* `[POST]` `http://localhost:7101/api/artists` **to create a new artist in the database**
* `[PUT]` `http://localhost:7101/api/artists` **to update**

All the examples and the entities for creating and updating an artists are included in the project root as a [Postman](https://www.getpostman.com/) collection if you want to take a look and test it for yourself.

# Database initialization

This first version assumes that you have a local installation of PostgreSQL running in your machine with the default user/schema (`postgres`). You can run the `DDL_PG.sql` script (located in the `SQL/` folder of this project) in that schema to create all the database model and then run the `DML_PG.sql` that contains a very basic dataset.
This is not yet automated, but it will in future versions, so you don't have to do it by hand.

If you are running macOS you can use the excellent [Postgres.app](https://postgresapp.com/) that runs a complete instance in your machine without configuring or messing up with a new installation of PG.

In other OS's you need to install the Database engine and execute the scripts mentioned above.

**REMEMBER:** the model is created in the `postgres` default schema.

# Disclaimer

As noted before, this is a _toy project_, so, things like exception handling, exception messages and so on can fail abruptly. This is going to be improved in future versions of this project. Again, the code here is only for demonstration purposes and it isn't intended for production use (yet?).

# License

The code in this repository is licensed under the MIT license. If you don't understand what does that means, here's an excerpt from [choosealicense](https://choosealicense.com/licenses/mit/):

> A short and simple permissive license with conditions only requiring preservation of copyright and license notices. Licensed works, modifications, and larger works may be distributed under different terms and without source code.

And that's it :)
If you have questions you can get in touch with me on [Twitter](https://twitter.com/AlejandroM_E).

AME.