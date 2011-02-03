package freizeit

class Urlaubsantrag {

  def mitarbeiter
  int vorjahresanspruch
  int jahresanspruch
  def typ
  Date firstDay
  Date lastDay

  int getNumberOfDays() {
    new FeiertagService().getWorkdays(firstDay..lastDay)
  }

  def getResturlaub() {
    int available = vorjahresanspruch + jahresanspruch
    int resturlaub = available;
    if (typ == 'Erholungsurlaub') {
      resturlaub -= numberOfDays
    }
    jahresanspruch != 0 ? resturlaub : null
  }

  def getJahresanspruch() {
    getNullOrValue jahresanspruch
  }

  def getVorjahresanspruch() {
    getNullOrValue vorjahresanspruch
  }

  private def getNullOrValue(int value) {
    value != 0 ? value : null
  }
}