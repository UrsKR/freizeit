package freizeit

class CalendarUtil {

    public static Calendar createCalendar(date) {
        def calendar = Calendar.instance
        calendar.setTime(date)
        calendar
    }
}