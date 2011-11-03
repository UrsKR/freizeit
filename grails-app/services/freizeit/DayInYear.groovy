package freizeit

import static freizeit.CalendarUtil.createCalendar
import static java.util.Calendar.DAY_OF_MONTH
import static java.util.Calendar.MONTH

class DayInYear {
    static DayInYear From(Date date) {
        def calendar = createCalendar(date)
        def day = calendar.get(DAY_OF_MONTH)
        def month = calendar.get(MONTH)
        new DayInYear(day, month)
    }

    def dayInMonth;
    def month;

    DayInYear(dayInMonth, month) {
        this.dayInMonth = dayInMonth
        this.month = month
    }

    boolean equals(Object object) {
        if (!(object instanceof DayInYear)) {
            return false
        }
        return dayInMonth == object.dayInMonth && month == object.month
    }

    int hashCode() {
        return dayInMonth * month
    }
}
