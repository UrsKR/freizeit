<html>
<head>
    <style type="text/css">
    @page {
        size: 148mm 210mm;
        margin: 0.25in;
    }

    .title {
        border: black solid 1px;
        background-color: #d3d3d3;
        text-align: center;
    }

    .body {
        margin-top: 1cm;
        margin-left: auto;
        margin-right: auto;
    }

    .checkbox {
        width: 0.5cm;
        height: 0.5cm;
        border: #d3d3d3 solid 1px;
        text-align: center;
    }

    table, th, td {
        border-collapse: collapse;
        border: 0.5px solid black;
    }

    td {
        width: 7.5cm;
        height: 0.5cm;
        padding-left: 0.2cm;
    }
    </style>
</head>
<body>
<div class="title"><h1>URLAUBSMITTEILUNG</h1></div>
<div class="body">
    <table>
        <g:set var="urlaubscheckbox">${g.render(template: "typcheckbox", model: [checked: 'Erholungsurlaub' == antrag.typ])}</g:set>
        <g:set var="freizeitcheckbox">${g.render(template: "typcheckbox", model: [checked: 'Überstundenausgleich' == antrag.typ])}</g:set>
        <g:set var="sonderurlaubcheckbox">${g.render(template: "typcheckbox", model: [checked: 'Sonderurlaub' == antrag.typ])}</g:set>
        <g:set var="zeitraum">${urlaub.formatDate(date: antrag.firstDay)} - ${urlaub.formatDate(date: antrag.lastDay)}</g:set>
        <g:set var="today" value="${urlaub.formatDate(date:new Date())}"/>
        <g:set var="jahresanspruch" value="${urlaub.formatDayCount(days: antrag.jahresanspruch)}"/>
        <g:set var="vorjahresanspruch" value="${urlaub.formatDayCount(days:antrag.vorjahresanspruch)}"/>
        <g:set var="anzahlTage" value="${urlaub.formatDayCount(days:antrag.numberOfDays)}"/>
        <g:set var="resturlaub" value="${urlaub.formatDayCount(days:antrag.resturlaub)}"/>
        <g:render template="zeile" model="${[label: 'Mitarbeiter', content:antrag.mitarbeiter]}"/>
        <g:render template="zeile" model="${[label: 'Resturlaub aus Vorjahr', content:vorjahresanspruch]}"/>
        <g:render template="zeile" model="${[label: 'Verbleibender Urlaub in diesem Jahr', content:jahresanspruch]}"/>
        <g:render template="zeile" model="${[label: 'Erholungsurlaub', content:urlaubscheckbox]}"/>
        <g:render template="zeile" model="${[label: 'Freizeit', content:freizeitcheckbox]}"/>
        <g:render template="zeile" model="${[label: 'Sonderurlaub', content:sonderurlaubcheckbox]}"/>
        <g:render template="zeile" model="${[label: 'Zeitraum', content:zeitraum]}"/>
        <g:render template="zeile" model="${[label: 'Anzahl Tage', content:anzahlTage]}"/>
        <g:render template="zeile" model="${[label: 'Resturlaub nach diesem Urlaub', content:resturlaub]}"/>
        <g:render template="zeile" model="${[label: 'Datum der Mitteilung', content:today]}"/>
        <g:render template="zeile" model="${[label: 'Unterschrift Antragsteller']}"/>
        <g:render template="zeile" model="${[label: 'Genehmigt']}"/>
    </table>
</div>
</body>
</html>