<%--
  Created by IntelliJ IDEA.
  User: tanvimurke
  Date: 2/10/23
  Time: 7:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--To display the UI-->
    <title>Distributed Systems Class Clicker</title>
</head>
<body>
<h1>Distributed Systems Class Clicker</h1>
<br/>
<!- if there are no options selected-->
<% if(request.getAttribute("countA").equals(0) && request.getAttribute("countB").equals(0) &&
        request.getAttribute("countC").equals(0) && request.getAttribute("countD").equals(0)){ %>
There are currently no results
<%  }else{%>
<br/>
<!--displays the result with count ->
The results from the survey are as follows<br/><br/>
A: <%=request.getAttribute("countA")%><br/>
B: <%=request.getAttribute("countB")%><br/>
C: <%=request.getAttribute("countC")%><br/>
D: <%=request.getAttribute("countD")%><br/>
<%}%>
</body>
</html>

