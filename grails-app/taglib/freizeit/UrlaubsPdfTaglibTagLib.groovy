package freizeit

class UrlaubsPdfTaglibTagLib {

  static namespace = 'urlaub'

  def formatDate = { arguments ->
    out << arguments.date.format('dd.MM.yyyy')
  }
}