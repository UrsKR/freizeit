<html>
<head>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='urlaubsantrag.css'/>"/>
</head>
<body>
<div class="title"><h1>URLAUBSMITTEILUNG</h1></div>
<div class="body">
  <table>
    <g:render template="zeile" model="${[label: 'Mitarbeiter', content:antrag.mitarbeiter]}"/>
    <g:render template="zeile" model="${[label: 'Urlaubsanspruch aus Vorjahr', content:antrag.vorjahresanspruch]}"/>
    <g:render template="zeile" model="${[label: ' Urlaubsanspruch pro Jahr', content:antrag.jahresanspruch]}"/>
    <tr>
      <td>
        Erholungsurlaub
      </td>
      <td>
        <g:render template="typcheckbox" model="${[checked:'Erholungsurlaub'==antrag.typ]}"/>
      </td>
    </tr>
    <tr>
      <td>
        Freizeit
      </td>
      <td>
        <g:render template="typcheckbox" model="${[checked:'Überstundenausgleich'==antrag.typ]}"/>
      </td>
    </tr>
    <tr>
      <td>
        Zeitraum
      </td>
      <td>
        <urlaub:formatDate date="${antrag.firstDay}"/> - <urlaub:formatDate date="${antrag.lastDay}"/>
      </td>
    </tr>
    <g:render template="zeile" model="${[label: 'Anzahl Tage', content:antrag.numberOfDays]}"/>
    <g:render template="zeile" model="${[label: 'Noch bestehender Resturlaub', content:antrag.resturlaub]}"/>
    <tr>
      <td>
        Datum der Mitteilung
      </td>
      <td>
        <urlaub:formatDate date="${new Date()}"/>
      </td>
    </tr>
    <g:render template="zeile" model="${[label: 'Unterschrift Antragsteller']}"/>
    <g:render template="zeile" model="${[label: 'Genehmigt']}"/>
  </table>
</div>
</body>
</html>