import java.util.Date

trait Wallet {
  def walletNumber: String
  def currency: String
  def insert(amount: Int)
  def withdraw(amount: Int)
  def transfer(to: Wallet, amount: Int)
  def getHistory(from: Date, to: Date): String
}

object Wallet {

}

case class WalletInstance(currency: String) extends Wallet {
  override def walletNumber: String = ???

  override def insert(amount: Int): Unit = ???

  override def withdraw(amount: Int): Unit = ???

  override def transfer(to: Wallet, amount: Int): Unit = ???

  override def getHistory(from: Date, to: Date): String = ???
}
