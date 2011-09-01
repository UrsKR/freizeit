<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:javascript library="prototype"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='urlaubsformular.css'/>"/>
    <link rel="stylesheet" href="<g:createLinkTo dir='css' file='main.css'/>"/>
    <calendar:resources/>
    <g:javascript>
        function activateHalfDays() {
            var firstDay = document.getElementById('firstDay_value').value
            var lastDay = document.getElementById('lastDay_value').value
            var lastDayIsHalfDay = document.getElementById('lastDayIsHalfDay')
            var lastDayLabel= document.getElementById('lastDayLabel')
            if (firstDay == lastDay) {
                lastDayIsHalfDay.checked = false
                lastDayIsHalfDay.disabled = true
                lastDayLabel.style.color="gray"
            }
            else {
                lastDayIsHalfDay.disabled = false
                lastDayLabel.style.color="black"
            }
        }

        function showRequiredDays() {
            var firstDay = document.getElementById('firstDay_value').value
            var lastDay = document.getElementById('lastDay_value').value
            var firstDayIsHalfDay = document.getElementById('firstDayIsHalfDay').checked
            var lastDayIsHalfDay = document.getElementById('lastDayIsHalfDay').checked
            new Ajax.Request('/freizeit/urlaub/requiredDays',
            {
                method:'get',
                parameters: {firstDay: firstDay, lastDay:lastDay, firstDayIsHalfDay:firstDayIsHalfDay, lastDayIsHalfDay:lastDayIsHalfDay},
                onSuccess: function(transport) {
                    document.getElementById('numberOfDays').innerHTML = transport.responseText
                }
            });
        }
    </g:javascript>
</head>

<body>
<div class=content>
    <h1>Bitte fülle den Urlaubsantrag aus und klicke auf "PDF erstellen".</h1>
    <g:form controller="urlaub">
        <table border="0">
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
                    <g:textField name="vorjahresanspruch"
                                 value="${urlaub.formatDayCount(days: antrag.vorjahresanspruch)}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Resturlaub in diesem Jahr
                </td>
                <td>
                    <g:textField name="jahresanspruch" value="${urlaub.formatDayCount(days: antrag.jahresanspruch)}"/>
                </td>
            </tr>
            <tr>
                <td>
                    Ich mache
                </td>
                <td>
                    <g:radioGroup name="typ" labels="['Erholungsurlaub','Überstundenausgleich','Sonderurlaub']"
                                  values="['Erholungsurlaub', 'Überstundenausgleich','Sonderurlaub']"
                                  value="${antrag.typ}">
                        <p>${it.radio} ${it.label}</p>
                    </g:radioGroup>
                </td>
            </tr>
            <tr>
                <td>
                    Erster freier Tag
                </td>
                <td>
                    <calendar:datePicker name="firstDay" defaultValue="${antrag.firstDay}" dateFormat="%d.%m.%Y"
                                         onChange="activateHalfDays(); showRequiredDays();"/>
                </td>
                <td rowspan="4" style="font-size:120px; padding-top:43px;">
                    }
                    <span id="numberOfDays" style="font-size:40px;position: relative;top:-20px">${flash.message}</span>
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>
                    <g:checkBox name="firstDayIsHalfDay"
                                onChange="showRequiredDays()"/> <span>Der erste Tag ist ein halber Tag.</span>
                </td>
            </tr>
            <tr>
                <td>
                    Letzter freier Tag
                </td>
                <td>
                    <calendar:datePicker name="lastDay" defaultValue="${antrag.lastDay}" dateFormat="%d.%m.%Y"
                                         onChange="activateHalfDays(); showRequiredDays()"/>
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>
                    <g:checkBox name="lastDayIsHalfDay"
                                onChange="showRequiredDays()" disabled="true"/> <span id="lastDayLabel"
                                                                                      style="color:gray;">Der letzte Tag ist ein halber Tag.</span>
                </td>
            </tr>
        </table>
        <g:actionSubmit class="submit" value="PDF erstellen" action="pdf"/>
    </g:form>
    <div class="contact">Probleme? Wünsche? <a
            href="http://code.google.com/p/freizeit/issues/entry">Bitte meldet euch!</a></div>
</div>
</body>
</html>