<%-- 
    Document   : google
    Created on : Mar 10, 2020, 10:31:13 AM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Google</title>
        <s:head/>
    </head>
    <body>
        <h1>Information</h1>
        <h3>*Fill in your information to get started</h3>
            
        <s:form action="regisGoogle" method="POST">
            <s:textfield name="googleEmail" label="Email" value="%{#request.EMAIL}" readonly="true"/>
            <s:textfield name="googleName" label="Name"/>
            <s:textfield name="googlePhone" label="Phone"/>
            <s:textfield name="googleAddress" label="Address"/>
            <s:submit value="Register"/>
        </s:form>
    </body>
</html>
