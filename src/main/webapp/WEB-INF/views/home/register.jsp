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
    <title>Rejestracja</title>
    <link rel="stylesheet" href="styles/css/podstawowy.css" type="text/css" title="Podstawowy" />
</head>
<body>
<div class="title">
    <p><img src="http://www.robot.agh.edu.pl/img/logos/agh.png" alt="agh" class="agh" /></p>
    <p><img src="http://cdn.crunchify.com/wp-content/uploads/2013/06/Java-Design-Patterns.png" alt="design" class="design" /></p>
    <h1>Rejestracja</h1>
</div>
<div  class="menuleft">
    <dl>
        <dt class="second"> rejestracja</dt>


    </dl>
    <dl>
        <dt class="second"> Nawigacja strony </dt>


    </dl>

</div>





<div class="content">

    <form method="post" action="/registerValidate">

        <h2> ${register_msg} </h2>

        Podaj swój login: <br />

        <input type="text" name="login" /><br />

        Podaj swoje hasło: <br />

        <input type="password" name="password" /><br />

        Podaj swoje hało ponownie: <br />

        <input type="password" name="password2" /><br />

        <input type="submit" value="Zarejestruj">
    </form>

</div>





</body>
</html>