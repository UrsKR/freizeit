package freizeit

import java.text.SimpleDateFormat

class UrlaubController {

    private static final int oneYearInSeconds = 60 * 60 * 24 * 365
    public static final String Cookie_Name = "Name"
    public static final String Cookie_Vorjahresanspruch = "Vorjahresanspruch"
    public static final String Cookie_Jahresanspruch = "Jahresanspruch"
    static defaultAction = 'antrag'
    def urlaubstageService
    def pdfRenderingService

    def antrag = { Urlaubsantrag antrag ->
        antrag.mitarbeiter = request.getCookie(Cookie_Name) ?: ""
        antrag.vorjahresanspruch = request.getCookie(Cookie_Vorjahresanspruch) as Float ?: 0
        antrag.jahresanspruch = request.getCookie("Jahresanspruch") as Float ?: 0
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
        response.setCookie(Cookie_Name, antrag.mitarbeiter, oneYearInSeconds)
        float restVorjahresanspruch = Math.max(antrag.vorjahresanspruch - antrag.numberOfDays, 0)
        response.setCookie(Cookie_Vorjahresanspruch, restVorjahresanspruch as String, oneYearInSeconds)
        float restJahresanspruch = restVorjahresanspruch > 0 ? antrag.jahresanspruch : antrag.jahresanspruch - (antrag.numberOfDays - antrag.vorjahresanspruch)
        response.setCookie(Cookie_Jahresanspruch, restJahresanspruch as String, oneYearInSeconds)
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