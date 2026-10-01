<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Xác nhận OTP</title>
</head>
<body>

<div class="auth-wrapper">
    <div class="card">
        <h2 style="text-align: center; margin-bottom: 12px; color: #1e3a8a;">Xác nhận Mã OTP</h2>
        <p style="text-align: center; font-size: 0.9rem; color: #64748b; margin-bottom: 20px;">
            Mã OTP 6 số đã được gửi đến email của bạn (hoặc kiểm tra log console server).
        </p>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/verify-otp" method="post">
            <div class="form-group">
                <label for="email">Email đăng ký</label>
                <input type="email" id="email" name="email" class="form-control" value="${email}" readonly style="background-color: #f1f5f9;">
            </div>

            <div class="form-group">
                <label for="otp">Mã OTP (6 chữ số)</label>
                <input type="text" id="otp" name="otp" class="form-control" maxlength="6" required autofocus placeholder="Nhập 6 số OTP" style="font-size: 1.25rem; letter-spacing: 4px; text-align: center;">
            </div>

            <div style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary" style="width: 100%;">Kích hoạt tài khoản</button>
            </div>
        </form>

        <div style="margin-top: 20px; text-align: center; font-size: 0.95rem;">
            <a href="${pageContext.request.contextPath}/login">Quay lại Đăng nhập</a>
        </div>
    </div>
</div>

</body>
</html>
