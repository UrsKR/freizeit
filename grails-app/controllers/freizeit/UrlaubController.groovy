package freizeit

import java.text.SimpleDateFormat

class UrlaubController {

    static defaultAction = 'antrag'
    def feiertagService
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
        def firstDayIsHalf = params.firstDayIsHalfDay
        def lastDayIsHalf = params.lastDayIsHalfDay
        def days = feiertagService.getWorkdays(firstDay..lastDay)
        if (firstDayIsHalf) {
            days -= 0.5;
        }
        if (lastDayIsHalf) {
            days -= 0.5;
        }
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