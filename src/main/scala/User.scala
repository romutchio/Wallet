import java.util.Date

sealed trait User {
  def fullName: String
  def phone: String

  def addWallet(currency: String): Wallet
  def deleteWallet(number: String)
  def getWallet(number: String): Wallet

  def report(from: Date, to: Date): Report
}

object User {

}

case class AnonymousUser(fullName: String, phone: String) extends User {
  override def addWallet(currency: String): Wallet = ???

  override def deleteWallet(number: String): Unit = ???

  override def getWallet(number: String): Wallet = ???

  override def report(from: Date, to: Date): Report = ???
}

case class TrustedUser(fullName: String, phone: String) extends User {
  override def addWallet(currency: String): Wallet = ???

  override def deleteWallet(number: String): Unit = ???

  override def getWallet(number: String): Wallet = ???

  override def report(from: Date, to: Date): Report = ???
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