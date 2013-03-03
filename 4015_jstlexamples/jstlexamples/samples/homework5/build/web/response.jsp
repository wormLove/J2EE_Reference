<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>response.jsp Page</h1>        
        <jsp:useBean id="mybean" scope="session" class="org.me.hello.NameHandler" />
        <jsp:setProperty name="mybean" property="*"  />
        Hello, <jsp:getProperty name="mybean" property="name" />
        <br>
        <br>       
        <%-- JSTL homework exercise: Add your own code below  --%>
        <c:out value="${customerTable}" escapeXml="false"/>
    </body>
</html>
