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
        int daysRequired = new UrlaubstageService().getRequiredUrlaubstage(typ, firstDay..lastDay)
        resturlaub -= daysRequired
        jahresanspruch.toInteger() != 0 ? resturlaub : null
    }
}