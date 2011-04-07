package freizeit

import grails.test.ControllerUnitTestCase
import static freizeit.test.UrlaubsantragObjectMother.*

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


}