package freizeit

import grails.test.GrailsUnitTestCase
import static java.util.Calendar.DECEMBER
import static java.util.Calendar.JANUARY

class UrlaubstageService_ChristmasTests extends GrailsUnitTestCase {
    def service

    protected void setUp() {
        super.setUp()
        service = new UrlaubstageService()
    }

    void testReducesByNothingIfItsNotChristmasEve() {
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, false, new Date(2010, DECEMBER, 22)..new Date(2010, DECEMBER, 23))
        assertEquals(2, tage)
    }

    void testReducesNumberOfDaysByHalfADayIfChristmasEveIsPartOfRange() {
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, false, new Date(2010, DECEMBER, 23)..new Date(2010, DECEMBER, 25))
        assertEquals(1.5, tage)
    }

    void testReducesNumberOfDaysByHalfADayIfNewYearsEveIsPartOfRange() {
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, false, new Date(2010, DECEMBER, 30)..new Date(2010, DECEMBER, 31))
        assertEquals(1.5, tage)
    }

    void testSubtractsOnlyHalfADayIfFirstDayIsChristmasEveAndMarkedAsHalf() {
        createFeiertagServiceWithWorkdays(1)
        def tage = service.getUrlaubstage(true, false, new Date(2010, DECEMBER, 24)..new Date(2010, DECEMBER, 25))
        assertEquals(0.5, tage)
    }

    void testSubtractsOnlyHalfADayIfFirstDayIsNewYearsEveAndMarkedAsHalf() {
        createFeiertagServiceWithWorkdays(1)
        def tage = service.getUrlaubstage(true, false, new Date(2010, DECEMBER, 31)..new Date(2011, JANUARY, 01))
        assertEquals(0.5, tage)
    }

    void testSubtractsOnlyHalfADayIfLastDayIsChristmasEveAndMarkedAsHalf() {
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, true, new Date(2010, DECEMBER, 23)..new Date(2010, DECEMBER, 24))
        assertEquals(1.5, tage)
    }

    void testSubtractsOnlyHalfADayIfLastDayIsNewYearsEveAndMarkedAsHalf() {
        createFeiertagServiceWithWorkdays(2)
        def tage = service.getUrlaubstage(false, true, new Date(2010, DECEMBER, 30)..new Date(2010, DECEMBER, 31))
        assertEquals(1.5, tage)
    }

    void testSubtractsHalfADayOnlyIfChristmasEveIsAWorkday() {
        service.feiertagService = [getWorkdays: {range -> 1}, daysThatAreNotOnAWeekend: {Date date -> date?.date != 24}]
        def tage = service.getUrlaubstage(false, false, new Date(2011, DECEMBER, 23)..new Date(2011, DECEMBER, 24))
        assertEquals(1, tage)
    }

    void testSubtractsHalfADayOnlyIfNewYearsEveIsAWorkday() {
        service.feiertagService = [getWorkdays: {range -> 1}, daysThatAreNotOnAWeekend: {Date date -> date?.date != 31}]
        def tage = service.getUrlaubstage(false, false, new Date(2011, DECEMBER, 30)..new Date(2011, DECEMBER, 31))
        assertEquals(1, tage)
    }

    private def createFeiertagServiceWithWorkdays(int workdays) {
        service.feiertagService = [getWorkdays: {range -> workdays}, daysThatAreNotOnAWeekend: {Date date -> true}]
    }
}