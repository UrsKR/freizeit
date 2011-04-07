package freizeit

import grails.test.GrailsUnitTestCase
import static freizeit.test.UrlaubsantragObjectMother.*

class UrlaubsantragTest extends GrailsUnitTestCase {

    void setUp() {
        super.setUp()
    }

    void tearDown() {
        super.tearDown()
    }

    void testRequestsDurationFromService() {
        def antrag = createAntragForDays(17)
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
        def antrag = createAntragForDays(2)
        antrag.jahresanspruch = 2
        antrag.typ = 'Erholungsurlaub'
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
        def antrag = createAntragForDays(0.5)
        antrag.jahresanspruch = 5
        antrag.typ = 'Erholungsurlaub'
        assertEquals(4.5, antrag.resturlaub)
    }

    private Date tomorrowWithoutTime() {
        def tomorrow = new Date() + 1
        tomorrow.clearTime()
        return tomorrow
    }
}