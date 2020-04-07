package config

import cats.effect._
import cats.implicits._
import ciris._
import ciris.refined._
import eu.timepit.refined.auto._
import eu.timepit.refined.cats._
import eu.timepit.refined.types.net.UserPortNumber
import eu.timepit.refined.types.numeric.PosInt
import eu.timepit.refined.types.string.NonEmptyString

import scala.concurrent.duration._


case class AppConfig(
                        postgreSQL: PostgreSQLConfig,
                        httpServerConfig: HttpServerConfig,
                        httpClientConfig: HttpClientConfig
                    )

case class PostgreSQLConfig(
                               host: NonEmptyString,
                               port: UserPortNumber,
                               user: NonEmptyString,
                               database: NonEmptyString,
                               max: PosInt
                           )

case class HttpServerConfig(
                               host: NonEmptyString,
                               port: UserPortNumber
                           )

case class HttpClientConfig(
                               connectTimeout: FiniteDuration,
                               requestTimeout: FiniteDuration
                           )

object load {
    def apply[F[_] : Async : ContextShift]: F[AppConfig] = default().load[F]

    private def default(): ConfigValue[AppConfig] = AppConfig(
        PostgreSQLConfig("localhost", 5432, "postgres", "store", 10),
        HttpServerConfig("0.0.0.0", 8080),
        HttpClientConfig(2.seconds, 2.seconds)
    )
}
