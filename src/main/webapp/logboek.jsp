<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Logboek | Liedjes </title>
    <link rel="icon" type="image/x-icon" href="media/favicon.png">
    <link rel="stylesheet" href="main.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="twee">
    <h1>Liedjeslijst</h1>

    <jsp:include page="nav.jsp">
        <jsp:param name="current" value="logboek"/>
    </jsp:include>
</header>

<main class="logboek">
    <h2>Logboek</h2>
    <p><a href="Controller?command=reset">reset</a></p>
    <section>
        <ul>
            <c:forEach items="${logboek != null ? logboek : ''}" var="s">
                <li>${s}</li>
            </c:forEach>
        </ul>

        <img src="media/logboek2.jpg" alt="spotify op laptop">
    </section>

</main>

<footer>
    <jsp:include page="footer.jsp">
        <jsp:param name="current" value="logboek"/>
    </jsp:include>
</footer>

</body>
</html>
