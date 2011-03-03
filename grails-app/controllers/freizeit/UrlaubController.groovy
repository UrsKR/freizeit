package freizeit

class UrlaubController {

    static defaultAction = 'antrag'
    def feiertagService

    def antrag = { Urlaubsantrag antrag ->
        def tomorrow = new Date() + 1
        if (!antrag.firstDay) {
            antrag.firstDay = tomorrow;
        }
        if (!antrag.lastDay) {
            antrag.lastDay = tomorrow;
        }
        if (!antrag.typ) {
            antrag.typ = "Erholungsurlaub"
        }
        def days = feiertagService.getWorkdays(antrag.firstDay..antrag.lastDay)
        def text = days + " Tag"
        if (days > 1) {
            text += 'e';
        }
        flash.message = text
        [antrag: antrag]
    }

    def pdf = { Urlaubsantrag antrag ->
        renderPdf(template: '/urlaub/pdf', model: [antrag: antrag])
    }
}