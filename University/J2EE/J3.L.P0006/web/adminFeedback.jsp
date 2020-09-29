<%-- 
    Document   : adminFeedback
    Created on : Mar 18, 2020, 8:49:37 PM
    Author     : Tuan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Feedback</title>
    </head>
    <body>
        <h1>Feedback for <s:property value="%{name}"/></h1>
        <s:if test="%{feedback != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Point</th>
                        <th>Email</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="feedback">
                        <tr>
                            <td><s:property value="%{#dto.id}"/></td>
                            <td><s:property value="%{#dto.point}"/></td>
                            <td><s:property value="%{#dto.email}"/></td>
                            <td><s:property value="%{#dto.date}"/></td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

        </s:if>
        <s:if test="%{feedback == null}">
            <h3>The car hasn't feedback yet !</h3>
        </s:if>
    </body>
</html>
