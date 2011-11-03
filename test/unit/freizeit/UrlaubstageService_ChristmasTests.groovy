package freizeit

import grails.test.GrailsUnitTestCase
import static java.util.Calendar.DECEMBER

class UrlaubstageService_ChristmasTests extends GrailsUnitTestCase {
    def service

    protected void setUp() {
        super.setUp()
        service = new UrlaubstageService()
    }

    void testReducesNumberOfDaysByHalfADayIfChristmasEveIsPartOfRange() {
        service.feiertagService = [getWorkdays: {range -> 2}]
        def tage = service.getUrlaubstage(false, false, new Date(2011, DECEMBER, 23)..new Date(2011, DECEMBER, 25))
        assertEquals(1.5, tage)
    }

    void testUsesFirstDayFlagAsConfirmationThatChristmasEveIsHalfADay() {
        service.feiertagService = [getWorkdays: {range -> 1}]
        def tage = service.getUrlaubstage(true, false, new Date(2011, DECEMBER, 24)..new Date(2011, DECEMBER, 25))
        assertEquals(0.5, tage)
    }

    void testUsesLastDayFlagAsConfirmationThatChristmasEveIsHalfADay() {
        service.feiertagService = [getWorkdays: {range -> 2}]
        def tage = service.getUrlaubstage(false, true, new Date(2011, DECEMBER, 23)..new Date(2011, DECEMBER, 24))
        assertEquals(1.5, tage)
    }
}