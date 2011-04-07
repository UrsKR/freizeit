package freizeit.test

import freizeit.Urlaubsantrag

class UrlaubsantragObjectMother {

    static Urlaubsantrag createAntragForDays(duration) {
        def antrag = new Urlaubsantrag()
        antrag.feiertagService = [getWorkdays: {range -> duration }]
        return antrag
    }
}