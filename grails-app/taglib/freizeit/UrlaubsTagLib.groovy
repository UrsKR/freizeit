package freizeit

import java.math.RoundingMode

class UrlaubsTagLib {

    static namespace = 'urlaub'

    def formatDate = { arguments ->
        out << arguments.date.format('dd.MM.yyyy')
    }

    def formatDayCount = { arguments ->
        def days = arguments.days
        if (fractionIsOneHalf(days)) {
            out << printWithLigature(days)
        } else {
            out << printWithFraction(days)
        }
    }

    private def printWithLigature(days) {
        def formattedDays = g.formatNumber(format: '0', number: days, roundingMode: RoundingMode.HALF_DOWN)
        formattedDays + '½'
    }

    private boolean fractionIsOneHalf(days) {
        def hasFraction = (days as int) != days
        def fractionIsOneHalf = (2 * days) == ((2 * days) as int)
        hasFraction && fractionIsOneHalf
    }

    private def printWithFraction(days) {
        g.formatNumber(format: '0.#', number: days)
    }
}