<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="urlAdd" value="/references/product/group/edit.html" />
<a class="button" href="${urlAdd}"><fmt:message key="add" /></a>

<c:url var="urlDelete" value="/references/product/group/delete.html" />
<form action="${urlDelete}" method="post">
    <c:forEach var="group" items="${productGroups}">
        <div class="element collection-element">
            <table border="1">
                <tbody>
                    <tr>
                        <td>
                            <input name="ids" type="checkbox" value="${group.id}" />
                        </td>
                        <th><fmt:message key="product.group.name" />:</th>
                        <td>
                            <c:url var="url" value="/references/product/group/edit.html">
                                <c:param name="id" value="${group.id}" />
                            </c:url>
                            <a href="${url}">${group.name}</a>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <c:url var="urlAdd" value="/references/product/group/attribute/edit.html">
                                <c:param name="groupId" value="${group.id}" />
                            </c:url>
                            <a class="button" href="${urlAdd}">
                                <fmt:message key="product.group.attribute.add" />
                            </a>
                        </td>
                    </tr>
                    <c:forEach var="attribute" items="${group.groupAttributes}">
                        <tr>
                            <td colspan="3">
                                <c:url var="urlAttr" value="/references/product/group/attribute/edit.html">
                                    <c:param name="id" value="${attribute.id}" />
                                </c:url>
                                <a href="${urlAttr}"><c:out value="${attribute.name}" /></a>
                                <c:url var="urlItemAdd" value="/references/product/group/attribute/item/edit.html">
                                    <c:param name="attributeId" value="${attribute.id}" />
                                </c:url>
                                (<a href="${urlItemAdd}"><fmt:message key="add" /></a>)
                                <c:if test="${not empty attribute.attributeItems}">
                                    :
                                    <c:forEach var="item" items="${attribute.attributeItems}" varStatus="status">
                                        <c:url var="urlItem" value="/references/product/group/attribute/item/edit.html">
                                            <c:param name="attributeId" value="${attribute.id}" />
                                            <c:param name="id" value="${item.id}" />
                                        </c:url>
                                        <a href="${urlItem}"><c:out value="${item.name}" /></a><c:if test="${not status.last}">,</c:if>
                                    </c:forEach>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
    <c:if test="${not empty productGroups}">
        <fmt:message key="delete" var="deleteMsg" />
        <input type="submit" value="${deleteMsg}" />
    </c:if>
</form>
