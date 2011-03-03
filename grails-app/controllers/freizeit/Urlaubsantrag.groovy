package freizeit

class Urlaubsantrag {

    def feiertagService

    def mitarbeiter
    int vorjahresanspruch
    int jahresanspruch
    def typ = "Erholungsurlaub"
    Date firstDay
    Date lastDay

    {
        def tomorrow = new Date() + 1
        def calendar = new GregorianCalendar()
        calendar.time = tomorrow
        calendar.set Calendar.HOUR_OF_DAY, 0
        calendar.set Calendar.MINUTE, 0
        calendar.set Calendar.SECOND, 0
        calendar.set Calendar.MILLISECOND, 0
        firstDay = calendar.time;
        lastDay = calendar.time;
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