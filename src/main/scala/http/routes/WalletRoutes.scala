package http.routes

import cats._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router


final class WalletRoutes[F[_]: Defer: Monad] extends Http4sDsl[F] {
    private[routes] val prefixPath = "/user"

    private val httpRoutes: HttpRoutes[F] = HttpRoutes.of[F] {

        case req @ POST -> Root / "wallet" =>

        case req @ DELETE -> Root / "wallet" =>

        case req @ PUT -> Root / "wallet"/ "insert" =>
        case req @ PUT -> Root / "wallet"/ "try" / "insert" =>

        case req @ PUT -> Root / "wallet" / "transfer" =>
        case req @ PUT -> Root / "wallet"/ "try" / "transfer" =>

        case req @ PUT -> Root / "wallet" / "withdraw" =>
        case req @ PUT -> Root / "wallet"/ "try" / "withdraw" =>

        case req @ GET -> Root / "wallet" / "history" =>
        case req @ GET -> Root / "wallet" / "report" =>

    }

    val routes: HttpRoutes[F] = Router(
        prefixPath -> httpRoutes
    )
}
