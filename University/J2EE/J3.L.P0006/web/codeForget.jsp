<%-- 
    Document   : codeForget
    Created on : Mar 16, 2020, 4:35:16 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forget</title>
    </head>
    <body>
        <h1>Enter Code</h1>
        <h3>*Check your email to receive code to change your password !</h3>
        <form action="forgetConfirm">
            Email : <input type="text" name="email" value="<s:property value="%{email}"/>" readonly="true"/> <br/>
            Code : <input type="text" name="forgetCode" value="" required=""/>
            
            <input type="submit" value="Confirm" />
        </form>        
    </body>
</html>
