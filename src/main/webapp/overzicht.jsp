<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Overzicht | Liedjes </title>
    <link rel="icon" type="image/x-icon" href="media/favicon.png">
    <link rel="stylesheet" href="main.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="twee">
    <h1>Liedjeslijst</h1>

    <jsp:include page="nav.jsp">
        <jsp:param name="current" value="overzicht"/>
    </jsp:include>
</header>

<main class="overzicht">
    <h2>Liedjeslijst</h2>

    <p id="message">${message != null ? message : ""}</p>
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
            <td><a id="update${l.id}" href="Controller?command=update&id=${l.id}">x</a></td>
            <td><a id="verwijder${l.id}" href="Controller?command=verwijderBevestiging&id=${l.id}">x</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <article>
        <p>Totaal liedjes: ${liedjes.size()}</p>
        <p>Totaal favorieten: ${aantalFavorieten}</p>
        <p>Hoogste rating: ${hoogsteRating.titel != null ? hoogsteRating.titel : "..."} van ${hoogsteRating.artist != null ? hoogsteRating.artist : "..."} rating: ${hoogsteRating.rating}/10</p>
        <p>Gemiddelde rating: ${gemiddeldeRating}/10</p>
    </article>

    <img src="media/overzicht.jpg" alt="concert">

</main>

<footer>
    <jsp:include page="footer.jsp">
        <jsp:param name="current" value="zoek"/>
    </jsp:include>
</footer>
</body>
</html>
