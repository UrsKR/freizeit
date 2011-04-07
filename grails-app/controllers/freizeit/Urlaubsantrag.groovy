package freizeit

class Urlaubsantrag {

    def urlaubstageService

    String mitarbeiter
    float vorjahresanspruch = 0
    float jahresanspruch = 0
    def typ = "Erholungsurlaub"
    Date firstDay
    Date lastDay
    boolean firstOrLastDayIsHalfDay

    {
        def tomorrow = new Date() + 1
        tomorrow.clearTime()
        firstDay = tomorrow;
        lastDay = tomorrow;
    }

    float getNumberOfDays() {
        urlaubstageService.getUrlaubstage(firstOrLastDayIsHalfDay, firstDay..lastDay)
    }

    def getResturlaub() {
        float resturlaub = vorjahresanspruch + jahresanspruch
        float daysRequired = getDaysRequired()
        resturlaub - daysRequired
    }

    private float getDaysRequired() {
        float daysRequired = 0
        if (typ == "Erholungsurlaub") {
            daysRequired = numberOfDays
        }
        daysRequired
    }
}