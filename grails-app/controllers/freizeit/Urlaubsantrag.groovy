package freizeit

class Urlaubsantrag {

    def feiertagService

    def mitarbeiter
    int vorjahresanspruch
    int jahresanspruch
    def typ
    Date firstDay
    Date lastDay

    int getNumberOfDays() {
        feiertagService.getWorkdays(firstDay..lastDay)
    }

    def getResturlaub() {
        int available = vorjahresanspruch + jahresanspruch
        int resturlaub = available;
        int daysRequired = numberOfDays
        resturlaub -= daysRequired
        jahresanspruch.toInteger() != 0 ? resturlaub : null
    }
}