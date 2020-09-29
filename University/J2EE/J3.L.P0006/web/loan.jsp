<%-- 
    Document   : loan
    Created on : Mar 17, 2020, 4:35:32 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Car Information</title>
        <s:head/>
    </head>
    <body>
        <h1>Lend Your Car</h1>
        <h3>Your car will be allowed to be rented when approved !</h3>
        <s:form action="lendCar" method="POST" theme="simple"> 
            Name : <input type="text" name="carName" value="" required="true"/><br/>
            Year : <input type="number" name="carYear" value="" required="true"/><br/>
            Category :
            <select name="carCategory">
                <s:iterator var="cat" value="%{#session.CATEGORY}">
                    <option><s:property value="%{#cat}"/></option>
                </s:iterator>
            </select><br/>             
            Price : <input type="number" name="carPrice" value="" required="true"/><br/>
            Quantity : <input type="number" name="carQuantity" value="" required="true"/><br/>
            Color :
            <select name="carColor">
                <s:iterator var="col" value="%{#session.COLOR}">
                    <option><s:property value="%{#col}"/></option>
                </s:iterator>
            </select><br/>
            <input type="submit" value="Register Your Car" />
        </s:form>        
    </body>
</html>
