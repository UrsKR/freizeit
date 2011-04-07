package freizeit

class UrlaubsTagLib {

    static namespace = 'urlaub'

    def formatDate = { arguments ->
        out << arguments.date.format('dd.MM.yyyy')
    }

    def formatDayCount = { arguments ->
        def days = arguments.days
        def formattedDays = g.formatNumber(format: '0.#', number: days)
        out << formattedDays
    }
}