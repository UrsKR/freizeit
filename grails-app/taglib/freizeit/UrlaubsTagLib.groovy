package freizeit

class UrlaubsTagLib {

    static namespace = 'urlaub'
    static final String FORMAT = 'dd.MM.yyyy'

    def formatDate = { arguments ->
        out << arguments.date.format(FORMAT)
    }

    def formatDayCount = { arguments ->
        def days = arguments.days
        if (fractionIsOneHalf(days)) {
            out << printWithLigature(days)
        } else {
            out << printWithFraction(days)
        }
    }

    private boolean fractionIsOneHalf(days) {
        def hasFraction = (days as int) != days
        def fractionIsOneHalf = (2 * days) == ((2 * days) as int)
        hasFraction && fractionIsOneHalf
    }

    private def printWithLigature(days) {
        if (days == 0.5) {
            return '½'
        }
        def dayString = (days as String) - '.5'
        dayString + '½'
    }

    private def printWithFraction(days) {
        g.formatNumber(format: '0.#', number: days)
    }
}