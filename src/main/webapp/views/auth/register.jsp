<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký Tài khoản</title>
</head>
<body>

<div class="auth-wrapper">
    <div class="card">
        <h2 style="text-align: center; margin-bottom: 20px; color: #1e3a8a;">Đăng ký Tài khoản</h2>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập (Username) *</label>
                <input type="text" id="username" name="username" class="form-control" required placeholder="Nhập username">
            </div>

            <div class="form-group">
                <label for="email">Địa chỉ Email (Nhận mã OTP) *</label>
                <input type="email" id="email" name="email" class="form-control" required placeholder="email@gmail.com">
            </div>

            <div class="form-group">
                <label for="fullname">Họ và tên</label>
                <input type="text" id="fullname" name="fullname" class="form-control" placeholder="Nguyễn Văn A">
            </div>

            <div class="form-group">
                <label for="phone">Số điện thoại</label>
                <input type="text" id="phone" name="phone" class="form-control" placeholder="0901234567">
            </div>

            <div class="form-group">
                <label for="password">Mật khẩu *</label>
                <input type="password" id="password" name="password" class="form-control" required placeholder="Nhập mật khẩu">
            </div>

            <div style="margin-top: 20px;">
                <button type="submit" class="btn btn-primary" style="width: 100%;">Đăng ký &amp; Nhận OTP</button>
            </div>
        </form>

        <div style="margin-top: 20px; text-align: center; font-size: 0.95rem;">
            Đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </div>
    </div>
</div>

</body>
</html>
