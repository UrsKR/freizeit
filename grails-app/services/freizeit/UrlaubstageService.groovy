package freizeit

class UrlaubstageService {

    def feiertagService

    def getUrlaubstage(boolean firstDayIsHalf, boolean lastDayIsHalf, Collection days) {
        float dayCount = feiertagService.getWorkdays(days)
        if (firstDayIsHalf) {
            dayCount -= 0.5
        }
        if (lastDayIsHalf) {
            dayCount -= 0.5
        }
        dayCount
    }
}