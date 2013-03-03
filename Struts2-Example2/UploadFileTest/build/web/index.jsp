<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>upload a file</title>
</head>
<body>

<ul>
	<li>
		<s:url id="url" action="upload" namespace="/fileupload" />
		<s:a href="%{#url}">Single File Upload</s:a>
	</li>
</ul>

</body>
</html>