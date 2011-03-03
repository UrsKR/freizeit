package freizeit

class Urlaubsantrag {

    def feiertagService

    def mitarbeiter
    int vorjahresanspruch
    int jahresanspruch
    def typ = "Erholungsurlaub"
    Date firstDay = new Date() + 1
    Date lastDay = new Date() + 1

    int getNumberOfDays() {
        feiertagService.getWorkdays(firstDay..lastDay)
    }

    def getResturlaub() {
        int available = vorjahresanspruch + jahresanspruch
        int resturlaub = available
        int daysRequired = 0
        if (typ=="Erholungsurlaub"){
            daysRequired = numberOfDays
        }
        resturlaub -= daysRequired
        jahresanspruch.toInteger() != 0 ? resturlaub : null
    }
}