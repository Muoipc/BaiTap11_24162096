<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đặt Hàng Thành Công - Đề 05</title>
</head>
<body>
    <div class="card" style="max-width: 650px; margin: 40px auto; text-align: center; padding: 40px 32px;">
        <div style="width: 72px; height: 72px; background-color: #dcfce7; color: #16a34a; border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 2.5rem; margin: 0 auto 20px;">
            ✓
        </div>

        <h2 style="color: #0f172a; margin-bottom: 8px;">Đặt hàng thành công!</h2>
        <p style="color: #64748b; font-size: 1rem; margin-bottom: 24px;">Cảm ơn bạn đã mua sắm. Đơn hàng của bạn đã được ghi nhận vào hệ thống.</p>

        <c:if test="${not empty order}">
            <div style="background-color: #f8fafc; border: 1px solid #e2e8f0; border-radius: 8px; padding: 20px; text-align: left; margin-bottom: 28px;">
                <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                    <span style="color: #64748b;">Mã đơn hàng:</span>
                    <strong style="color: #0f172a;">#${order.cartId}</strong>
                </div>
                <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                    <span style="color: #64748b;">Thời gian đặt:</span>
                    <span>${order.formattedBuyDate}</span>
                </div>
                <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                    <span style="color: #64748b;">Phương thức thanh toán:</span>
                    <strong style="color: #2563eb;">Thanh toán khi nhận hàng (COD)</strong>
                </div>
                <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                    <span style="color: #64748b;">Số điện thoại:</span>
                    <span>${order.phone}</span>
                </div>
                <div style="display: flex; justify-content: space-between; margin-bottom: 10px;">
                    <span style="color: #64748b;">Địa chỉ giao hàng:</span>
                    <span style="text-align: right; max-width: 60%;">${order.shippingAddress}</span>
                </div>
                <div style="display: flex; justify-content: space-between; padding-top: 10px; border-top: 1px solid #e2e8f0; font-size: 1.1rem;">
                    <span style="color: #0f172a; font-weight: 600;">Tổng tiền thu COD:</span>
                    <span style="color: #2563eb; font-weight: 700; font-size: 1.25rem;">${order.formattedTotalAmount} đ</span>
                </div>
            </div>
        </c:if>

        <div style="display: flex; gap: 12px; justify-content: center;">
            <a href="${pageContext.request.contextPath}/order-history" class="btn btn-primary" style="padding: 10px 20px;">Xem lịch sử đơn hàng</a>
            <a href="${pageContext.request.contextPath}/seller-products" class="btn btn-secondary" style="padding: 10px 20px;">Tiếp tục mua sắm</a>
        </div>
    </div>
</body>
</html>
