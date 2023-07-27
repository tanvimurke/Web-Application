<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<!--To display UI-->
<head>
    <title>Distributed Systems Class Clicker</title>
</head>
<body>
<h1><%= "Distributed Systems Class Clicker" %></h1>
<br/>
<% if (request.getParameter("answer") != null) { %>
Your "<%= request.getParameter("answer")%>" has been registered <br/>
<%}%>
Submit your answer to the current question:
<br/>
<form action="clickerServlet" method = "GET">
    <input type="radio" name="answer" value="A">A <br/>
    <input type="radio" name="answer" value="B">B <br/>
    <input type="radio" name="answer" value="C">C <br/>
    <input type="radio" name="answer" value="D">D <br/>
    <br/><br/><input type = "submit" value = "Submit" />
    <br/><br/>
</form>
</body>
</html>