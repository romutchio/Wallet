import cats.effect.{Blocker, ContextShift, ExitCode, IO, IOApp}
import doobie.Transactor
import doobie.util.ExecutionContexts
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor.Aux
import io.chrisdavenport.log4cats.Logger
import cats.implicits._
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import modules.HttpApi
import org.http4s.server.blaze.BlazeServerBuilder

object Main extends IOApp{
    implicit val logger = Slf4jLogger.getLogger[IO]
    override def run(args: List[String]): IO[ExitCode] = {
//    val xa: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
//      "org.postgresql.Driver",     // driver classname
//      "jdbc:postgresql://127.0.0.1:5432/wallet_database",     // connect URL (driver-specific)
//      "walletuser",                  // user
//      "123",                          // password
//      Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
//    )
//

      config.load[IO].flatMap { cfg =>
          Logger[IO].info(s"Loaded config $cfg") *>
              AppResources.make[IO](cfg).use { res =>
                  for {
                      api <- HttpApi.make[IO]
                      _ <- BlazeServerBuilder[IO]
                              .bindHttp(
                                  cfg.httpServerConfig.port.value,
                                  cfg.httpServerConfig.host.value
                              )
                              .withHttpApp(api.httpApp)
                              .serve
                              .compile
                              .drain
                  } yield ExitCode.Success
              }
      }

  }
}
