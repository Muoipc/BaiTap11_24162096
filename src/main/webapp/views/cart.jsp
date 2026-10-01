<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ Hàng Mua Sắm - Đề 05</title>
</head>
<body>
    <div style="margin-bottom: 24px;">
        <h2 style="font-size: 1.5rem; color: #1e293b; margin-bottom: 6px;">Giỏ hàng của bạn</h2>
        <p style="color: #64748b; font-size: 0.95rem;">Quản lý danh sách sản phẩm đã chọn và kiểm tra tổng tiền trước khi thanh toán.</p>
    </div>

    <c:choose>
        <c:when test="${empty cart or empty cart.items or cart.totalQuantity == 0}">
            <div class="card" style="text-align: center; padding: 48px 24px;">
                <div style="font-size: 3.5rem; margin-bottom: 16px;">🛒</div>
                <h3 style="color: #334155; margin-bottom: 8px;">Giỏ hàng của bạn đang trống</h3>
                <p style="color: #64748b; margin-bottom: 24px;">Hãy khám phá các sản phẩm tuyệt vời của các cửa hàng và thêm vào giỏ nhé!</p>
                <div>
                    <a href="${pageContext.request.contextPath}/seller-products" class="btn btn-primary" style="padding: 10px 24px;">Khám phá sản phẩm ngay</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 24px;">
                <div class="card">
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #e2e8f0;">
                        <h3 style="font-size: 1.15rem; color: #1e293b; margin: 0;">Sản phẩm trong giỏ (${cart.totalQuantity} món)</h3>
                        <a href="${pageContext.request.contextPath}/cart/clear" class="btn btn-secondary" style="padding: 6px 12px; font-size: 0.85rem; color: #ef4444;" onclick="return confirm('Bạn có chắc muốn xóa toàn bộ giỏ hàng?');">Xóa tất cả</a>
                    </div>

                    <table class="table" style="margin-bottom: 0;">
                        <thead>
                            <tr>
                                <th>Sản phẩm</th>
                                <th style="text-align: right;">Đơn giá</th>
                                <th style="text-align: center; width: 140px;">Số lượng</th>
                                <th style="text-align: right;">Thành tiền</th>
                                <th style="text-align: center; width: 60px;">Xóa</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cart.items}">
                                <tr>
                                    <td>
                                        <div style="display: flex; align-items: center; gap: 12px;">
                                            <img src="${item.product.images}" alt="${item.product.productName}" style="width: 56px; height: 56px; object-fit: cover; border-radius: 6px; border: 1px solid #e2e8f0;">
                                            <div>
                                                <a href="${pageContext.request.contextPath}/product-detail?id=${item.product.productId}" style="font-weight: 600; color: #1e293b; text-decoration: none;">
                                                    ${item.product.productName}
                                                </a>
                                                <div style="font-size: 0.8rem; color: #64748b; margin-top: 2px;">
                                                    Mã SP: ${item.product.productCode} | Cửa hàng: ${item.product.seller.sellername}
                                                </div>
                                                <div style="font-size: 0.8rem; color: #0284c7;">
                                                    Tồn kho: ${item.product.amount}
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                    <td style="text-align: right; font-weight: 500;">
                                        ${item.formattedUnitPrice} đ
                                    </td>
                                    <td style="text-align: center;">
                                        <form action="${pageContext.request.contextPath}/cart/update" method="post" style="display: inline-flex; align-items: center; gap: 4px;">
                                            <input type="hidden" name="productId" value="${item.product.productId}">
                                            <button type="submit" name="quantity" value="${item.quantity - 1}" class="btn btn-secondary" style="padding: 2px 8px; font-weight: bold;">-</button>
                                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.product.amount}" style="width: 45px; text-align: center; padding: 3px; border: 1px solid #cbd5e1; border-radius: 4px;" onchange="this.form.submit();">
                                            <button type="submit" name="quantity" value="${item.quantity + 1}" class="btn btn-secondary" style="padding: 2px 8px; font-weight: bold;" ${item.quantity >= item.product.amount ? 'disabled' : ''}>+</button>
                                        </form>
                                    </td>
                                    <td style="text-align: right; font-weight: 600; color: #2563eb;">
                                        ${item.formattedTotalPrice} đ
                                    </td>
                                    <td style="text-align: center;">
                                        <a href="${pageContext.request.contextPath}/cart/delete?productId=${item.product.productId}" style="color: #ef4444; font-size: 1.1rem; text-decoration: none;" onclick="return confirm('Xóa sản phẩm này khỏi giỏ hàng?');">✕</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div>
                    <div class="card" style="position: sticky; top: 20px;">
                        <h3 style="font-size: 1.15rem; color: #1e293b; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #e2e8f0;">Tóm tắt đơn hàng</h3>
                        
                        <div style="display: flex; justify-content: space-between; margin-bottom: 12px; color: #475569; font-size: 0.95rem;">
                            <span>Tổng số lượng:</span>
                            <strong>${cart.totalQuantity} sản phẩm</strong>
                        </div>

                        <div style="display: flex; justify-content: space-between; margin-bottom: 12px; color: #475569; font-size: 0.95rem;">
                            <span>Tạm tính:</span>
                            <span>${cart.formattedTotalAmount} đ</span>
                        </div>

                        <div style="display: flex; justify-content: space-between; margin-bottom: 16px; color: #475569; font-size: 0.95rem;">
                            <span>Phí vận chuyển:</span>
                            <span style="color: #16a34a; font-weight: 500;">Miễn phí</span>
                        </div>

                        <div style="display: flex; justify-content: space-between; margin-bottom: 24px; padding-top: 14px; border-top: 2px solid #e2e8f0; font-size: 1.1rem;">
                            <span style="font-weight: 600; color: #0f172a;">Tổng thanh toán:</span>
                            <span style="font-weight: 700; color: #2563eb; font-size: 1.3rem;">${cart.formattedTotalAmount} đ</span>
                        </div>

                        <div style="display: flex; flex-direction: column; gap: 10px;">
                            <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary" style="padding: 12px; text-align: center; font-size: 1rem;">Tiến hành đặt hàng (COD)</a>
                            <a href="${pageContext.request.contextPath}/seller-products" class="btn btn-secondary" style="padding: 10px; text-align: center;">Tiếp tục mua hàng</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
