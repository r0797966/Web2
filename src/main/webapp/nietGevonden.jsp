<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Niet gevonden | Liedjes</title>
    <link rel="icon" type="image/x-icon" href="media/favicon.png">
    <link rel="stylesheet" href="main.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="twee">
    <h1>Liedjeslijst</h1>

    <jsp:include page="nav.jsp">
        <jsp:param name="current" value="zoeken"/>
    </jsp:include>
</header>

<main class="overzicht">
    <h2>Niet gevonden!</h2>

    <article>
    <p id="message">Je zoektocht naar ${zoek} leverde geen resultaten op</p>
    </article>

    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Titel</th>
            <th>Artist</th>
            <th>Genre</th>
            <th>Rating</th>
            <th>Favoriet</th>
            <th># beluisterd</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${liedjes != null ? liedjes : ''}" var="l">
            <tr>
                <td>${l.id}</td>
                <td>${l.titel}</td>
                <td>${l.artist}</td>
                <td>${l.genre}</td>
                <td>${l.rating}</td>
                <td>${l.favorietToString()}</td>
                <td>${l.aantalKeerBeluisterd}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

<footer>
    <jsp:include page="footer.jsp">
        <jsp:param name="current" value="zoek"/>
    </jsp:include>
</footer>
</body>
</html>
