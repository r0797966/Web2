<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit | Liedjes</title>
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

<main>
    <h2>Verander een lied!</h2>
    <!-- errors -->
    <form action="Controller?command=edit" method="POST">
        <label for="titel">Titel:</label>
        <input id="titel" name="titel" type="text" placeholder="titel" value="${lied.titel}">

        <label for="artist">Artist:</label>
        <input id="artist" name="artist" type="text" placeholder="artist" value="${lied.artist}">

        <label for="genre">Genre:</label>
        <input id="genre" name="genre" type="text" placeholder="genre" value="${lied.genre}">

        <label for="rating">Rating:</label>
        <input id="rating" name="rating" type="number" placeholder="0" value="${lied.rating}" min="0" max="10" required>

        <label for="favoriet">Favoriet:</label>
        <input id="favoriet" name="favoriet" type="checkbox" value="true">

        <input type="hidden" id="id" name="id" value="${lied.id}">

        <input id="submit" type="submit" value="Versturen">
    </form>
</main>

<footer>
    <jsp:include page="footer.jsp">
        <jsp:param name="current" value="zoek"/>
    </jsp:include>
</footer>
</body>
</html>
