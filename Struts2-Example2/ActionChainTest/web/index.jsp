<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Showcase - Fileupload</title>
</head>
<body>

<ul>
	<li>
		<s:url id="url" action="actionChain1!input" namespace="/action" /> 
               <%-- <s:url id="url" action="actionChain1" namespace="/action" /> --%>
		<s:a href="%{#url}">Action Test Chain</s:a>

	</li>
</ul>

</body>
</html>