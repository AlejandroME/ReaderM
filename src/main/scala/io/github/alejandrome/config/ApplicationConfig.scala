package io.github.alejandrome.config

import com.typesafe.config.{Config, ConfigFactory}

object ApplicationConfig {

  val config: Config = ConfigFactory.load()

  val driverClassName: String = config.getString("postgres.driverClassName")
  val connectionString: String = config.getString("postgres.connectionString")
  val userName: String = config.getString("postgres.userName")
  val password: String = config.getString("postgres.password")

}
