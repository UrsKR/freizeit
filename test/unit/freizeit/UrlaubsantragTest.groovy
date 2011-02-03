package freizeit

import grails.test.GrailsUnitTestCase

class UrlaubsantragTest extends GrailsUnitTestCase {

  void setUp() {
    super.setUp()
  }

  void tearDown() {
    super.tearDown()
  }

  void testCalculatesSingleDayIfStartAndEndEqual() {
    def day = new Date()
    def antrag = new Urlaubsantrag(firstDay: day, lastDay: day)
    assertEquals(1, antrag.numberOfDays)
  }

  void testCalculatesTwoDaysIfLastDayFollowsFirstDay() {
    def day = new Date()
    def antrag = new Urlaubsantrag(firstDay: day, lastDay: day.plus(1))
    assertEquals(2, antrag.numberOfDays)
  }

  void testDoesNotReduceAnspruchIfTypeIsNotErholungsurlaub() {
    def antrag = new Urlaubsantrag(jahresanspruch: 5)
    assertEquals(5, antrag.resturlaub)
  }

  void testAddsResturlaubToAnspruch() {
    def antrag = new Urlaubsantrag(vorjahresanspruch: 5, jahresanspruch: 2)
    assertEquals(7, antrag.resturlaub)
  }

  void testDeductsDurationIfTypeIsErholungsurlaub() {
    def day = new Date()
    def antrag = new Urlaubsantrag(firstDay: day, lastDay: day.plus(1), jahresanspruch: 2, typ: 'Erholungsurlaub')
    assertEquals(0, antrag.resturlaub)
  }
}