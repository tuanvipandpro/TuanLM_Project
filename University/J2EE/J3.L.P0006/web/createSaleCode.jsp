<%-- 
    Document   : createSaleCode
    Created on : Mar 19, 2020, 8:31:30 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Sale</title>
        <s:head/>
    </head>
    <body>
        <h1>Create Sale Code</h1>
        
        <s:form action="createSale" method="POST" theme="simple">
            Code : <s:textfield name="code" label="Code"/><br/>
            Sale : <input type="number" name="sale" value="10" min="10" max="80" required="true"/> % <br/>
            Expired Date : <input type="date" name="expired" value="" required="true"/><br/>
            <input type="submit" value="Create" />
            
        </s:form>
    </body>
</html>
