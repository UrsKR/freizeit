<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='urlaubsformular.css'/>"/>
    <calendar:resources/>
</head>
<body>
<h1>Bitte füll den Urlaubsantrag aus und klicke auf "Bereit zum Ausdruck".</h1>
<g:pdfForm controller='urlaub' action='pdf' method='post' filename="urlaubsantrag.pdf">
    <table border="1" style="border-collapse: collapse;">
        <tr>
            <td>
                Dein Name
            </td>
            <td>
                <g:textField name="mitarbeiter"/>
            </td>
        </tr>
        <tr>
            <td>
                Resturlaub aus dem Vorjahr
            </td>
            <td>
                <g:textField name="vorjahresanspruch"/>
            </td>
        </tr>
        <tr>
            <td>
                Urlaub pro Jahr
            </td>
            <td>
                <g:textField name="jahresanspruch"/>
            </td>
        </tr>
        <tr>
            <td>
                Ich mache
            </td>
            <td>
                <g:radioGroup name="typ" labels="['Erholungsurlaub','Überstundenausgleich']" values="['Erholungsurlaub', 'Überstundenausgleich']" value="Erholungsurlaub">
                    <p>${it.radio} ${it.label}</p>
                </g:radioGroup>
            </td>
        </tr>
        <g:set var="tomorrow" value="${new Date().plus(1)}"/>
        <tr>
            <td>
                Erster freier Tag
            </td>
            <td>
                <calendar:datePicker name="firstDay" defaultValue="${tomorrow}" dateFormat="%d.%m.%Y"/>
            </td>
        </tr>
        <tr>
            <td>
                Letzter freier Tag
            </td>
            <td>
                <calendar:datePicker name="lastDay" defaultValue="${tomorrow}" dateFormat="%d.%m.%Y"/>
            </td>
        </tr>
    </table>
    <g:submitButton name="Bereit zum Ausdruck" value="Bereit zum Ausdruck"/>
</g:pdfForm>
</body>
</html>