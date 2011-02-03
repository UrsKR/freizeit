package freizeit
import static java.util.Calendar.*;
class FeiertagService {

  private static final def newYear = new DayInYear(1, JANUARY)
  private static final def dreiKoenige = new DayInYear(6, JANUARY)
  private static final def mayDay = new DayInYear(1, MAY)
  private static final def deutscheEinheit = new DayInYear(3, OCTOBER)
  private static final def allerheiligen = new DayInYear(1, NOVEMBER)
  private static final def christmas1 = new DayInYear(25, DECEMBER)
  private static final def christmas2 = new DayInYear(26, DECEMBER)
  private static final def fixedHolidays = [newYear, dreiKoenige, mayDay, deutscheEinheit, allerheiligen, christmas1, christmas2]
  private static final def weekend = [SATURDAY, SUNDAY]

  def getWorkdays(Collection days) {
    def notOnAWeekend = getDaysThatAreNotOnAWeekend(days)
    def notAHoliday = getDaysThatAreNotHolidays(notOnAWeekend)
    notAHoliday.size()
  }

  private def getDaysThatAreNotHolidays(Collection collection) {
    collection.findAll daysThatAreNotHolidays
  }

  private def getDaysThatAreNotOnAWeekend(Collection days) {
    days.findAll daysThatAreNotOnAWeekend
  }

  private int getDayOfWeek(date) {
    Calendar calendar = createCalendar(date)
    calendar.get(DAY_OF_WEEK)
  }

  private Calendar createCalendar(date) {
    def calendar = Calendar.instance
    calendar.setTime(date)
    return calendar
  }

  def daysThatAreNotOnAWeekend = {
    int dayOfWeek = getDayOfWeek(it)
    !weekend.contains(dayOfWeek)
  }

  def daysThatAreNotHolidays = {
    !isAFixedHoliday(it) && !isAVariableHoliday(it)
  }

  def isAFixedHoliday(date) {
    DayInYear day = getDayInYear(date)
    fixedHolidays.contains day
  }

  def isAVariableHoliday(date) {
    def calendar = createCalendar(date)
    def year = calendar.get(Calendar.YEAR)
    def candidate = getDayInYear(date)
    def variableHolidays = getVariableHolidays(year)
    variableHolidays.contains(candidate)
  }

  private def getVariableHolidays(year) {
    def easterSunday = getEasterSunday(year)
    def easterMonday = getDayInYear(easterSunday + 1)
    def goodFriday = getDayInYear(easterSunday - 2)
    def ascension = getDayInYear(easterSunday + 39)
    def pentecost = getDayInYear(easterSunday + 50)
    def corpusChristi = getDayInYear(easterSunday + 60)
    [goodFriday, easterMonday, ascension, pentecost,corpusChristi]
  }

  private DayInYear getDayInYear(date) {
    def calendar = createCalendar(date)
    def day = calendar.get(DAY_OF_MONTH)
    def month = calendar.get(MONTH)
    new DayInYear(day, month)
  }

  //As per http://www.hib-wien.at/leute/wurban/mathematik/Ostern/Osterdatum.html
  private def getEasterSunday(year) {
    int a = year % 19;
    int b = year / 100;
    int c = year % 100;
    int d = b / 4;
    int e = b % 4;
    int f = (b + 8) / 25;
    int g = (b - f + 1) / 3;
    int h = (19 * a + b - d - g + 15) % 30;
    int i = c / 4;
    int j = c % 4;
    int k = (32 + 2 * e + 2 * i - h - j) % 7;
    int l = (a + 11 * h + 22 * k) / 451;
    int x = h + k - 7 * l + 114;
    int month = (x / 31) - 1;      //-1: Adjustment for Java Calendar Notation
    int day = (x % 31) + 1;
    return new GregorianCalendar(year, month, day).time;
  }
}