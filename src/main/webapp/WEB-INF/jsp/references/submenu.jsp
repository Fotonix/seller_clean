<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="submenu">
    <ul>
        <%-- Products --%>
        <c:set var="selected" value="" />
        <c:if test="${'products' eq param.selectedSubMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/references/product/list.html" />
            <a href="${url}" class="submenu-item"><fmt:message key="submenu.references.products" /></a>
        </li>
        <%-- Product Groups --%>
        <c:set var="selected" value="" />
        <c:if test="${'productGroups' eq param.selectedSubMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/references/product/group/list.html" />
            <a href="${url}" class="submenu-item"><fmt:message key="submenu.references.product.groups" /></a>
        </li>
        <%-- Producers --%>
        <c:set var="selected" value="" />
        <c:if test="${'producers' eq param.selectedSubMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/references/producer/list.html" />
            <a href="${url}" class="submenu-item"><fmt:message key="submenu.references.producers" /></a>
        </li>
    </ul>
</div>
