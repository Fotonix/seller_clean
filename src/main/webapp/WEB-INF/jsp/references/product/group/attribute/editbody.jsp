<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="url" value="/references/product/group/attribute/edit.html" />
<form method="post" action="${url}">
    <div class="element">
        <input name="id" value="${groupAttribute.id}" type="hidden" />
        <input name="groupId" value="${param.groupId}" type="hidden" />
        <table>
            <tr>
                <th><fmt:message key="product.group.attribute.name" /></th>
                <td><input name="name" value="${groupAttribute.name}" type="text" maxlength="64" /></td>
            </tr>
        </table>
        <fmt:message key="save" var="saveBtn" />
        <input type="submit" value="${saveBtn}" />
    </div>
</form>
