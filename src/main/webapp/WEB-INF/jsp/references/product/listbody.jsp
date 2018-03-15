<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="urlAdd" value="/references/product/edit.html" />
<a class="button" href="${urlAdd}"><fmt:message key="add" /></a>

<c:url var="urlDelete" value="/references/product/delete.html" />
<form action="${urlDelete}" method="post">
    <c:forEach var="product" items="${products}">
        <div class="element collection-element">
            <table>
                <tbody>
                    <tr>
                        <td rowspan="2">
                            <input name="ids" type="checkbox" value="${product.id}" />
                        </td>
                        <th><fmt:message key="product.name" />:</th>
                        <td>${product.name}</td>
                    </tr>
                    <tr>
                        <th><fmt:message key="product.group" />:</th>
                        <td>${product.group}</td>
                    </tr>
                </tbody>
            </table>
            <c:url var="url" value="/references/product/edit.html">
                <c:param name="id" value="${product.id}" />
            </c:url>
            <a href="${url}" class="save-btn"></a>
        </div>
    </c:forEach>
    <c:if test="${not empty products}">
        <fmt:message key="delete" var="deleteMsg" />
        <input type="submit" value="${deleteMsg}" />
    </c:if>
</form>
