<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:write property="title">Quản trị Hệ thống</sitemesh:write></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <sitemesh:write property="head" />
</head>
<body>

<header class="topbar admin">
    <div class="brand">
        <a href="${pageContext.request.contextPath}/admin/dashboard" style="color: #fff; text-decoration: none;">JETJET TÁO - ADMIN CONTROL PANEL</a>
    </div>

    <nav>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/categories">Quản lý Category</a>
        <a href="${pageContext.request.contextPath}/admin/products">Quản lý Product</a>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary btn-sm">Xem trang Web</a>

        <span class="user-info">
            Admin: <strong>${sessionScope.account.fullname}</strong>
        </span>

        <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Đăng xuất</a>
    </nav>
</header>

<main class="container">
    <sitemesh:write property="body" />
</main>

</body>
</html>
