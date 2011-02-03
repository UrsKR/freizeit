package freizeit

import freizeit.UrlaubController.Urlaubsantrag
import grails.test.GrailsUnitTestCase

class UrlaubsantragTest extends GrailsUnitTestCase {

  def day = new Date()
  def antrag = new Urlaubsantrag(mitarbeiter: 'Anwender', jahresanspruch: 28, vorjahresanspruch: 3, firstDay: day, lastDay: day, typ: "nix")

  void setUp() {
    super.setUp()
  }

  void tearDown() {
    super.tearDown()
  }

  void testCalculatesSingleDayIfStartAndEndEqual() {

  }
}
