<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="url" value="/invoice/item/edit.html" />
<form method="post" action="${url}">
    <div class="element">
        <input name="invoiceId" value="${param.invoiceId}" type="hidden" />
        <input name="itemId" value="${item.id}" type="hidden" />
        <table>
            <tr>
                <th><fmt:message key="invoice.item.group" /></th>
                <td>
                    <select name="groupId">
                        <c:forEach var="group" items="${productGroups}">
                            <c:set var="selectedGroup" value="" />
                            <c:if test="${group.id eq item.productGroupId}">
                                <c:set var="selectedGroup" value="selected='selected'" />
                            </c:if>
                            <option value="${group.id}" ${selectedGroup}>
                                ${group.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${empty item.id}">
                            <fmt:message key="select" var="changeGroupBtn" />
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="change" var="changeGroupBtn" />
                        </c:otherwise>
                    </c:choose>
                    <input name="changeGroup" type="submit" value="${changeGroupBtn}" />
                </td>
            </tr>
            <tr>
                <th><fmt:message key="invoice.item.product" /></th>
                <td>
                    <select name="productId">
                        <c:forEach var="product" items="${products}">
                            <c:set var="selectedProduct" value="" />
                            <c:if test="${product.id eq item.productId}">
                                <c:set var="selectedProduct" value="selected='selected'" />
                            </c:if>
                            <option value="${product.id}" ${selectedProduct}>
                                ${product.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="invoice.item.quantity" /></th>
                <td><input name="quantity" value="${item.quantity}" type="text" maxlength="3" /></td>
            </tr>
            <tr>
                <th><fmt:message key="invoice.item.cost" /></th>
                <td><input name="cost" value="${item.cost}" type="text" maxlength="9" /></td>
            </tr>
            <tr>
                <th><fmt:message key="invoice.item.price" /></th>
                <td><input name="price" value="${item.price}" type="text" maxlength="9" /></td>
            </tr>
        </table>

        <c:set var="disableSave" value="" />
        <c:if test="${empty item.productGroupId}">
            <c:set var="disableSave" value="disabled='disabled'" />
        </c:if>
        <fmt:message key="save" var="saveBtn" />
        <input type="submit" value="${saveBtn}" ${disableSave} />

        <c:url var="urlBack" value="/invoice/edit.html">
            <c:param name="id" value="${param.invoiceId}" />
        </c:url>
        <a class="button" href="${urlBack}"><fmt:message key="back" /></a>
    </div>
</form>
