<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-02-15
  Time: 21:43
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

    </dl>
    <dl>
        <dt class="second">Nawigacja strony</dt>

    </dl>

</div>





<div class="content">

    <!-- Użycie tagu jsp:useBean. Tag ten szuka obiektu klasy User z pakiecie
    javastart.jspexample.model w sesji użytkownika. Jeśli znajdzie, to go
    wykorzysta, jeśli nie stworzy nowy obiekt klasy user o nazwie "user"-->
    <jsp:useBean id="user" class="com.spring.boot.model.User" scope="session"></jsp:useBean>

    <!-- Użycie tagu jsp:setProperty. Tag ten umieszcza wszystkie parametry
    przesyłane z innych plików jsp pasujące do obiektu o nazwie user. Jeśli
    parametry te nie będą się zgadzać, tomcat wyrzuci wyjątkiem -->
    <jsp:setProperty property="*" name="user" />

    <!-- Ponowne użycie tagu jsp:useBean ładujący źródło danych. -->
    <jsp:useBean id="dataSource" class="com.spring.boot.model.DataSource" scope="session"></jsp:useBean>

    <!-- Wyświetlenie nazwy użytkownika. -->
    Nazwa: <%= user.getName() %><br />

    <!-- Logika sprawdzająca poprawność parametrów logowania. -->
    <% String result = "Dane niepoprawne";

        if(dataSource.userExists(user)) {
            result = "Poprawny użytkownik oraz hasło";
        }
    %>

    <!-- Zwrócenie wynikowego stringa "result" -->
    <%= result %>

</div>





</body>
</html>
