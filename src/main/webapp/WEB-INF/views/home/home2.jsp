<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-02-15
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center> <font color="gray" size="7"> Hello World! </font> </center>

<%
    // To jest skryptlet.  Zauważ, że zmienna
    // "date" zadeklarowana w pierwszym wbudowanym
    // wyrażeniu jest dostępna również w tym późniejszym.
    System.out.println( "Test helloworld" );
    java.util.Date date = new java.util.Date();
    java.lang.String aa = "aa";

%>
Obecnie mamy <%= date %>
</body>
</html>
