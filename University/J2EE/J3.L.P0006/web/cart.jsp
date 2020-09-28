<%-- 
    Document   : cart.jsp
    Created on : Mar 11, 2020, 9:48:20 AM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <s:if test="%{#session.CART == null}">
            <h3>Your cart is empty!</h3>
            <a href="home"> Click here to return !</a>
        </s:if>
        <s:if test="%{#session.CART != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>From</th>
                        <th>To</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="%{#session.CART.cart}" status="counter">
                        <form action="shopping" method="POST">
                            <tr>
                                <td> <s:property value="%{#counter.count}"/> </td>
                                <td> <s:property value="%{#dto.key.name}"/> </td>
                                <td> <s:property value="%{#dto.key.price}"/> </td>
                                <td> 
                                    <input type="number" name="quantity" value="<s:property value="%{#dto.value}"/>" min="1"/>
                                    <input type="hidden" name="car_id" value="<s:property value="%{#dto.key.id}"/>" />
                                </td>
                                <td> 
                                    <input type="text" name="from" value="<s:property value="%{#dto.key.from}"/>" readonly="true"/>
                                </td>
                                <td>
                                    <input type="text" name="to" value="<s:property value="%{#dto.key.to}"/>" readonly="true"/>
                                </td>
                                <td>
                                    <input type="submit" value="Delete" name="btAction" onclick="return confirm('Do you want to remove this car ?')"/>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction"/>             
                                </td>
                            </tr>
                        </form>    
                    </s:iterator>
                        <tr>
                            <td colspan="4">Total : <s:property value="%{#session.TOTAL}"/> USD</td>
                            <td colspan="4"> <a href="home">Click here to rent more car !</a> </td>
                        </tr>
                </tbody>
            </table>
            <form action="payment">
                Sale (if any) : <input type="text" name="sale" value="" /><br/>
                <input type="submit" value="Payment" onclick="return confirm('Do you want to pay this cart ?')"/>
            </form>            
        </s:if>    
    </body>
</html>
