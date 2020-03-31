sealed trait User {
  def fullName: String

  def phone: String
}

object User {

}

case class AnonymousUser(fullName: String, phone: String) extends User {

}

case class TrustedUser(fullName: String, phone: String) extends User {

}

trait Report {
  def totalBalance(user: User): Int

  def totalIncomePayments(user: User): Int

  def totalOutcomePayments(user: User): Int
}