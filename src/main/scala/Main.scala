import cats.effect.{Blocker, ContextShift, ExitCode, IO, IOApp}
import doobie.Transactor
import doobie.util.ExecutionContexts
import doobie._
import doobie.implicits._
import doobie.util.transactor.Transactor.Aux

object Main extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = {
//    implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContexts.synchronous)

    val xa: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
      "org.postgresql.Driver",     // driver classname
      "jdbc:postgresql://127.0.0.1:5432/wallet_database",     // connect URL (driver-specific)
      "walletuser",                  // user
      "123",                          // password
      Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
    )

    val y = xa.yolo
    import y._
    val create =
      sql"""
    CREATE TABLE person (
      id   SERIAL,
      name VARCHAR NOT NULL UNIQUE,
      age  SMALLINT
    )
  """.update.run

    create.transact(xa).attempt.map(_.fold(println, println)).map(_ => ExitCode.Success)
  }
}
