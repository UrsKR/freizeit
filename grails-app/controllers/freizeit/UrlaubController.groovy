package freizeit

import java.text.SimpleDateFormat

class UrlaubController {

    static defaultAction = 'antrag'
    def urlaubstageService
    def pdfRenderingService

    def antrag = { Urlaubsantrag antrag ->
        def days = antrag.numberOfDays
        String text = getTextForNumberOfDays(days)
        flash.message = text
        [antrag: antrag]
    }

    def requiredDays = {
        def format = new SimpleDateFormat(UrlaubsTagLib.FORMAT)
        def firstDay = format.parse(params.firstDay)
        def lastDay = format.parse(params.lastDay)
        boolean firstDayIsHalf = params.boolean('firstDayIsHalfDay')
        boolean lastDayIsHalf = params.boolean('lastDayIsHalfDay')
        def days = urlaubstageService.getUrlaubstage(firstDayIsHalf, lastDayIsHalf, firstDay..lastDay)
        render getTextForNumberOfDays(days)
    }

    def pdf = { Urlaubsantrag antrag ->
        pdfRenderingService.render([template: '/urlaub/pdf', model: [antrag: antrag]], response)
    }

    private String getTextForNumberOfDays(days) {
        def formattedDays = urlaub.formatDayCount(days: days)
        def text = formattedDays + " Tag"
        if (days != 1 && days != 0.5) {
            text += 'e';
        }
        text
    }
}