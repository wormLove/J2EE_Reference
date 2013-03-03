<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Showcase - Fileupload</title>
</head>
<body>

<ul>
	<li>
		<s:url id="url" action="multipleUploadByArray" namespace="/fileupload" />
		<s:a href="%{#url}">Multiple File Upload (Array)</s:a>

	</li>
</ul>

</body>
</html>