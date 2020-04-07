package modules
import cats.effect._
import cats.implicits._
import http.routes.{UserRoutes, WalletRoutes}
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.middleware._
import org.http4s.server.Router

import scala.concurrent.duration._

object HttpApi {
    def make[F[_]: Concurrent: Timer] (): F[HttpApi[F]] = Sync[F].delay(new HttpApi[F]())
}

final class HttpApi[F[_]: Concurrent: Timer] {
    private val userRoutes = new UserRoutes[F].routes
    private val walletRoutes = new WalletRoutes[F].routes



    private val routes: HttpRoutes[F] = Router(
        version.v1 -> userRoutes <+> walletRoutes,
    )

    private val middleware: HttpRoutes[F] => HttpRoutes[F] = {
        http: HttpRoutes[F] => AutoSlash(http)
    } andThen {http: HttpRoutes[F] =>
        CORS(http, CORS.DefaultCORSConfig)
    } andThen{
        http: HttpRoutes[F] => Timeout(60.seconds)(http)
    }

    private val loggers: HttpApp[F] => HttpApp[F] = {
        { http: HttpApp[F] =>
            RequestLogger.httpApp(true, true)(http)
        } andThen { http: HttpApp[F] =>
            ResponseLogger.httpApp(true, true)(http)
        }
    }

    val httpApp: HttpApp[F] = loggers(middleware(routes).orNotFound)

}
