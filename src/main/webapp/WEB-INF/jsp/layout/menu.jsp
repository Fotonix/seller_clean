<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="main-menu">
    <ul>
        <%-- Main --%>
        <c:set var="selected" value="" />
        <c:if test="${'main' eq param.selectedMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/main.html" />
            <a href="${url}"><fmt:message key="menu.main" /></a>
        </li>
        <%-- Invoice --%>
        <c:set var="selected" value="" />
        <c:if test="${'invoice' eq param.selectedMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/invoice.html" />
            <a href="${url}"><fmt:message key="menu.invoice" /></a>
        </li>
        <%-- Selling --%>
        <c:set var="selected" value="" />
        <c:if test="${'sale' eq param.selectedMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/sale.html" />
            <a href="${url}"><fmt:message key="menu.sale" /></a>
        </li>
        <%-- References --%>
        <c:set var="selected" value="" />
        <c:if test="${'references' eq param.selectedMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/references.html" />
            <a href="${url}"><fmt:message key="menu.references" /></a>
        </li>
        <%-- Reports --%>
        <c:set var="selected" value="" />
        <c:if test="${'reports' eq param.selectedMenuItem}"><c:set var="selected" value="selected" /></c:if>
        <li class="${selected}">
            <c:url var="url" value="/reports.html" />
            <a href="${url}"><fmt:message key="menu.reports" /></a>
        </li>
    </ul>
</div>
