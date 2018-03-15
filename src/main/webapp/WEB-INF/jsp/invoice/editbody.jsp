<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="saveUrl" value="/invoice/edit.html" />
<form method="post" action="${saveUrl}">
    <div class="element">
        <input name="id" value="${invoice.id}" type="hidden" />
        <table>
            <tr>
                <th><fmt:message key="invoice.date" /></th>
                <td>
                    <fmt:formatDate var="date" value="${invoice.date}" pattern="${DATE_FORMAT}" />
                    <input name="date" value="${date}" type="text" maxlength="10" />
                </td>
                <th><fmt:message key="invoice.series" /></th>
                <td><input name="series" value="${invoice.series}" type="text" maxlength="64" /></td>
            </tr>
            <tr>
                <th><fmt:message key="invoice.producer" /></th>
                <td>
                    <select name="producerId">
                        <c:forEach var="producer" items="${producers}">
                            <c:set var="selectedProducer" value="" />
                            <c:if test="${producer.id eq invoice.producerId}">
                                <c:set var="selectedProducer" value="selected='selected'" />
                            </c:if>
                            <option value="${producer.id}" ${selectedProducer}>
                                ${producer.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
        </table>
        <fmt:message key="save" var="saveBtn" />
        <input type="submit" value="${saveBtn}" />
        <fmt:message key="hold" var="holdBtn" />
        <input type="submit" value="${holdBtn}" />
    </div>
</form>

<c:if test="${not empty invoice.id}">
    <c:url var="urlAdd" value="/invoice/item/edit.html">
        <c:param name="invoiceId" value="${invoice.id}" />
    </c:url>
    <a class="button" href="${urlAdd}"><fmt:message key="add" /></a>
</c:if>

<c:url var="deleteUrl" value="/invoice/item/delete.html" />
<form method="post" action="${deleteUrl}">
    <c:if test="${not empty items}">
        <input name="invoiceId" value="${invoice.id}" type="hidden" />
        <table class="table-collection">
            <thead>
                <tr>
                    <td><fmt:message key="invoice.item.number" /></td>
                    <td><fmt:message key="invoice.item.group" /></td>
                    <td><fmt:message key="invoice.item.product" /></td>
                    <td><fmt:message key="invoice.item.quantity" /></td>
                    <td><fmt:message key="invoice.item.cost" /></td>
                    <td><fmt:message key="invoice.item.price" /></td>
                    <td>&nbsp;</td>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${items}" varStatus="status">
                    <tr>
                        <td>
                            <c:url var="url" value="/invoice/item/edit.html">
                                <c:param name="invoiceId" value="${invoice.id}" />
                                <c:param name="itemId" value="${item.id}" />
                            </c:url>
                            <a href="${url}">${status.index + 1}</a>
                        </td>
                        <td>${item.group}</td>
                        <td>${item.product}</td>
                        <td>${item.quantity}</td>
                        <td>${item.cost}</td>
                        <td>${item.price}</td>
                        <td>
                            <input name="ids" type="checkbox" value="${item.id}" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <fmt:message key="delete" var="deleteMsg" />
        <input type="submit" value="${deleteMsg}" />
    </c:if>
</form>
