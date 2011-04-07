package freizeit

import grails.test.GrailsUnitTestCase

class UrlaubstageServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testReducesNumberOfDaysByHalfADayIfFlagIsSet() {
        def service = new UrlaubstageService()
        service.feiertagService = [getWorkdays: {range -> 1}]
        def tage = service.getUrlaubstage(true, new Date()..new Date())
        assertEquals(0.5, tage)
    }
}
