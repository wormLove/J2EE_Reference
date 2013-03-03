<%-- 
    Document   : HelloTime
    Created on : Oct 23, 2010, 12:19:32 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page language="java" import="java.util.*" %>

<html>
    <head>
        <title>Access Request and Response Example! </title>
    </head>
    <body>
        <h1><span style="background-color: #FFFFcc">Access Request
                and Response Example!</span></h1>
        <b>Request: </b><s:property value="servletRequest" /><br>
        <br><%=request%><br><br>
        <b>Response:</b> <s:property value="servletResponse" /><br>
        <br><%=response%><br><br>
        <b>Date: </b><%=new Date()%>
    </body>
</html>