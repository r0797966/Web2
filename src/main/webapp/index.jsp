<%@ page import="ucll.be.website.domain.model.Lied" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liedjes! | Robyn Hamelyck</title>
    <link rel="icon" type="image/x-icon" href="media/favicon.png">
    <link rel="stylesheet" href="main.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<header class="een">

  <img src="media/banner.jpg" alt="Banner">

  <jsp:include page="nav.jsp">
    <jsp:param name="current" value="home"/>
  </jsp:include>
</header>

<main>
  <article>
    <p>Hou recent beluisterde liedjes bij in een lijst! Je kan liedjes <a href="">toegevoegd verwijderen en aanpassen</a>. Elk lied krijgt een rating zodat je weet wat je dacht van het lied.
    Je kan een liedje terug <a href="">opzoeken</a> uit de lijst!
    Het lied met de hoogste rating op dit moment? ${hoogsteRating.titel != null ? hoogsteRating.titel : "..."} van ${hoogsteRating.artist != null ? hoogsteRating.artist : "..."} rating: ${hoogsteRating.rating}/10</p>
    <img src="media/ipod.jpg" alt="ipod foto">
  </article>

  <article>
    <img src="media/vinyl.jpg" alt="vinyl foto">
    <ul>
      <li> Vijf nieuwste liedjes
        <ol>
          <c:forEach items="${vijfRecente != null ? vijfRecente : ''}" var="l">
          <li>${l.artist} - ${l.titel}</li>
          </c:forEach>
        </ol>
      </li>
    </ul>
  </article>

</main>

<footer>
  <jsp:include page="footer.jsp">
    <jsp:param name="current" value="zoek"/>
  </jsp:include>
</footer>
</body>
</html>
