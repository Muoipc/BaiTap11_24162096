<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><sitemesh:write property="title">Trang chủ - JetJet Táo</sitemesh:write></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <sitemesh:write property="head" />
</head>
<body>

<header class="topbar">
    <div class="brand">
        <a href="${pageContext.request.contextPath}/home" style="color: #fff; text-decoration: none;">JETJET TÁO</a>
    </div>

    <nav>
        <a href="${pageContext.request.contextPath}/home">Trang Chủ</a>
        <a href="${pageContext.request.contextPath}/seller-products">Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/cart" style="display: inline-flex; align-items: center; gap: 5px;">
            🛒 Giỏ hàng
            <c:if test="${not empty sessionScope.cart && sessionScope.cart.totalQuantity > 0}">
                <span style="background: #ef4444; color: #fff; font-size: 0.75rem; font-weight: 700; padding: 2px 7px; border-radius: 10px; line-height: 1;">
                    ${sessionScope.cart.totalQuantity}
                </span>
            </c:if>
        </a>

        <c:choose>
            <c:when test="${not empty sessionScope.account}">
                <a href="${pageContext.request.contextPath}/order-history">📦 Lịch sử đơn</a>

                <c:if test="${sessionScope.account.role != null && sessionScope.account.role.roleName == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="admin-badge">Trang quản trị</a>
                </c:if>

                <span class="user-info">
                    Chào, <strong>${sessionScope.account.fullname}</strong>
                    <c:if test="${sessionScope.account.role != null}">
                        (${sessionScope.account.role.roleName})
                    </c:if>
                </span>

                <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger btn-sm">Đăng xuất</a>
            </c:when>
            <c:otherwise>
                <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
                <a href="${pageContext.request.contextPath}/register">Đăng ký</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>

<main class="container">
    <sitemesh:write property="body" />
</main>

</body>
</html>
