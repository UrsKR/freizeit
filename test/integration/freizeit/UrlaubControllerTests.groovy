package freizeit

import grails.test.ControllerUnitTestCase

class UrlaubControllerTests extends ControllerUnitTestCase {
    def controller = new UrlaubController()

    void testOmitsFractionsIfZero() {
        Urlaubsantrag antrag = createAntragForDays(1)
        controller.antrag(antrag)
        assertEquals '1 Tag', controller.flash.message
    }

    void testUsesPluralFor0Days() {
        Urlaubsantrag antrag = createAntragForDays(0)
        controller.antrag(antrag)
        assertEquals '0 Tage', controller.flash.message
    }

    void testUsesPluralForFractionDays() {
        Urlaubsantrag antrag = createAntragForDays(1.5)
        controller.antrag(antrag)
        assertEquals '1.5 Tage', controller.flash.message
    }

    private Urlaubsantrag createAntragForDays(duration) {
        def antrag = new Urlaubsantrag()
        antrag.feiertagService = [getWorkdays: {range -> duration }]
        return antrag
    }
}