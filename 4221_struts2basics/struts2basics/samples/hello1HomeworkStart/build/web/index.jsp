<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head><title>Hello</title></head>
    <body bgcolor="white">
        <img src="duke.waving.gif"> 
        <h2>Hello, my name is Duke. What's yours?</h2>
        <form  action ="HelloTime.action">
            <input type="text" name="username" size="25">
            <p></p>
            <input type="submit" value="Submit">
            <input type="reset" value="Reset">
        </form>

        <s:property value="username" />
    </body>
</html>
