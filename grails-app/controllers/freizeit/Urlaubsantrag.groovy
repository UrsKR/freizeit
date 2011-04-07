package freizeit

class Urlaubsantrag {

    def feiertagService

    String mitarbeiter
    def vorjahresanspruch = 0
    def jahresanspruch = 0
    def typ = "Erholungsurlaub"
    Date firstDay
    Date lastDay

    {
        def tomorrow = new Date() + 1
        tomorrow.clearTime()
        firstDay = tomorrow;
        lastDay = tomorrow;
    }

    int getNumberOfDays() {
        feiertagService.getWorkdays(firstDay..lastDay)
    }

    def getResturlaub() {
        int available = vorjahresanspruch + jahresanspruch
        int resturlaub = available
        int daysRequired = 0
        if (typ == "Erholungsurlaub") {
            daysRequired = numberOfDays
        }
        resturlaub -= daysRequired
        jahresanspruch.toInteger() != 0 ? resturlaub : null
    }
}