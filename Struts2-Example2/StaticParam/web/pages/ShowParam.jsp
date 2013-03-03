<%-- 
    Document   : HelloTime
    Created on : Oct 23, 2010, 12:19:32 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>Static Parameter Example!</title>
    </head>
    <body>
    <h1>Static Parameter</h1>
    <table border="1" width="25%" bgcolor="#ffffcc">
       <tr>
      <td width="25%"><b>Key</b></td>
      <td width="25%"><b>Value</b></td>
      </tr>
      <tr>
      <td width="25%">pramater1</td>
      <td width="25%"><s:property value="pramater1" /></td>
      </tr>
      <tr>
      <td width="25%">pramater2</td>
      <td width="25%"><s:property value="pramater2" /></td>
      </tr>
      <tr>
      <td width="25%">pramater3</td>
      <td width="25%"><s:property value="pramater3" /></td>
      </tr>
    </table>
    </body>
</html>