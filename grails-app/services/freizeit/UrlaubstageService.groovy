package freizeit

class UrlaubstageService {

    def getRequiredUrlaubstage(typ, Collection days) {
        if (typ != "Erholungsurlaub") {
            return 0
        }
        new FeiertagService().getWorkdays(days)
    }
}
