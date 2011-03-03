<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:javascript library="prototype"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='urlaubsformular.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='main.css'/>"/>
    <calendar:resources/>
</head>
<body>
<h1>Bitte fülle den Urlaubsantrag aus und klicke auf "Bereit zum Ausdruck".</h1>
<g:form controller="urlaub">
    <table border="1" style="border-collapse: collapse;">
        <tr>
            <td>
                Dein Name
            </td>
            <td>
                <g:textField name="mitarbeiter" value="${antrag.mitarbeiter}"/>
            </td>
        </tr>
        <tr>
            <td>
                Resturlaub aus dem Vorjahr
            </td>
            <td>
                <g:textField name="vorjahresanspruch" value="${antrag.vorjahresanspruch}"/>
            </td>
        </tr>
        <tr>
            <td>
                Resturlaub in diesem Jahr
            </td>
            <td>
                <g:textField name="jahresanspruch" value="${antrag.jahresanspruch}"/>
            </td>
        </tr>
        <tr>
            <td>
                Ich mache
            </td>
            <td>
                <g:radioGroup name="typ" labels="['Erholungsurlaub','Überstundenausgleich']" values="['Erholungsurlaub', 'Überstundenausgleich']" value="${antrag.typ}">
                    <p>${it.radio} ${it.label}</p>
                </g:radioGroup>
            </td>
        </tr>
        <tr>
            <td>
                Erster freier Tag
            </td>
            <td>
                <calendar:datePicker name="firstDay" defaultValue="${antrag.firstDay}" dateFormat="%d.%m.%Y"/>
            </td>
        </tr>
        <tr>
            <td>
                Letzter freier Tag
            </td>
            <td>
                <calendar:datePicker name="lastDay" defaultValue="${antrag.lastDay}" dateFormat="%d.%m.%Y"/>
            </td>
        </tr>
    </table>
    <g:actionSubmit value="Wie viele Tage" action="antrag"/>
    <g:actionSubmit value="Bereit zum Ausdruck" action="pdf"/>
</g:form>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>
<div>Probleme? Wünsche? <a href="http://code.google.com/p/freizeit/issues/entry">Bitte meldet euch!</a></div>
</body>
</html>