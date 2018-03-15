<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="url" value="/references/product/group/attribute/item/edit.html" />
<form method="post" action="${url}">
    <div class="element">
        <input name="id" value="${item.id}" type="hidden" />
        <input name="attributeId" value="${param.attributeId}" type="hidden" />
        <table>
            <tr>
                <th><fmt:message key="product.group.attribute.item.name" /></th>
                <td><input name="name" value="${item.name}" type="text" maxlength="64" /></td>
            </tr>
        </table>
        <fmt:message key="save" var="saveBtn" />
        <input type="submit" value="${saveBtn}" />
    </div>
</form>
