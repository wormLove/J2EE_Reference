
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Entry Form</h1>
        
        <c:set var="customerTable" scope="application">
            <%-- JSTL homework exercise: Add your own code below  --%>
            <table border="1">
                  <c:forEach var="customer" items="${customers}">
                  <tr>
	          <td>${customer.lastName}</td>
                  <td>${customer.firstName}</td>
                  <td><c:out value="${customer.birthDate}" default="no birth date specified"/></td>
	          <td><c:out value="${customer.address}" default="no address specified"/></td>
                  <td><c:out value="${customer.phoneHome}" default="no address specified"/></td>
                  <td><c:out value="${customer.phoneCell}" default="no address specified"/></td>
	          <td>
	              <c:out value="${customer.address}">
		      <font color="red">no address specified</font>
                      </c:out>
                  </td>
                  </tr>
             </c:forEach>
            </table>
        </c:set>

        <a href="../response.jsp"></a>
        
        
        <form action="response.jsp">
            Enter your name: <input type="text" name="name" value="" />
        <input type="submit" value="OK" />

        </form>
        
    </body>
</html>
