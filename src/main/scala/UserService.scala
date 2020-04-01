import java.util.Date

trait UserService {
  def createUser(fullname: String, phone: String): User

  def createWallet(user: User, currency: String): Wallet
  def deleteWallet(user: User, wallet: Wallet)

  def insertToWallet(user: User, number: String, amount: Int)
  def insertToWalletWithNumber(phoneNumber: String, amount: Int)

  def transfer(from: Wallet, to: Wallet, amount: Int)
  def withdrawFromWallet(wallet: Wallet, amount: Int)


  def requestWalletHistory(wallet: Wallet, from: Date, to: Date)
  def requestReport(user: User, from: Date, to: Date)


  def insertToWalletPrecalculate(walletNumber: String, amount: Int)
  def insertToWalletWithNumberPrecalculate(phoneNumber: String, amount: Int)
  def transferPrecalculate(from: Wallet, to: Wallet, amount: Int)
  def withdrawFromWalletPrecalculate(wallet: Wallet, amount: Int)

}


case class DummyUserService() extends UserService {
  override def createUser(fullname: String, phone: String): User = AnonymousUser(fullname, phone)

  override def createWallet(user: User, currency: String): Wallet = user.addWallet(currency)

  override def deleteWallet(user: User, wallet: Wallet): Unit = user.deleteWallet(wallet.walletNumber)

  override def insertToWallet(user: User, number: String, amount: Int): Unit = user.getWallet(number).insert(amount)

  override def insertToWalletWithNumber(phoneNumber: String, amount: Int): Unit = ???

  override def transfer(from: Wallet, to: Wallet, amount: Int): Unit = from.transfer(to, amount)

  override def withdrawFromWallet(wallet: Wallet, amount: Int): Unit = wallet.withdraw(amount)

  override def requestWalletHistory(wallet: Wallet, from: Date, to: Date): Unit = wallet.getHistory(from, to)

  override def requestReport(user: User, from: Date, to: Date): Unit = user.report(from, to)

  override def insertToWalletPrecalculate(walletNumber: String, amount: Int): Unit = ???

  override def insertToWalletWithNumberPrecalculate(phoneNumber: String, amount: Int): Unit = ???

  override def transferPrecalculate(from: Wallet, to: Wallet, amount: Int): Unit = ???

  override def withdrawFromWalletPrecalculate(wallet: Wallet, amount: Int): Unit = ???
}