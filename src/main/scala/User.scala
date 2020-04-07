import java.util.Date

import skunk.{Codec, Command, Query}

case class UserName(value: String)
case class PhoneNumber(value: Long)

sealed trait User[F[_]] {
  def userName: UserName
  def phoneNumber: PhoneNumber

//  def addWallet(currency: String): Wallet
//  def deleteWallet(number: String)
//  def getWallet(number: String): Wallet
//
//  def report(from: Date, to: Date): Report
}

case class AnonymousUser(userName: UserName, phoneNumber: PhoneNumber) extends User {

}

case class TrustedUser(userName: UserName, phoneNumber: PhoneNumber) extends User {

}


private object UserQueries {
  val codec: Codec[User ~ EncryptedPassword] =
    (uuid.cimap[UserId] ~ varchar.cimap[UserName] ~ varchar.cimap[EncryptedPassword]).imap {
      case i ~ n ~ p =>
        User(i, n) ~ p
    } {
      case u ~ p =>
        u.id ~ u.name ~ p
    }

  val selectUser: Query[] =
    sql"""
         SELECT * FROM users
         WHERE name = ${varchar.cimap[UserName]}
       """.query(codec)

  val insertUser: Command[User] =
    sql"""
         INSERT INTO users
         VALUES ($codec)
       """.command
}

trait Report {
  def totalBalance(user: User): Int

  def totalIncomePayments(user: User): Int

  def totalOutcomePayments(user: User): Int
}

case class DummyReport() extends Report {
  override def totalBalance(user: User): Int = ???

  override def totalIncomePayments(user: User): Int = ???

  override def totalOutcomePayments(user: User): Int = ???
}