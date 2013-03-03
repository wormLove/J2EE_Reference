
<%------------------ commented out for now ---------------------------
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
---------------------------------------------------------------------%>
<html>
    <head><title>Hello</title></head>
    <body bgcolor="white">
        <img alt=""  src="duke.waving.gif">
        <h2>Hello, my name is Duke.<br>My hobby is Golf. <br>I was born in China.<br>What are your name and hobby? </h2>
        <form action=""  method="get">
            <input type="text" name="username" size="25"><br>
            <input type="text" name="hobby" size="25"><br>
            <input type="text" name="birthplace" size="25">
            <input type="hidden" name="myparameter" value="Passion!" />
            <p></p>
<!--            <param name="myparameter" value="Passion!" />-->
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
           
        </form>
        <%
            String username = request.getParameter("username");
            if ( username != null && username.length() > 0 ) {
        %>
        <jsp:forward page="middle.jsp"/>

        
        <%
                }
        %>
        
        <%------------ commented out for now ------------
        <c:if test="${fn:length(param.username) > 0}" >
            <%@include file="response.jsp" %>
        </c:if>
        -------------------------------------------------%>
    </body>
</html>
