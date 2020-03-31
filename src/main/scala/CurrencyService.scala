import scalacache._
import scalacache.modes.try_._
import scalacache.serialization.binary._
import scalacache.memcached._
import scalacache.memoization._

import scala.concurrent.duration._
import scala.util.Try

case class ISO(name: String, numCode: Int, charCode: String)

trait CurrencyService {
  // TODO: send request to http://www.cbr.ru/scripts/XML_valFull.asp and parse XML
  def getAllISO(): Try[List[ISO]]

  // TODO: send request to http://www.cbr.ru/scripts/XML_daily.asp?date_req=31/03/2020 and parse XML
  def getCurrencyStat(): Map[ISO, Double]
}



implicit val isoCache: Cache[List[ISO]] = MemcachedCache("localhost:11211")

case class DummyCurrencyService() extends CurrencyService {
  override def getAllISO(): Try[List[ISO]] = memoizeF[Try, List[ISO]](Some(24.hours)) {
    Try {
      ISO ("Russian ruble", 72, "RUB") :: ISO("Dollar", 22, "USD") :: Nil
    }
  }

  override def getCurrencyStat(): Map[ISO, Double] = {
    Map(ISO ("Russian ruble", 72, "RUB") -> 77.45)
  }
}


