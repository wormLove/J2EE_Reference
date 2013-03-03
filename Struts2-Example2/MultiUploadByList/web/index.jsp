<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Showcase - Fileupload</title>
</head>
<body>

<ul>
	<li>
		<s:url id="url" action="multipleUploadByList" namespace="/fileupload" />
		<s:a href="%{#url}">Multiple File Upload (List)</s:a>

	</li>
</ul>

</body>
</html>