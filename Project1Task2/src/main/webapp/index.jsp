<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<!--To display the UI-->
<head>
    <title>Women's World Cup 2023</title>
</head>
<body>
<h1><%= "Women's World Cup 2023" %>
</h1>
<h4><%= "Created by Tanvi Murke" %>
</h4>
<h2><%= "Participating Countries" %>
</h2>
<body>
Choose a Country <br><br>
<form action="worldCupServlet" method = "GET">
    <select name="item">
        <option id='Argentina'>Argentina</option>
        <option id='Australia'>Australia</option>
        <option id='Brazil'>Brazil</option>
        <option id='Canada'>Canada</option>
        <option id='China'>China</option>
        <option id='Colombia'>Colombia</option>
        <option id='Costa Rica'>Costa Rica</option>
        <option id='Denmark'>Denmark</option>
        <option id='England'>England</option>
        <option id='France'>France</option>
        <option id='Germany'>Germany</option>
        <option id='Ireland'>Ireland</option>
        <option id='Italy'>Italy</option>
        <option id='Jamaica'>Jamaica</option>
        <option id='Japan'>Japan</option>
        <option id='Morocco'>Morocco</option>
        <option id='Netherlands'>Netherlands</option>
        <option id='New Zealand'>New Zealand</option>
        <option id='Nigeria'>Nigeria</option>
        <option id='Norway'>Norway</option>
        <option id='Philippines'>Philippines</option>
        <option id='South Africa'>South Africa</option>
        <option id='South Korea'>South Korea</option>
        <option id='Spain'>Spain</option>
        <option id='Sweden'>Sweden</option>
        <option id='Switzerland'>Switzerland</option>
        <option id='United States'>United States</option>
        <option id='Vietnam'>Vietnam</option>
        <option id='Zambia'>Zambia</option>
    </select>
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
<br/>
</body>
</html>