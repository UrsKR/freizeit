package freizeit

import grails.test.GrailsUnitTestCase

class FeiertagServiceTests extends GrailsUnitTestCase {
  def feiertagService = new FeiertagService()

  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testTreatsWeekdaysAsWorkday() {
    def start = getDate(2011, Calendar.JANUARY, 03)
    def end = getDate(2011, Calendar.JANUARY, 05)
    assertWorkdays(start..end, 3)
  }

  void testDoesNotTreatSaturdayAsWorkday() {
    def start = getDate(2011, Calendar.JANUARY, 15)
    def end = getDate(2011, Calendar.JANUARY, 15)
    assertWorkdays(start..end, 0)
  }

  void testDoesNotTreatSundayAsWorkday() {
    def start = getDate(2011, Calendar.JANUARY, 16)
    def end = getDate(2011, Calendar.JANUARY, 16)
    assertWorkdays(start..end, 0)
  }

  void testTreatsNewYearAsHoliday() {
    def start = getDate(2010, Calendar.JANUARY, 01)
    def end = getDate(2010, Calendar.JANUARY, 04)
    assertWorkdays(start..end, 1)
  }

  void testTreatsDreiKoenigeAsHoliday() {
    def start = getDate(2010, Calendar.JANUARY, 06)
    def end = getDate(2010, Calendar.JANUARY, 06)
    assertWorkdays(start..end, 0)
  }

  void testTreatsMayDayAsHoliday() {
    def start = getDate(2012, Calendar.MAY, 01)
    def end = getDate(2012, Calendar.MAY, 01)
    assertWorkdays(start..end, 0)
  }

  void testTreatsDeutscheEinheitAsHoliday() {
    def start = getDate(2012, Calendar.OCTOBER, 03)
    def end = getDate(2012, Calendar.OCTOBER, 03)
    assertWorkdays(start..end, 0)
  }

  void testTreatsAllerheiligenAsHoliday() {
    def start = getDate(2012, Calendar.NOVEMBER, 01)
    def end = getDate(2012, Calendar.NOVEMBER, 01)
    assertWorkdays(start..end, 0)
  }

  void testTreatsChristmayDay1AsHoliday() {
    def start = getDate(2012, Calendar.DECEMBER, 25)
    def end = getDate(2012, Calendar.DECEMBER, 25)
    assertWorkdays(start..end, 0)
  }

  void testTreatsChristmayDay2AsHoliday() {
    def start = getDate(2012, Calendar.DECEMBER, 26)
    def end = getDate(2012, Calendar.DECEMBER, 26)
    assertWorkdays(start..end, 0)
  }

  void testTreatsEaster2010AsHoliday() {
    def start = getDate(2010, Calendar.APRIL, 05)
    def end = getDate(2010, Calendar.APRIL, 05)
    assertWorkdays(start..end, 0)
  }

  void testTreatsEaster2011AsHoliday() {
    def start = getDate(2011, Calendar.APRIL, 25)
    def end = getDate(2011, Calendar.APRIL, 25)
    assertWorkdays(start..end, 0)
  }

  void testTreatsEaster2012AsHoliday() {
    def start = getDate(2012, Calendar.APRIL, 9)
    def end = getDate(2012, Calendar.APRIL, 9)
    assertWorkdays(start..end, 0)
  }

  void testTreatsKarfreitagAsHoliday() {
    def start = getDate(2012, Calendar.APRIL, 6)
    def end = getDate(2012, Calendar.APRIL, 6)
    assertWorkdays(start..end, 0)
  }

  void testTreatsAscensionAsHoliday() {
    def start = getDate(2012, Calendar.MAY, 17)
    def end = getDate(2012, Calendar.MAY, 17)
    assertWorkdays(start..end, 0)
  }

  void testTreatsPentecostAsHoliday() {
    def start = getDate(2012, Calendar.MAY, 28)
    def end = getDate(2012, Calendar.MAY, 28)
    assertWorkdays(start..end, 0)
  }

  void testTreatsCorpusChristiAsHoliday() {
    def start = getDate(2012, Calendar.JUNE, 07)
    def end = getDate(2012, Calendar.JUNE, 07)
    assertWorkdays(start..end, 0)
  }

  private Date getDate(int year, int month, int day) {
    return new GregorianCalendar(year, month, day).time
  }

  private def assertWorkdays(ObjectRange range, int expected) {
    int days = feiertagService.getWorkdays(range)
    assertEquals(expected, days)
  }
}
