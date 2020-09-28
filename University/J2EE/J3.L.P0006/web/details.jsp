<%-- 
    Document   : details
    Created on : Mar 10, 2020, 4:42:08 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Details</title>
    </head>
    <body>
        <s:if test="%{#session.USER.name != null and #session.USER.role_id == 1 and #session.USER.name != ''}">
            <a href="home">Home</a> ||
            <a href="history">History</a>
        </s:if>
        <s:if test="%{#session.USER.name != null and #session.USER.role_id == 2 and #session.USER.name != ''}">
            <a href="admin">Home</a> 
        </s:if>

        <h1>Details</h1>
        <s:if test="%{details != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Rental_Date</th>
                        <th>Return_Date</th>
                        <th>Quantity</th>
                        <th>Bill_ID</th>
                        <s:if test="%{#session.USER.name != null and #session.USER.role_id == 1 and #session.USER.name != ''}">
                            <th>Feedback</th>
                        </s:if>
                        
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="details" status="counter">
                        <tr>
                            <td> <s:property value="%{#counter.count}"/> </td>
                            <td> <s:property value="%{#dto.id}"/> </td>
                            <td> <s:property value="%{#dto.name}"/> </td>
                            <td> <s:property value="%{#dto.rental_date}"/> </td>
                            <td> <s:property value="%{#dto.return_date}"/> </td>
                            <td> <s:property value="%{#dto.quantity}"/> </td>
                            <td> <s:property value="%{#dto.bill_id}"/> </td>
                            <s:if test="%{#session.USER.name != null and #session.USER.role_id == 1 and #session.USER.name != ''}">
                            <td>  
                                <s:url var="fb" value="detail">
                                    <s:param name="carName" value="%{#dto.name}"/>
                                </s:url>
                                <s:a href="%{fb}">Feedback</s:a>
                            </td>
                            </s:if>                            

                        </tr>                        
                    </s:iterator>
                </tbody>
            </table>

        </s:if>
    </body>
</html>
