<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h2><%= "Project 1 Task 1- Compute Hashes" %>
</h2>
<!-- To display the UI-->
<form action = "helloServlet" method = "POST">
    <br>
    Enter Text: <br><br><input type = "text" name = "text" id="text">
    <br><br>
    Select Hashing method:
    <br><br>
    <!-displays the radio button-->
    <input type="radio" name="hash" value="MD5" checked="true">MD5
    <input type="radio" name="hash" value="SHA256">SHA-256
    <br><br><input type = "submit" value = "Submit" />
</form>

<br>
</body>
</html>