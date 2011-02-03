package freizeit

class Urlaubsantrag {
  def mitarbeiter
  int vorjahresanspruch
  int jahresanspruch
  def typ
  Date firstDay
  Date lastDay

  int getNumberOfDays() {
    return (lastDay - firstDay).toInteger() + 1
  }

  def getResturlaub() {
    int available = vorjahresanspruch + jahresanspruch
    if (typ == 'Erholungsurlaub') {
      return available - numberOfDays
    }
    return available
  }
}