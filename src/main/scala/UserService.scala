import java.util.Date


trait UserService {
  //TODO: add more methods
  def createUser(fullname: String, phone: String): User

  def createWallet(user: User): Wallet
  def deleteWallet(user: User, wallet: Wallet)
  def insertToWallet(wallet: Wallet)

  def transfer(from: Wallet, to: Wallet)
  def withdrawFromWallet(wallet: Wallet, amount: Int)


  def requestWalletHistory(wallet: Wallet, from: Date, to: Date)


  def insertToWalletPrecalculate(wallet: Wallet)
  def transferPrecalculate(from: Wallet, to: Wallet)
  def withdrawFromWalletPrecalculate(wallet: Wallet, amount: Int)

}