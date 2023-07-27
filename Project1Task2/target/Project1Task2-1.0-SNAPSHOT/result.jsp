<%--
  Created by IntelliJ IDEA.
  User: tanvimurke
  Date: 2/7/23
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Women's World Cup 2023</title>
</head>
<body>
<!-- To display the UI-->
<h1>Country: <%=request.getAttribute("country")%> </h1>
<h2>Nickname: <%=request.getAttribute("nickname")%> </h2>
<p> https://www.topendsports.com/sport/soccer/team-nicknames-women.htm </p>
<h2>Capital City: <%=request.getAttribute("capital")%> </h2>
<p> https://restcountries.com </p>
<h2>Top scorer in 2019: <%=request.getAttribute("playermessage")%> </h2>
<p> https://www.espn.com/soccer/stats/_/league/FIFA.WWC/season/2019/view/scoring </p>
<h2>Flag:<br><br> <img src="<%=request.getAttribute("imgsrc")%>" width="325px" height="225px"> </h2>
<p> https://www.cia.gov/the-world-factbook/countries/ </p>
<h2>Flag Emoji:<br><br> <img src="<%=request.getAttribute("emojisrc")%>" width="500px" height="400px"> </h2>
<p> https://cdn.jsdelivr.net/npm/country-flag-emoji-json@2.0.0/dist/images/US.svg </p>
<br>
<form action="index.jsp">
    <input type="submit" name="Continue" value="Continue">
</form>

</body>
</html>
