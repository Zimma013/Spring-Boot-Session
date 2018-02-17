<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-02-15
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/css/podstawowy.css" type="text/css" title="Podstawowy" />
</head>
<body>
<div class="title">
    <p><img src="http://www.robot.agh.edu.pl/img/logos/agh.png" alt="agh" class="agh" /></p>
    <p><img src="http://cdn.crunchify.com/wp-content/uploads/2013/06/Java-Design-Patterns.png" alt="design" class="design" /></p>
    <h1>Strona</h1>
</div>
<div  class="menuleft">
    <dl>
        <dt class="second">Odnośniki do ...</dt>
        <dd><a href="">Przeskocz do ...</a></dd>

    </dl>
    <dl>
        <dt class="second">Nawigacja strony</dt>
        <dd><a href="">Nawiguj do ...</a></dd>
        <dd><a href="">Nawiguj do ...</a></dd>


    </dl>

</div>





<div class="content">

    <form method="post" action="formularz">
        Podaj swój login: <br />
        <input type="text" name="name" value=${tmp2} /><br />
        Podaj swoje hasło <br />
        <input type="text" name="password" value=${tmp} /><br />
        <input type="submit" value="zaloguj">
    </form>

</div>





</body>
</html>