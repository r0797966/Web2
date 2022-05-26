<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Zoek | Liedjes</title>
    <link rel="icon" type="image/x-icon" href="media/favicon.png">
    <link rel="stylesheet" href="main.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="twee">
    <h1>Liedjeslijst</h1>

    <jsp:include page="nav.jsp">
        <jsp:param name="current" value="zoek"/>
    </jsp:include>
</header>

<main class="zoek">
    <h2>Zoeken</h2>
    <form action="Controller?command=search" method="POST">
        <label for="zoek">Zoek op titel, artist of genre: </label>
        <input id="zoek" name="zoek" type="text" placeholder="${lastSearch == null ? "titel, artist of genre": lastSearch}">

        <input id="submit" type="submit" value="Versturen">
    </form>

    <!-- cookie -->
</main>

<footer>
    <jsp:include page="footer.jsp">
        <jsp:param name="current" value="zoek"/>
    </jsp:include>
</footer>
</body>
</html>
