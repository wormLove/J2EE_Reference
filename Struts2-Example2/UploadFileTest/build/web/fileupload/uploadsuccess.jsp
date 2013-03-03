<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Showcase</title>
</head>

<body>
<h1>Fileupload sample</h1>

    <ul>
        <li>File: <s:property value="upload" /></li>
        <li><%=request.getSession().getServletContext().getRealPath("/")%>
    </ul>

</body>
</html>

