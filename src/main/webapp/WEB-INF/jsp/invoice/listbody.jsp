<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="urlAdd" value="/invoice/edit.html" />
<a class="button" href="${urlAdd}"><fmt:message key="add" /></a>

<c:url var="urlDelete" value="/invoice/delete.html" />
<form action="${urlDelete}" method="post">
    <table class="table-collection">
        <thead>
            <tr>
                <td><fmt:message key="invoices.number" /></td>
                <td><fmt:message key="invoices.date" /></td>
                <td><fmt:message key="invoices.series" /></td>
                <td><fmt:message key="invoices.producer" /></td>
                <td><fmt:message key="invoices.held" /></td>
                <td>&nbsp;</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="invoice" varStatus="status" items="${invoices}">
                <tr>
                    <td>
                        <c:url var="url" value="/invoice/edit.html">
                            <c:param name="id" value="${invoice.id}" />
                        </c:url>
                        <a href="${url}">${status.index + 1}</a>
                    </td>
                    <td>
                        <fmt:formatDate value="${invoice.date}" pattern="${DATE_FORMAT}" />
                    </td>
                    <td>${invoice.series}</td>
                    <td>${invoice.producer}</td>
                    <td>${invoice.held}</td>
                    <td>
                        <input name="ids" type="checkbox" value="${invoice.id}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty invoices}">
        <fmt:message key="delete" var="deleteMsg" />
        <input type="submit" value="${deleteMsg}" />
    </c:if>
</form>
