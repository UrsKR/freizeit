package freizeit

class UrlaubstageService {

    def feiertagService

    def getUrlaubstage(boolean firstOrLastDayIsHalf, Collection days) {
        float dayCount = feiertagService.getWorkdays(days)
        if (firstOrLastDayIsHalf) {
            dayCount -= 0.5
        }
        dayCount
    }
}
