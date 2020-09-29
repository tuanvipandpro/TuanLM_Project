<%-- 
    Document   : verify
    Created on : Mar 6, 2020, 8:54:04 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify</title>
    </head>
    <body>
        <h1>Verify</h1>
        <s:form action="verify">
            <s:textfield name="code" label="Verify Code"/>
            <s:textfield name="email" label="Email" readonly="true"/>
            <s:submit value="Verify"/>
        </s:form>
    </body>
</html>
