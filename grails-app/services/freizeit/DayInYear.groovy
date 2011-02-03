package freizeit

class DayInYear {
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
