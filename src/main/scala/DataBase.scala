import doobie._
import doobie.implicits._
import doobie.util.ExecutionContexts
import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import fs2.Stream

case class DataBase() {
  // We need a ContextShift[IO] before we can construct a Transactor[IO]. The passed ExecutionContext
  // is where nonblocking operations will be executed. For testing here we're using a synchronous EC.
  implicit val cs = IO.contextShift(ExecutionContexts.synchronous)

  // A transactor that gets connections from java.sql.DriverManager and executes blocking operations
  // on an our synchronous EC. See the chapter on connection handling for more info.
  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver",     // driver classname
    "jdbc:postgresql:world",     // connect URL (driver-specific)
    "walletuser",                  // user
    "123",                          // password
    Blocker.liftExecutionContext(ExecutionContexts.synchronous) // just for testing
  )

  val y = xa.yolo
  import y._

  //  Credentials:
  // db : wallet_database
  // user: walletuser -> 123

  val drop =
    sql"""
    DROP TABLE IF EXISTS person
  """.update.run

  val create =
    sql"""
    CREATE TABLE person (
      id   SERIAL,
      name VARCHAR NOT NULL UNIQUE,
      age  SMALLINT
    )
  """.update.run
}
