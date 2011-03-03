package freizeit

class UrlaubsPdfTagLib {

  static namespace = 'urlaub'

  def formatDate = { arguments ->
    out << arguments.date.format('dd.MM.yyyy')
  }
}