package freizeit

import grails.test.GrailsUnitTestCase

class UrlaubstageServiceTests extends GrailsUnitTestCase {
    def service

    protected void setUp() {
        super.setUp()
        service = new UrlaubstageService()
        service.feiertagService = [getWorkdays: {range -> 1}]
    }

    void testReducesNumberOfDaysByHalfADayIfFirstDayIsHalfDay() {
        def tage = service.getUrlaubstage(true, false, new Date()..new Date())
        assertEquals(0.5, tage)
    }

    void testReducesNumberOfDaysByHalfADayIfLastDayIsHalfDay() {
        def tage = service.getUrlaubstage(false, true, new Date()..new Date())
        assertEquals(0.5, tage)
    }

    void testCountsZeroDaysIfThereAreNone() {
        def tage = service.getUrlaubstage(false, true, [])
        assertEquals(0.0, tage)
    }
}