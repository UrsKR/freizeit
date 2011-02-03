package freizeit

class UrlaubController {

  static defaultAction = 'antrag'

  def antrag = {
    //nothing to do
  }

  def pdf = { Urlaubsantrag antrag ->
    [antrag: antrag]
  }
}