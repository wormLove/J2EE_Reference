<%@page contentType="text/html" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>

<head>
<title>Input form</title>
 <link href="<s:url value="/css/main.css"/>" rel="stylesheet"
          type="text/css"/>
      <s:head/>
</head>

<body>

  <s:form method="POST" action="example/dateValidation1.action">
    <s:textfield label="Enter Joining Date of Employee" name="joiningdate"/>
    <s:submit />
  </s:form>

</body>

</html>