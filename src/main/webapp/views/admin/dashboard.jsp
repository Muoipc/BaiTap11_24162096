<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bảng Điều Khiển Quản Trị</title>
</head>
<body>

<div class="page-title">
    <span>Bảng Điều Khiển Quản Trị (Admin Dashboard)</span>
</div>

<div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 20px; margin-bottom: 32px;">
    <div class="card" style="border-left: 5px solid #2563eb;">
        <div style="font-size: 0.9rem; color: #64748b; font-weight: 600; text-transform: uppercase;">Tổng Category</div>
        <div style="font-size: 2.2rem; font-weight: 700; color: #1e3a8a; margin-top: 8px;">${totalCategories}</div>
    </div>

    <div class="card" style="border-left: 5px solid #16a34a;">
        <div style="font-size: 0.9rem; color: #64748b; font-weight: 600; text-transform: uppercase;">Tổng Product</div>
        <div style="font-size: 2.2rem; font-weight: 700; color: #15803d; margin-top: 8px;">${totalProducts}</div>
    </div>

    <div class="card" style="border-left: 5px solid #f59e0b;">
        <div style="font-size: 0.9rem; color: #64748b; font-weight: 600; text-transform: uppercase;">Cửa hàng (Seller)</div>
        <div style="font-size: 2.2rem; font-weight: 700; color: #b45309; margin-top: 8px;">${totalSellers}</div>
    </div>

    <div class="card" style="border-left: 5px solid #8b5cf6;">
        <div style="font-size: 0.9rem; color: #64748b; font-weight: 600; text-transform: uppercase;">Người Dùng (User)</div>
        <div style="font-size: 2.2rem; font-weight: 700; color: #6d28d9; margin-top: 8px;">${totalUsers}</div>
    </div>
</div>

<div class="card">
    <h3 style="margin-bottom: 16px; color: #0f172a;">Lối Tắt Chức Năng Quản Trị</h3>
    <div style="display: flex; gap: 16px; flex-wrap: wrap;">
        <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-primary">
            Quản lý Category &raquo;
        </a>
        <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-success">
            Quản lý Product &raquo;
        </a>
        <a href="${pageContext.request.contextPath}/seller-products" class="btn btn-secondary">
            Xem Sản phẩm theo Cửa hàng &raquo;
        </a>
    </div>
</div>

</body>
</html>
