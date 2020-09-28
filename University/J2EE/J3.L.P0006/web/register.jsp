<%-- 
    Document   : register
    Created on : Mar 6, 2020, 5:10:18 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <s:head/>
    </head>
    <body>
        <h1>Register</h1>        
        <s:form action="register" method="POST">
            <s:textfield name="email" label="Email"/>
            <s:password name="password" label="Password"/>
            <s:password name="confirm" label="Confirm"/>
            <s:textfield name="name" label="Fullname"/>
            <s:textfield name="phone" label="Phone"/>
            <s:textfield name="address" label="Address"/>
            <s:submit value="Register"/>
            <s:reset value="Reset"/>
        </s:form><br/>
        <s:if test="%{exception.message.contains('duplicate')}">
            <font color="red">
                <s:property value="email"/> is exist !!!
            </font>
        </s:if>
    </body>
</html>
