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
    <title>Logowanie</title>
    <link rel="stylesheet" href="styles/css/podstawowy.css" type="text/css" title="Podstawowy" />
</head>
<body>
<div class="title">
    <p><img src="http://www.robot.agh.edu.pl/img/logos/agh.png" alt="agh" class="agh" /></p>
    <p><img src="https://orig00.deviantart.net/9f81/f/2009/220/1/e/giant_anteater_by_kaileo.png" alt="design" class="design" /></p>
    <h1>Logowanie</h1>
</div>
<div  class="menuleft">
    <dl>
        <dt class="second"> logowanie </dt>


    </dl>
    <dl>
        <dt class="second">Nawigacja strony</dt>



    </dl>

</div>





<div class="content">



        <form method="post" action="/loginValidate">

            <h2> ${login_msg} </h2>

            Podaj swój login: <br />

            <input type="text" name="login" /><br />

            Podaj swoje hasło <br />

            <input type="password" name="password" /><br />

            <input type="submit" value="zaloguj">
        </form>
        <br />
        <br />
        <br />
        <form method="post" action="register">
            <input type="submit" value="załóż konto">
        </form>







</div>





</body>
</html>