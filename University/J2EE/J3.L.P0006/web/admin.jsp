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
        <title>Admin</title>
    </head>
    <body>
        <a href="admin">Admin</a> ||
        <a href="createCarLink">New Car</a> ||
        <a href="managerAdmin">Manager</a> ||
        <a href="createSaleLink">Create Sale Code</a> ||
        <a href="logout">Logout</a> 
        <br/>
        
        <s:if test="%{#session.USER.name != null and #session.USER.name != ''}">
            <font color="red">
                Welcome, <s:property value="%{#session.USER.name}"/>
            </font>            
        </s:if>
        <h1>Admin</h1>
        <s:if test="%{list != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Year</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Color</th>
                        <th>Status</th>
                        <th>Update</th>
                        <th>Feedback</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="dto" value="list" status="counter">
                        <form action="update">
                        <tr>
                        <input type="hidden" name="id" value="<s:property value="%{#dto.id}"/>" />
                            <td> <s:property value="%{#counter.count}"/> </td>
                            <td> <s:property value="%{#dto.name}"/> </td>
                            <td> <s:property value="%{#dto.year}"/> </td>
                            <td>
                                <select name="category">
                                    <s:iterator var="cat" value="%{#session.CATEGORY}">
                                        <s:if test="%{#dto.category == #cat}">
                                            <option selected="true"><s:property value="%{#cat}"/></option>
                                        </s:if>
                                        <s:if test="%{#dto.category != #cat}">
                                            <option><s:property value="%{#cat}"/></option>
                                        </s:if>
                                    </s:iterator>
                                </select>                            
                            </td>
                            <td> 
                                <input type="number" name="price" value="<s:property value="%{#dto.price}"/>" />
                            </td>
                            <td> 
                                <input type="number" name="quantity" value="<s:property value="%{#dto.quantity}"/>" />
                            </td>
                            <td> 
                                <select name="color">
                                    <s:iterator var="col" value="%{#session.COLOR}">
                                        <s:if test="%{#dto.color == #col}">
                                            <option selected="true"><s:property value="%{#col}"/></option>
                                        </s:if>
                                        <s:if test="%{#dto.color != #col}">
                                            <option><s:property value="%{#col}"/></option>
                                        </s:if>
                                    </s:iterator>
                                </select>
                            </td>
                            <td> 
                               <select name="status">
                                   <s:if test="%{#dto.status == 1}">
                                       <option selected="true">New</option>
                                       <option>Active</option>
                                       <option>InActive</option>
                                   </s:if>                                       
                                   <s:if test="%{#dto.status == 2}">
                                       <option>New</option>
                                       <option selected="true">Active</option>
                                       <option>InActive</option>
                                   </s:if>
                                   <s:if test="%{#dto.status == 3}">
                                       <option>New</option>
                                       <option>Active</option>
                                       <option selected="true">InActive</option>
                                   </s:if>                                   
                                </select>
                            </td>
                            <td> <input type="submit" value="Update" /> </td>
                            <td> <a href="adminFeedback?id=<s:property value="%{#dto.id}"/>&name=<s:property value="%{#dto.name}"/>">View Feedback</a> </td>
                        </tr>
                        </form>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:if test="%{list == null}">
            <h3>No record is match !</h3>
        </s:if>  
    </body>
</html>
