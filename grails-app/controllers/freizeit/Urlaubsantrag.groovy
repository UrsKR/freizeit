package freizeit

class Urlaubsantrag {

    def feiertagService

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
        float days = feiertagService.getWorkdays(firstDay..lastDay)
        if (firstOrLastDayIsHalfDay) {
            days -= 0.5
        }
        days
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