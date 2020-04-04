package http.routes

import cats._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router


final class WalletRoutes[F[_]: Defer: Monad] extends Http4sDsl[F] {
    private[routes] val prefixPath = "/users"

    private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {

        case req @ POST -> Root =>

    }

    val routes: HttpRoutes[F] = Router(
        prefixPath -> httpRoutes
    )
}
