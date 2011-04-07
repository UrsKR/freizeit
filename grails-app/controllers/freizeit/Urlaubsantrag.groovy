package freizeit

class Urlaubsantrag {

    def feiertagService

    String mitarbeiter
    int vorjahresanspruch = 0
    int jahresanspruch = 0
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
        def resturlaub = vorjahresanspruch + jahresanspruch
        int daysRequired = getDaysRequired()
        resturlaub -= daysRequired
        jahresanspruch.toInteger() != 0 ? resturlaub : null
    }

    private int getDaysRequired() {
        int daysRequired = 0
        if (typ == "Erholungsurlaub") {
            daysRequired = numberOfDays
        }
        daysRequired
    }
}