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
        halfWorkDays.each {
            if (shouldSubtractFor(days, it)) {
                dayCount -= 0.5
            }
        }
        dayCount
    }

    private boolean shouldSubtractFor(Collection days, DayInYear halfDay) {
        Date mayBeHalfDay = days.find {Date day -> DayInYear.From(day) == halfDay}
        boolean rangeContainsHalfDay = mayBeHalfDay as boolean
        if (!rangeContainsHalfDay){
            return false;
        }
        return feiertagService.daysThatAreNotOnAWeekend(mayBeHalfDay)
    }

    private float getNumberOfDaysToSubtractFromLeaveDaysForFirstDay(Collection days, boolean isMarkedAsHalf) {
        getNumberOfDaysToSubtract(days.first(), isMarkedAsHalf)
    }

    private float getNumberOfDaysToSubtractFromLeaveDaysForLastDay(Collection days, boolean isMarkedAsHalf) {
        getNumberOfDaysToSubtract(days.last(), isMarkedAsHalf)
    }

    private BigDecimal getNumberOfDaysToSubtract(day, boolean isMarkedAsHalf) {
        boolean dayIsAlreadyHalfDay = halfWorkDays.contains(DayInYear.From(day))
        if (isMarkedAsHalf && !dayIsAlreadyHalfDay) {
            return 0.5
        }
        return 0
    }
}