package freizeit

import static java.util.Calendar.DECEMBER

class UrlaubstageService {

    private static final def christmasEve = new DayInYear(24, DECEMBER)
    private static final def newYearsEve = new DayInYear(31, DECEMBER)
    private static final def halfWorkDays = [christmasEve, newYearsEve]
    def feiertagService

    def getUrlaubstage(boolean firstDayIsHalf, boolean lastDayIsHalf, Collection days) {
        if (!days) {
            return 0;
        }
        float dayCount = feiertagService.getWorkdays(days)
        dayCount -= getNumberOfDaysToSubtractFromLeaveDaysForFirstDay(days, firstDayIsHalf)
        dayCount -= getNumberOfDaysToSubtractFromLeaveDaysForLastDay(days, lastDayIsHalf)
        Date maybeChristmas = days.find {Date day ->  DayInYear.From(day) == christmasEve}
        Date maybeNewYear = days.find {Date day ->  DayInYear.From(day) == newYearsEve}
        boolean containsChristmasEve = maybeChristmas as boolean
        boolean containsNewYearsEve = maybeNewYear as boolean
        boolean christmasEveIsAWorkday = feiertagService.daysThatAreNotOnAWeekend(maybeChristmas)
        boolean newYearsEveIsAWorkday = feiertagService.daysThatAreNotOnAWeekend(maybeNewYear)
        if ((containsChristmasEve && christmasEveIsAWorkday) || (containsNewYearsEve && newYearsEveIsAWorkday)) {
            dayCount -= 0.5
        }
        dayCount
    }

    private float getNumberOfDaysToSubtractFromLeaveDaysForFirstDay(Collection days, boolean firstDayIsHalf) {
        boolean firstDayIsAlreadyHalfDay = halfWorkDays.contains(DayInYear.From(days.first()))
        if (firstDayIsHalf && !firstDayIsAlreadyHalfDay) {
            return 0.5
        }
        else {
            return 0
        }
    }

    private float getNumberOfDaysToSubtractFromLeaveDaysForLastDay(Collection days, boolean lastDayIsHalf) {
        boolean lastDayIsAlreadyHalfDay = halfWorkDays.contains(DayInYear.From(days.last()))
        if (lastDayIsHalf && !lastDayIsAlreadyHalfDay) {
            return 0.5
        }
        return 0
    }
}