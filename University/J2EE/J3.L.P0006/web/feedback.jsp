<%-- 
    Document   : details.jsp
    Created on : Mar 8, 2020, 9:00:30 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
    </head>
    <body>
        <a href="home">Home</a><br/>
        <h1>Feedback</h1>
        <form action="feedback">
            Car Name : <input type="text" name="carName" value="<s:property value="%{dto.name}"/>" readonly="true"/>
            <input type="hidden" name="carID" value="<s:property value="%{dto.id}"/>" />
            Point : <input type="number" name="point" value="0" min="0" max="10" required=""/><br/>
            <input type="submit" value="Send"/>
        </form>
        <h3>Score 0 to 10 according to your satisfaction !</h3>
    </body>
</html>
