<html>
<head>
  <link rel="stylesheet" href="<g:createLinkTo dir='css' file='urlaubsantrag.css'/>"/>
</head>
<body>
<div class="title"><h1>URLAUBSMITTEILUNG</h1></div>
<div class="body">
  <table>
    <tr>
      <td>
        Mitarbeiter
      </td>
      <td>
        ${antrag.mitarbeiter}
      </td>
    </tr>
    <tr>
      <td>
        Urlaubsanspruch aus Vorjahr
      </td>
      <td>
        ${antrag.vorjahresanspruch}
      </td>
    </tr>
    <tr>
      <td>
        Urlaubsanspruch pro Jahr
      </td>
      <td>
        ${antrag.jahresanspruch}
      </td>
    </tr>
    <tr>
      <td>
        Erholungsurlaub
      </td>
      <td>
        <div class='checkbox'>
          <g:if test="${antrag.typ == 'Erholungsurlaub'}">
            X
          </g:if>
        </div>
      </td>
    </tr>
    <tr>
      <td>
        Freizeit
      </td>
      <td>
        <div class='checkbox'>
          <g:if test="${antrag.typ == 'Überstundenausgleich'}">
            X
          </g:if>
        </div>
      </td>
    </tr>
    <tr>
      <td>
        Zeitraum
      </td>
      <td>
        ${antrag.firstDay.format('dd.MM.yyyy')} - ${antrag.lastDay.format('dd.MM.yyyy')}
      </td>
    </tr>
    <tr>
      <td>
        Anzahl Tage
      </td>
      <td>
        ${antrag.numberOfDays}
      </td>
    </tr>
    <tr>
      <td>
        Noch bestehender Resturlaub
      </td>
      <td>
        ${antrag.resturlaub}
      </td>
    </tr>
    <tr>
      <td>
        Datum der Mitteilung
      </td>
      <td>
        ${new Date().format('dd.MM.yyyy')}
      </td>
    </tr>
    <tr>
      <td>
        Unterschrift Antragsteller
      </td>
      <td>

      </td>
    </tr>
    <tr>
      <td>
        Genehmigt
      </td>
      <td>

      </td>
    </tr>
  </table>
</div>
</body>
</html>