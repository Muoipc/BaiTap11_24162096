<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thanh Toán Đơn Hàng (COD) - Đề 05</title>
</head>
<body>
    <div style="margin-bottom: 24px;">
        <h2 style="font-size: 1.5rem; color: #1e293b; margin-bottom: 6px;">Thanh toán đơn hàng</h2>
        <p style="color: #64748b; font-size: 0.95rem;">Phương thức thanh toán khi nhận hàng (Cash on Delivery - COD)</p>
    </div>

    <c:if test="${not empty error}">
        <div class="alert alert-error" style="margin-bottom: 20px;">
            ${error}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/checkout" method="post">
        <div style="display: grid; grid-template-columns: 1.3fr 1fr; gap: 24px;">
            <div>
                <div class="card" style="margin-bottom: 24px;">
                    <h3 style="font-size: 1.15rem; color: #1e293b; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #e2e8f0;">1. Thông tin giao hàng</h3>

                    <div class="form-group">
                        <label class="form-label">Người nhận hàng:</label>
                        <input type="text" class="form-control" name="fullName" value="${account.fullname}" readonly style="background-color: #f8fafc;">
                    </div>

                    <div class="form-group">
                        <label class="form-label">Email tài khoản:</label>
                        <input type="text" class="form-control" name="email" value="${account.email}" readonly style="background-color: #f8fafc;">
                    </div>

                    <div class="form-group">
                        <label class="form-label">Số điện thoại nhận hàng (*):</label>
                        <input type="tel" class="form-control" name="phone" value="${account.phone}" required placeholder="Ví dụ: 0901234567">
                    </div>

                    <div class="form-group">
                        <label class="form-label">Địa chỉ giao hàng chi tiết (*):</label>
                        <textarea class="form-control" name="shippingAddress" rows="3" required placeholder="Số nhà, tên đường, phường/xã, quận/huyện, tỉnh/thành phố..."></textarea>
                    </div>

                    <div class="form-group" style="margin-bottom: 0;">
                        <label class="form-label">Ghi chú cho shipper (tùy chọn):</label>
                        <input type="text" class="form-control" name="note" placeholder="Ví dụ: Giao giờ hành chính, gọi trước khi đến...">
                    </div>
                </div>

                <div class="card">
                    <h3 style="font-size: 1.15rem; color: #1e293b; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #e2e8f0;">2. Phương thức thanh toán</h3>
                    
                    <div style="padding: 16px; border: 2px solid #2563eb; border-radius: 8px; background-color: #eff6ff; display: flex; align-items: center; gap: 14px;">
                        <input type="radio" name="paymentMethod" value="COD" checked id="codRadio" style="width: 20px; height: 20px; accent-color: #2563eb;">
                        <label for="codRadio" style="margin: 0; cursor: pointer;">
                            <div style="font-weight: 600; color: #1e293b;">Thanh toán khi nhận hàng (COD)</div>
                            <div style="font-size: 0.85rem; color: #64748b;">Bạn chỉ thanh toán bằng tiền mặt khi đơn hàng được nhân viên bưu tá giao tới tận tay.</div>
                        </label>
                    </div>
                </div>
            </div>

            <div>
                <div class="card" style="position: sticky; top: 20px;">
                    <h3 style="font-size: 1.15rem; color: #1e293b; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #e2e8f0;">Đơn hàng (${cart.totalQuantity} sản phẩm)</h3>

                    <div style="max-height: 280px; overflow-y: auto; margin-bottom: 16px;">
                        <c:forEach var="item" items="${cart.items}">
                            <div style="display: flex; gap: 12px; margin-bottom: 12px; padding-bottom: 12px; border-bottom: 1px dashed #e2e8f0;">
                                <img src="${item.product.images}" alt="${item.product.productName}" style="width: 48px; height: 48px; object-fit: cover; border-radius: 4px; border: 1px solid #e2e8f0;">
                                <div style="flex: 1;">
                                    <div style="font-weight: 600; font-size: 0.9rem; color: #1e293b; line-height: 1.3;">${item.product.productName}</div>
                                    <div style="font-size: 0.8rem; color: #64748b; margin-top: 2px;">
                                        ${item.formattedUnitPrice} đ x ${item.quantity}
                                    </div>
                                </div>
                                <div style="font-weight: 600; color: #2563eb; font-size: 0.9rem;">
                                    ${item.formattedTotalPrice} đ
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div style="display: flex; justify-content: space-between; margin-bottom: 10px; color: #475569; font-size: 0.95rem;">
                        <span>Tạm tính:</span>
                        <span>${cart.formattedTotalAmount} đ</span>
                    </div>

                    <div style="display: flex; justify-content: space-between; margin-bottom: 14px; color: #475569; font-size: 0.95rem;">
                        <span>Phí vận chuyển:</span>
                        <span style="color: #16a34a; font-weight: 500;">Miễn phí (0 đ)</span>
                    </div>

                    <div style="display: flex; justify-content: space-between; margin-bottom: 24px; padding-top: 14px; border-top: 2px solid #e2e8f0; font-size: 1.15rem;">
                        <span style="font-weight: 600; color: #0f172a;">Tổng thanh toán:</span>
                        <span style="font-weight: 700; color: #2563eb; font-size: 1.35rem;">${cart.formattedTotalAmount} đ</span>
                    </div>

                    <div style="display: flex; flex-direction: column; gap: 10px;">
                        <button type="submit" class="btn btn-primary" style="padding: 14px; font-size: 1.05rem; font-weight: 600;">Xác nhận đặt hàng (COD)</button>
                        <a href="${pageContext.request.contextPath}/cart" class="btn btn-secondary" style="padding: 10px; text-align: center;">Quay lại giỏ hàng</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
