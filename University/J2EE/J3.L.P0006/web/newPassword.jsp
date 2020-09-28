<%-- 
    Document   : newPassword
    Created on : Mar 16, 2020, 7:35:42 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password</title>
        <s:head/>
    </head>
    <body>
        <h1>Enter new password !</h1>
        <s:form action="changePassword" method="POST">
            <input type="hidden" name="email" value="<s:property value="%{email}"/>" /><br/>
            <s:password name="password" label="New Password"/>
            <s:password name="confirm" label="Confirm"/>
            <s:submit value="Confirm"/>
        </s:form><br/>        
    </body>
</html>
