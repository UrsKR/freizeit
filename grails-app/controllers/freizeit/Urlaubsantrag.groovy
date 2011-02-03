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
    resturlaub
  }

  def getJahresanspruch() {
    jahresanspruch != 0 ? jahresanspruch : null
  }

  def getVorjahresanspruch() {
    vorjahresanspruch != 0 ? vorjahresanspruch : null
  }
}