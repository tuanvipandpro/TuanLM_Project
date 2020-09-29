<%-- 
    Document   : home
    Created on : Mar 1, 2020, 8:03:58 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <s:if test="%{#session.USER == null}">
            <a href="loginLink">Login</a><br/>         
        </s:if>
            
        <s:if test="%{#session.USER.name != null and #session.USER.name != ''}">
            <a href="home">Home</a> ||
            <a href="history">History</a> ||
            <a href="cart">My Cart</a> ||
            <%--
                <a href="loanLink">Lend your car</a> ||
            --%>
            <a href="logout">Logout</a><br/>
            <font color="red">
                Welcome, <s:property value="%{#session.USER.name}"/>
            </font>            
        </s:if>
            
        <h1>Home</h1>
        
        <s:if test="%{list != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <s:if test="%{#session.USER.name != null and #session.USER.name != ''}">
                            <th>From</th>
                            <th>To</th>
                            <th>Rent</th>
                        </s:if>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="list" status="counter">
                        <tr>
                            <td> <s:property value="%{#counter.count}"/> </td>
                            <td> <a href="viewCar?id=<s:property value="%{#dto.id}"/>"> <s:property value="%{#dto.name}"/> </a> </td>
                            <td> <s:property value="%{#dto.price}"/> </td>
                            <s:if test="%{#session.USER.name != null and #session.USER.name != ''}">
                                <form action="buy" method="POST">
                                    <td>
                                        <input type="date" name="from" value="" />
                                    </td>
                                    <td>
                                        <input type="date" name="to" value="" />
                                    </td>
                                    <td>
                                        <input type="submit" value="Rent" />
                                        <input type="hidden" name="car_id" value="<s:property value="%{#dto.id}"/>" />
                                    </td>
                                </form>
                            </s:if>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>   
    </body>
</html>
