<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Toevoegen | Liedjes</title>
    <link rel="icon" type="image/x-icon" href="media/favicon.png">
    <link rel="stylesheet" href="main.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="twee">
    <h1>Liedjeslijst</h1>

    <jsp:include page="nav.jsp">
        <jsp:param name="current" value="toevoegen"/>
    </jsp:include>
</header>

<main class=toevoegen>
    <section>
        <h2>Voeg toe aan lijst</h2>
        <ul id="error">
            <c:forEach items="${errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
        <form action="Controller?command=add" method="POST" novalidate>
            <label for="titel">Titel:</label>
            <input id="titel" name="titel" type="text" placeholder="Wuthering Heights" required>

            <label for="artist">Artist:</label>
            <input id="artist" name="artist" type="text" placeholder="Kate Bush" required>

            <label for="genre">Genre:</label>
            <input id="genre" name="genre" type="text" placeholder="Pop" required>

            <label for="rating">Rating:</label>
            <input id="rating" name="rating" type="number" placeholder="0" min="0" max="10" required>

            <label for="favoriet">Favoriet:</label>
            <input id="favoriet" name="favoriet" type="checkbox" value="true">

            <input id="submit" type="submit" value="Versturen">
        </form>
    </section>

    <img src="media/form.jpg" alt="vinyl">
</main>

<footer>
    <jsp:include page="footer.jsp">
        <jsp:param name="current" value="zoek"/>
    </jsp:include>
</footer>
</body>
</html>
