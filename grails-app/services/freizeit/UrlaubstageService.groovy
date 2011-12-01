package freizeit

import static java.util.Calendar.DECEMBER

class UrlaubstageService {

    private static final def christmasEve = new DayInYear(24, DECEMBER)
    def feiertagService

    def getUrlaubstage(boolean firstDayIsHalf, boolean lastDayIsHalf, Collection days) {
        if (!days){
            return 0;
        }
        float dayCount = feiertagService.getWorkdays(days)
        boolean firstDayIsChristmasEve = DayInYear.From(days.first()) == christmasEve
        boolean lastDayIsChristmasEve = DayInYear.From(days.last()) == christmasEve
        if (firstDayIsHalf && !firstDayIsChristmasEve) {
            dayCount -= 0.5
        }
        if (lastDayIsHalf && !lastDayIsChristmasEve) {
            dayCount -= 0.5
        }
        Date maybeChristmas = days.find {Date day ->  DayInYear.From(day) == christmasEve}
        boolean containsChristmasEve = maybeChristmas as boolean
        boolean christmasEveIsAWorkday = feiertagService.daysThatAreNotOnAWeekend(maybeChristmas)
        if (containsChristmasEve && christmasEveIsAWorkday) {
            dayCount -= 0.5
        }
        dayCount
    }
}