import cats.Apply
import cats.effect._
import cats.implicits._
import config.{AppConfig, HttpClientConfig, PostgreSQLConfig}
import io.chrisdavenport.log4cats.Logger
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder

import scala.concurrent.ExecutionContext
import skunk._

final case class AppResources[F[_]](
   client: Client[F],
   psql: Resource[F, Session[F]],
)

object AppResources {

    def make[F[_]: ConcurrentEffect:ContextShift:Logger](cfg: AppConfig): Resource[F, AppResources[F]] = {
        def makePostgreSqlResource(c: PostgreSQLConfig): SessionPool[F] =
            Session
            .pooled[F](
                c.host.value,
                c.port.value,
                c.user.value,
                c.database.value,
                c.max.value
            )

        def makeHttpClient(c: HttpClientConfig): Resource[F, Client[F]] =
            BlazeClientBuilder[F](ExecutionContext.global)
                .withConnectTimeout(c.connectTimeout)
                .withRequestTimeout(c.requestTimeout)
                .resource


        (
            makeHttpClient(cfg.httpClientConfig),
            makePostgreSqlResource(cfg.postgreSQL),
        ).mapN(AppResources.apply[F])
    }

}


