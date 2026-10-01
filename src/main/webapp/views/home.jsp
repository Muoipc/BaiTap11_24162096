<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ - Cửa hàng Trực tuyến</title>
</head>
<body>

<div class="card" style="background: linear-gradient(135deg, #1e3a8a, #3b82f6); color: #fff; padding: 32px;">
    <h1 style="font-size: 2rem; margin-bottom: 8px;">Chào mừng bạn đến với JetJet Táo</h1>
    <p style="font-size: 1.05rem; opacity: 0.95;">
        Hệ thống Lập trình Web - Servlet + JPA + JSP - Sinh viên thực hiện: <strong>Nguyễn Song Hoàng Phúc (24162096)</strong>
    </p>
    <div style="margin-top: 16px;">
        <a href="${pageContext.request.contextPath}/seller-products" class="btn btn-primary" style="background: #ffffff; color: #1e3a8a; font-weight: 700;">
            Xem Sản phẩm Gom theo Seller →
        </a>
    </div>
</div>

<div class="page-title">
    <span>Danh mục Sản phẩm</span>
</div>

<div style="display: grid; grid-template-columns: repeat(auto-fill, minmax(240px, 1fr)); gap: 16px; margin-bottom: 32px;">
    <c:forEach var="c" items="${categories}">
        <div class="card" style="padding: 16px; text-align: center;">
            <img src="${c.images}" alt="${c.categoryName}" style="width: 100%; height: 130px; object-fit: cover; border-radius: 6px; margin-bottom: 12px;">
            <h3 style="font-size: 1.05rem; font-weight: 600; color: #1e293b;">${c.categoryName}</h3>
            <span style="font-size: 0.85rem; color: #64748b;">Mã DM: #${c.categoryId}</span>
        </div>
    </c:forEach>
</div>

<div class="page-title">
    <span>Sản phẩm Nổi bật</span>
    <a href="${pageContext.request.contextPath}/seller-products" style="font-size: 0.95rem;">Xem tất cả theo Seller &raquo;</a>
</div>

<div style="display: grid; grid-template-columns: repeat(auto-fill, minmax(260px, 1fr)); gap: 20px;">
    <c:forEach var="p" items="${products}">
        <div class="card" style="padding: 16px; display: flex; flex-direction: column;">
            <img src="${p.images}" alt="${p.productName}" style="width: 100%; height: 180px; object-fit: cover; border-radius: 6px; margin-bottom: 12px;">
            <h4 style="font-size: 1.05rem; font-weight: 700; margin-bottom: 6px;">
                <a href="${pageContext.request.contextPath}/product-detail?id=${p.productId}">${p.productName}</a>
            </h4>
            <div style="font-size: 0.9rem; color: #475569; margin-bottom: 4px;">
                Cửa hàng: <strong>${p.seller != null ? p.seller.sellername : 'Chính hãng'}</strong>
            </div>
            <div class="price-tag" style="margin-bottom: 12px;">
                ${p.formattedPrice} đ
            </div>
            <div style="margin-top: auto; display: flex; gap: 8px;">
                <form action="${pageContext.request.contextPath}/cart/add" method="post" style="flex: 1;">
                    <input type="hidden" name="productId" value="${p.productId}">
                    <input type="hidden" name="quantity" value="1">
                    <button type="submit" class="btn btn-primary btn-sm" style="width: 100%;">
                        🛒 Thêm vào giỏ
                    </button>
                </form>
                <a href="${pageContext.request.contextPath}/product-detail?id=${p.productId}" class="btn btn-secondary btn-sm" style="padding: 6px 12px;">
                    Chi tiết
                </a>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
