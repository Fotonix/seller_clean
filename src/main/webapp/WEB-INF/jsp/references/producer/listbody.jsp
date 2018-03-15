<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="urlAdd" value="/references/producer/edit.html" />
<a class="button" href="${urlAdd}"><fmt:message key="add" /></a>

<c:url var="urlDelete" value="/references/producer/delete.html" />
<form action="${urlDelete}" method="post">
    <c:forEach var="producer" items="${producers}">
        <div class="element collection-element">
            <table>
                <tbody>
                    <tr>
                        <td>
                            <input name="ids" type="checkbox" value="${producer.id}" />
                        </td>
                        <th><fmt:message key="producer.name" />:</th>
                        <td>
                            <c:url var="url" value="/references/producer/edit.html">
                                <c:param name="id" value="${producer.id}" />
                            </c:url>
                            <a href="${url}">${producer.name}</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </c:forEach>
    <c:if test="${not empty producers}">
        <fmt:message key="delete" var="deleteMsg" />
        <input type="submit" value="${deleteMsg}" />
    </c:if>
</form>
