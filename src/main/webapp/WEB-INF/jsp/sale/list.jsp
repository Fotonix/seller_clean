<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
    "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.w3.org/MarkUp/SCHEMA/xhtml11.xsd"
    xml:lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><fmt:message key="title" /></title>

    <%@include file="/WEB-INF/jsp/layout/import.jsp" %>
</head>
<body>
    <%@include file="/WEB-INF/jsp/layout/header.jsp"%>
    <jsp:include page="/WEB-INF/jsp/layout/menu.jsp">
        <jsp:param name="selectedMenuItem" value="sale" />
    </jsp:include>

    <div id="main-content">
        <div id="page-content">
            <%@include file="listbody.jsp"%>
        </div>
    </div>

</body>
</html>