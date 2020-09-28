<%-- 
    Document   : manager
    Created on : Mar 19, 2020, 8:24:29 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Page</title>
    </head>
    <body>
        <h1>Manager Bill</h1>
        <s:if test="%{manager != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Total</th>
                        <th>Booking_Date</th>
                        <th>View</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="manager" status="counter">
                        <tr>
                            <td> <s:property value="%{#counter.count}"/> </td>
                            <td> <s:property value="%{#dto.id}"/> </td>
                            <td> <s:property value="%{#dto.total}"/> </td>
                            <td> <s:property value="%{#dto.booking_date}"/> </td>
                            <td>
                                <s:url var="detailsLink" value="detailsLink">
                                    <s:param name="pk" value="%{#dto.id}"/>
                                </s:url>
                                <s:a href="%{detailsLink}">View</s:a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>            
        </s:if>
    </body>
</html>
