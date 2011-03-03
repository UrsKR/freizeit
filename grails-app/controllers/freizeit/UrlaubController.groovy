package freizeit

import java.text.SimpleDateFormat

class UrlaubController {

    static defaultAction = 'antrag'
    def feiertagService

    def antrag = { Urlaubsantrag antrag ->
        def days = antrag.numberOfDays
        String text = getTextForNumberOfDays(days)
        flash.message = text
        [antrag: antrag]
    }

    def requiredDays = {
        def format = new SimpleDateFormat("dd.MM.yyyy")
        def firstDay = format.parse(params.firstDay)
        def lastDay = format.parse(params.lastDay)
        def days = feiertagService.getWorkdays(firstDay..lastDay)
        render getTextForNumberOfDays(days)
    }

    def pdf = { Urlaubsantrag antrag ->
        renderPdf(template: '/urlaub/pdf', model: [antrag: antrag])
    }

    private String getTextForNumberOfDays(days) {
        def text = days + " Tag"
        if (days > 1) {
            text += 'e';
        }
        text
    }
}