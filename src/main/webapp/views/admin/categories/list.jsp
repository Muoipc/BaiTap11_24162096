<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Category (Phân trang)</title>
</head>
<body>

<div class="page-title">
    <span>Quản lý Category</span>
    <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary btn-sm">+ Thêm Category Mới</a>
</div>

<div class="card">
    <table class="data-table">
        <thead>
            <tr>
                <th style="width: 80px;">Mã ID</th>
                <th style="width: 100px;">Hình ảnh</th>
                <th>Tên Danh mục</th>
                <th style="width: 130px;">Trạng thái</th>
                <th style="width: 160px; text-align: center;">Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="c" items="${categories}">
                <tr>
                    <td><strong>#${c.categoryId}</strong></td>
                    <td>
                        <img src="${c.images}" alt="${c.categoryName}" style="width: 60px; height: 60px; object-fit: cover; border-radius: 4px; border: 1px solid #cbd5e1;">
                    </td>
                    <td><span style="font-weight: 600; color: #1e293b;">${c.categoryName}</span></td>
                    <td>
                        <c:choose>
                            <c:when test="${c.status == 1}">
                                <span style="color: #16a34a; font-weight: 600;">● Hoạt động</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #dc2626; font-weight: 600;">● Đã khóa</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td style="text-align: center;">
                        <a href="${pageContext.request.contextPath}/admin/category/edit?id=${c.categoryId}" class="btn btn-primary btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/category/delete?id=${c.categoryId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa category này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div style="margin-top: 16px; font-size: 0.9rem; color: #64748b;">
        Tổng số danh mục: <strong>${totalItems}</strong> | Trang <strong>${currentPage}</strong> / <strong>${totalPages}</strong>
    </div>

    <c:if test="${totalPages > 1}">
        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <span class="active">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/admin/categories?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:if>
</div>

</body>
</html>
