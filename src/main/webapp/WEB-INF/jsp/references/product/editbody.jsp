<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="url" value="/references/product/edit.html" />
<form method="post" action="${url}">
    <div class="element">
        <input name="id" value="${product.id}" type="hidden" />
        <table>
            <tr>
                <th><fmt:message key="product.name" /></th>
                <td><input name="name" value="${product.name}" type="text" maxlength="64" /></td>
            </tr>
            <tr>
                <th><fmt:message key="product.group" /></th>
                <td>
                    <select name="groupId">
                        <c:forEach var="group" items="${groups}">
                            <c:set var="selectedGroup" value="" />
                            <c:if test="${group.id eq product.groupId}">
                                <c:set var="selectedGroup" value="selected='selected'" />
                            </c:if>
                            <option value="${group.id}" ${selectedGroup}>
                                ${group.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="product.price" /></th>
                <td><input name="price" value="${product.price}" type="text" maxlength="9" /></td>
            </tr>
        </table>
        <fmt:message key="save" var="saveBtn" />
        <input type="submit" value="${saveBtn}" />
    </div>
</form>
