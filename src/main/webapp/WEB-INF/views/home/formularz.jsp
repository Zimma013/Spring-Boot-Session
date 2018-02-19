<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-02-17
  Time: 04:13
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
        <dt class="second">zapisane konwersacje</dt>
        <form method="post" action=${redirectCRUD}>
        ${list_var}
        <br/>
            <input type="radio" name="submitType" value="1" checked = "checked">Zmień nazwę<br/>


            <input type="radio" name="submitType" value="2">Dezaktywuj<br/>

            <input type="radio" name="submitType" value="3">Aktywuj<br/>

            <input type="submit" value="Wykonaj">

        </form>
    </dl>
    <dl>
        <dt class="second">Nawigacja strony</dt>

    </dl>

</div>





<div class="content">



    <form method="post" action=${t}>


        Uzupełnij formularz:
        <br />

        <textarea name="form_text" rows="10" cols="80">
            ${form_text_var}
        </textarea>


        <br />
        <input type="submit" value="Wyślij">
    </form>





</div>





</body>
</html>