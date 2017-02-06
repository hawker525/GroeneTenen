<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head><v:head title='Filialen'/></head>
<body>
<v:menu/>
<h1>Filialen</h1>
<c:forEach items='${filialen}' var='filiaal'>
    <c:url var='url' value='/filialen'>
        <c:param name='id' value='${filiaal.id}'/>
    </c:url>
    <h2><a href='${url}'>${filiaal.naam}</a></h2>
    <p>${filiaal.adres.straat} ${filiaal.adres.huisNr}<br>
            ${filiaal.adres.postcode} ${filiaal.adres.gemeente}</p>
</c:forEach>
</body></html>