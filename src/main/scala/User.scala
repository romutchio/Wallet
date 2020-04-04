import java.util.Date

case class UserName(value: String)
case class PhoneNumber(value: Long)

sealed trait User {
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