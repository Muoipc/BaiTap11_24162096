<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Product (Phân trang)</title>
</head>
<body>

<div class="page-title">
    <span>Quản lý Product</span>
    <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary btn-sm">+ Thêm Product Mới</a>
</div>

<div class="card">
    <table class="data-table">
        <thead>
            <tr>
                <th style="width: 70px;">Mã ID</th>
                <th style="width: 80px;">Hình ảnh</th>
                <th>Tên Sản phẩm</th>
                <th style="width: 120px;">Mã SP</th>
                <th style="width: 140px;">Danh mục</th>
                <th style="width: 150px;">Cửa hàng (Seller)</th>
                <th style="width: 130px;">Đơn giá</th>
                <th style="width: 90px;">Amount</th>
                <th style="width: 140px; text-align: center;">Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${products}">
                <tr>
                    <td><strong>#${p.productId}</strong></td>
                    <td>
                        <img src="${p.images}" alt="${p.productName}" style="width: 55px; height: 55px; object-fit: cover; border-radius: 4px; border: 1px solid #cbd5e1;">
                    </td>
                    <td>
                        <span style="font-weight: 600; color: #1e3a8a;">${p.productName}</span>
                    </td>
                    <td><code>${p.productCode}</code></td>
                    <td>${p.category != null ? p.category.categoryName : 'Chưa gán'}</td>
                    <td>
                        <span style="background: #e2e8f0; padding: 2px 8px; border-radius: 4px; font-size: 0.85rem; font-weight: 500;">
                            ${p.seller != null ? p.seller.sellername : 'Chính hãng'}
                        </span>
                    </td>
                    <td><span class="price-tag" style="font-size: 0.95rem;">${p.formattedPrice} đ</span></td>
                    <td>${p.amount}</td>
                    <td style="text-align: center;">
                        <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.productId}" class="btn btn-primary btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.productId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div style="margin-top: 16px; font-size: 0.9rem; color: #64748b;">
        Tổng số sản phẩm: <strong>${totalItems}</strong> | Trang <strong>${currentPage}</strong> / <strong>${totalPages}</strong>
    </div>

    <c:if test="${totalPages > 1}">
        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <span class="active">${i}</span>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/admin/products?page=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:if>
</div>

</body>
</html>
