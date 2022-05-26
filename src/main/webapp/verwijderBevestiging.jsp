<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verwijder | Liedjes</title>
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
    <h2>Verwijder lied</h2>

    <article>
        <p>Ben je zeker dat je ${lied.titel} van ${lied.artist} wil verwijderen?</p>

        <form action="Controller?command=verwijder" method="POST">
            <input type="hidden" name="titel" value="${param.titel}">
            <input type="hidden" name="artist" value="${param.artist}">
            <input id="verwijder" type="submit" name="verwijder" value="Verwijder">
            <input id="cancel" type="submit" name="verwijder" value="Cancel">
        </form>
    </article>
</main>

<footer>
    <nav>
        <jsp:include page="footer.jsp">
            <jsp:param name="current" value="zoek"/>
        </jsp:include>
    </nav>
</footer>
</body>
</html>
