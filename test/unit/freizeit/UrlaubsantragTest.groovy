package freizeit

import grails.test.GrailsUnitTestCase
import java.text.DecimalFormat

class UrlaubsantragTest extends GrailsUnitTestCase {

    void setUp() {
        super.setUp()
    }

    void tearDown() {
        super.tearDown()
    }

    void testRequestsDurationFromService() {
        def antrag = new Urlaubsantrag()
        antrag.feiertagService = [getWorkdays: {range -> 17}]
        assertEquals antrag.getNumberOfDays(), 17
    }

    void testDoesNotReduceAnspruchIfTypeIsNotErholungsurlaub() {
        def antrag = new Urlaubsantrag(jahresanspruch: 5, typ: "Nicht Urlaub")
        assertEquals(5, antrag.resturlaub)
    }

    void testAddsResturlaubToAnspruch() {
        def antrag = new Urlaubsantrag(vorjahresanspruch: 5, jahresanspruch: 2, typ: "Nicht Urlaub")
        assertEquals(7, antrag.resturlaub)
    }

    void testDeductsDurationIfTypeIsErholungsurlaub() {
        def antrag = new Urlaubsantrag(jahresanspruch: 2, typ: 'Erholungsurlaub')
        antrag.feiertagService = [getWorkdays: {range -> 2}]
        assertEquals(0, antrag.resturlaub)
    }

    void testInitializesFirstDayToTomorrow() {
        Date tomorrow = tomorrowWithoutTime()
        assertEquals new Urlaubsantrag().firstDay, tomorrow
    }

    void testInitializesLastDayToTomorrow() {
        def tomorrow = tomorrowWithoutTime()
        assertEquals new Urlaubsantrag().lastDay, tomorrow
    }

    void testIsAbleToCalculateHalfDaysInJahresanspruch() {
        def antrag = new Urlaubsantrag(jahresanspruch: 5.5, typ: "Nicht Urlaub")
        assertEquals(5.5, antrag.resturlaub)
    }

    void testIsAbleToCalculateHalfDaysInResturlaub() {
        def antrag = new Urlaubsantrag(vorjahresanspruch: 5.5, typ: "Nicht Urlaub")
        assertEquals(5.5, antrag.resturlaub)
    }

    void testIsAbleToCalculateHalfDaysForRequiredDays() {
        def antrag = new Urlaubsantrag(jahresanspruch: 5, typ: "Erholungsurlaub")
        antrag.feiertagService = [getWorkdays: {range -> 0.5}]
        assertEquals(4.5, antrag.resturlaub)
    }

    private Date tomorrowWithoutTime() {
        def tomorrow = new Date() + 1
        tomorrow.clearTime()
        return tomorrow
    }
}