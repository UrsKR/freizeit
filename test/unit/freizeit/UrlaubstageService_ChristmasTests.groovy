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
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, false, new Date(2010, DECEMBER, 23)..new Date(2010, DECEMBER, 25))
        assertEquals(1.5, tage)
    }

    void testSubtractsOnlyHalfADayIfFirstDayIsChristmasEveAndMarkedAsHalf() {
        createFeiertagServiceWithWorkdays(1)
        def tage = service.getUrlaubstage(true, false, new Date(2010, DECEMBER, 24)..new Date(2010, DECEMBER, 25))
        assertEquals(0.5, tage)
    }

    void testSubtractsOnlyHalfADayIfLastDayIsChristmasEveAndMarkedAsHalf() {
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, true, new Date(2010, DECEMBER, 23)..new Date(2010, DECEMBER, 24))
        assertEquals(1.5, tage)
    }

    void testSubtractsHalfADayOnlyIfChristmasEveIsAWorkday() {
        service.feiertagService = [getWorkdays: {range -> 1}, daysThatAreNotOnAWeekend: {Date date -> date.date != 24}]
        def tage = service.getUrlaubstage(false, false, new Date(2011, DECEMBER, 23)..new Date(2011, DECEMBER, 24))
        assertEquals(1, tage)
    }

    private def createFeiertagServiceWithWorkdays(int workdays) {
        service.feiertagService = [getWorkdays: {range -> workdays}, daysThatAreNotOnAWeekend: {Date date -> true}]
    }
}