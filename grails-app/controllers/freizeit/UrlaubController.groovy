package freizeit

class UrlaubController {

    static defaultAction = 'antrag'
    def pdfRenderingService

    def antrag = {
        //nothing to do
    }

    def pdf = { Urlaubsantrag antrag ->
        renderPdf(template: '/urlaub/pdf', model: [antrag: antrag])
    }
}