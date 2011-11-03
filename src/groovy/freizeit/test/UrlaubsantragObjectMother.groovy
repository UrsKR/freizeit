package freizeit.test

import freizeit.Urlaubsantrag

class UrlaubsantragObjectMother {

    static Urlaubsantrag createAntragForDays(duration) {
        def antrag = new Urlaubsantrag()
        antrag.urlaubstageService = [getUrlaubstage: {firstHalf, lastHalf, range -> duration }]
        return antrag
    }
}