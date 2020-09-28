<%-- 
    Document   : carInfo
    Created on : Mar 19, 2020, 10:34:38 AM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Car Details</title>
    </head>
    <body>
        <h1>Car Name : <s:property value="%{car.name}"/></h1>
        
        <ul style="list-style-type:disc;">
            <li>Year     : <s:property value="%{car.year}"/></li>
            <li>Category : <s:property value="%{car.category}"/></li>
            <li>Color    : <s:property value="%{car.color}"/></li>
            <li>Price    : <s:property value="%{car.price}"/> $ </li>
        </ul>

        <s:if test="%{#session.USER.name != null and #session.USER.name != ''}">
            <form action="buy" method="POST">
                From : <input type="date" name="from" value="" />
                To : <input type="date" name="to" value="" />
                <input type="submit" value="Rent" />
                <input type="hidden" name="car_id" value="<s:property value="%{car.id}"/>" />            
            </form>
        </s:if>
        <s:if test="%{#session.USER == null}">
            <h3>***Please login if you want to rent this car !</h3>
        </s:if>
        <br/>
    </body>
</html>
