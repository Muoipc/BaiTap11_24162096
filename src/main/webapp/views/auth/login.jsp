<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>

<div class="auth-wrapper">
    <div class="card">
        <h2 style="text-align: center; margin-bottom: 20px; color: #1e3a8a;">Đăng nhập Hệ thống</h2>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <c:if test="${param.verified == 'true'}">
            <div class="alert alert-success">Xác thực tài khoản qua OTP thành công! Vui lòng đăng nhập.</div>
        </c:if>

        <c:if test="${param.logout == 'true'}">
            <div class="alert alert-success">Bạn đã đăng xuất khỏi hệ thống.</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label for="login">Tên đăng nhập hoặc Email</label>
                <input type="text" id="login" name="login" class="form-control" required autofocus placeholder="username hoặc email@...">
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" class="form-control" required placeholder="Nhập mật khẩu">
            </div>

            <div style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary" style="width: 100%;">Đăng nhập</button>
            </div>
        </form>

        <div style="margin-top: 20px; text-align: center; font-size: 0.95rem;">
            Chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký ngay</a>
        </div>
    </div>
</div>

</body>
</html>
