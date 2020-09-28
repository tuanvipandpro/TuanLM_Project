<%-- 
    Document   : history
    Created on : Mar 8, 2020, 7:21:46 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <a href="home">Home</a><br/>
        <h1>History</h1>
        <form action="HistorySearch">
            Search By :                     
                <select name="historyType">
                    <option>Name</option>
                    <option>Date</option>
                </select><br/>  
            Name : <input type="text" name="historyName" value="${param.historyName}" /><br/>  
            Or After Booking Date : <input type="date" name="historyDate" value="${param.historyDate}" /><br/>  
            <input type="submit" value="Search" />
        </form><br/>
        
        <s:if test="%{list != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Total</th>
                        <th>Booking_Date</th>
                        <th>Delete</th>
                        <th>View</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="list" status="counter">
                        <tr>
                            <td> <s:property value="%{#counter.count}"/> </td>
                            <td> <s:property value="%{#dto.id}"/> </td>
                            <td> <s:property value="%{#dto.total}"/> </td>
                            <td> <s:property value="%{#dto.booking_date}"/> </td>
                            <td> 
                                <a href="deleteHistory?pk=<s:property value="%{#dto.id}"/>" onclick="return confirm('Do you want to remove ?')">Delete</a>
                            </td>
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
        <s:if test="%{list == null}">
            <h3>Your history is empty !</h3>
            <h4>Please renting car to see your history !</h4>
        </s:if>
    </body>
</html>
