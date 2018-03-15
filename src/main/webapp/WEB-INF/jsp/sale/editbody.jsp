<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="currentDate" class="java.util.Date" />

<div id="submenu">
    <ul>
        <c:forEach var="productGroup" items="${productGroups}">
            <li>
                <c:url var="changeGroupUrl" value="/sale/edit.html">
                    <c:param name="id" value="${sale.id}" />
                    <c:param name="groupId" value="${productGroup.id}" />
                </c:url>
            <c:set var="selected" value="" />
            <c:if test="${productGroup.id eq groupId}">
                <c:set var="selected" value="selected" />
            </c:if>
                <a class="button ${selected}" href="${changeGroupUrl}">${productGroup.name}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<div id="page-subcontent">
<c:url var="saveUrl" value="/sale/edit.html" />
<form method="post" action="${saveUrl}">
    <div class="element">
        <input name="id" value="${sale.id}" type="hidden" />
        <input name="groupId" value="${groupId}" type="hidden" />
        <table>
            <tr>
                <th><fmt:message key="sale.date" /></th>
                <td>
                    <c:set var="saleDate" value="${sale.date}" />
                    <c:if test="${empty sale.date}">
                        <c:set var="saleDate" value="${currentDate}" />
                    </c:if>
                    <fmt:formatDate var="date" value="${saleDate}" pattern="${DATE_FORMAT}" />
                    <input name="date" value="${date}" type="text" maxlength="10" />
                </td>
            </tr>
        </table>
        <fmt:message key="save" var="saveBtn" />
        <input type="submit" value="${saveBtn}" />
        <fmt:message key="hold" var="holdBtn" />
        <input type="submit" value="${holdBtn}" />
    </div>
    <c:forEach var="saleItem" items="${saleItems}">
        <table class="table-collection">
            <caption>${saleItem.product.name} (${saleItem.balance})</caption>
            <thead>
                <tr>
                    <td><fmt:message key="invoice.item.price" /></td>
                    <td><fmt:message key="invoice.item.quantity" /></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${saleItem.items}">
                    <tr>
                        <td>
                            <c:set var="readonlyPrice" value="" />
                            <c:if test="${saleItem.product.price eq item.price}">
                                <c:set var="readonlyPrice" value="readonly='readonly'" />
                            </c:if>
                            <input name="price" type="text" value="${item.price}" ${readonlyPrice} />
                        </td>
                        <td>
                            <input name="itemId" type="hidden" value="${item.id}" />
                            <input name="productId" type="hidden" value="${saleItem.product.id}" />
                            <input name="quantity" type="text" value="${item.quantity}" />
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <input name="price" type="text" />
                    </td>
                    <td>
                        <input name="itemId" type="hidden" />
                        <input name="productId" type="hidden" value="${saleItem.product.id}" />
                        <input name="quantity" type="text" value="0" />
                    </td>
                </tr>
            </tbody>
        </table>
    </c:forEach>
</form>
</div>
