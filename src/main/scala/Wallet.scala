trait Wallet {
  def currency: String
  def insert()
  def withdraw()
  def transfer()
}

object Wallet {

}

case class WalletInstance(currency: String) extends Wallet {
  override def insert(): Unit = ???

  override def withdraw(): Unit = ???

  override def transfer(): Unit = ???
}

trait CurrencyService {
  // TODO: send request to http://www.cbr.ru/scripts/XML_valFull.asp and parse XML
  def getAllISO(): List[ISO]

  // TODO: send request to http://www.cbr.ru/scripts/XML_daily.asp?date_req=31/03/2020 and parse XML
  def getCurrencyStat(): Map[ISO, Double]
}

case class ISO(name: String, numCode: Int, charCode: String)

