<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="urlAdd" value="/sale/edit.html" />
<a class="button" href="${urlAdd}"><fmt:message key="add" /></a>

<c:url var="urlDelete" value="/sale/delete.html" />
<form action="${urlDelete}" method="post">
    <table class="table-collection">
        <thead>
            <tr>
                <td><fmt:message key="sales.number" /></td>
                <td><fmt:message key="sales.date" /></td>
                <td><fmt:message key="sales.held" /></td>
                <td>&nbsp;</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sale" varStatus="status" items="${sales}">
                <tr>
                    <td>
                        <c:url var="url" value="/sale/edit.html">
                            <c:param name="id" value="${sale.id}" />
                        </c:url>
                        <a href="${url}">${status.index + 1}</a>
                    </td>
                    <td>
                        <fmt:formatDate value="${sale.date}" pattern="${DATE_FORMAT}" />
                    </td>
                    <td>${sale.held}</td>
                    <td>
                        <input name="ids" type="checkbox" value="${sale.id}" />
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty sales}">
        <fmt:message key="delete" var="deleteMsg" />
        <input type="submit" value="${deleteMsg}" />
    </c:if>
</form>
