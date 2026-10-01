<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lịch Sử Đặt Hàng - Đề 05</title>
</head>
<body>
    <div style="margin-bottom: 20px;">
        <h2 style="font-size: 1.5rem; color: #1e293b; margin-bottom: 6px;">Lịch sử đặt hàng của bạn</h2>
        <p style="color: #64748b; font-size: 0.95rem;">Theo dõi trạng thái và tiến độ xử lý của tất cả các đơn hàng mua sắm.</p>
    </div>

    <c:if test="${param.msg == 'cancelled'}">
        <div class="alert alert-success" style="margin-bottom: 20px;">
            Đã hủy đơn hàng thành công! Số lượng sản phẩm đã được hoàn trả lại kho hàng.
        </div>
    </c:if>

    <div style="background-color: #f1f5f9; border: 1px solid #cbd5e1; border-radius: 8px; padding: 12px 16px; margin-bottom: 20px; font-size: 0.88rem; color: #334155;">
        <strong>Lưu ý quan sát trạng thái:</strong> Có thể vào MySQL cập nhật giá trị cột <code>status</code> của bảng <code>Cart</code> (1: Đơn hàng mới, 2: Đã xác nhận, 3: Chuẩn bị hàng, 4: Vận chuyển, 5: Giao hàng, 6: Đã giao, 7: Đơn hàng hủy, 8: Đơn hàng hoàn) rồi tải lại trang để quan sát trạng thái đơn hàng thay đổi tương ứng.
    </div>

    <div class="status-filter-bar">
        <a href="${pageContext.request.contextPath}/order-history" class="filter-tab ${empty currentStatus ? 'active' : ''}">Tất cả</a>
        <a href="${pageContext.request.contextPath}/order-history?status=1" class="filter-tab ${currentStatus == 1 ? 'active' : ''}">Đơn hàng mới</a>
        <a href="${pageContext.request.contextPath}/order-history?status=2" class="filter-tab ${currentStatus == 2 ? 'active' : ''}">Đã xác nhận</a>
        <a href="${pageContext.request.contextPath}/order-history?status=3" class="filter-tab ${currentStatus == 3 ? 'active' : ''}">Chuẩn bị hàng</a>
        <a href="${pageContext.request.contextPath}/order-history?status=4" class="filter-tab ${currentStatus == 4 ? 'active' : ''}">Vận chuyển</a>
        <a href="${pageContext.request.contextPath}/order-history?status=5" class="filter-tab ${currentStatus == 5 ? 'active' : ''}">Giao hàng</a>
        <a href="${pageContext.request.contextPath}/order-history?status=6" class="filter-tab ${currentStatus == 6 ? 'active' : ''}">Đã giao</a>
        <a href="${pageContext.request.contextPath}/order-history?status=7" class="filter-tab ${currentStatus == 7 ? 'active' : ''}">Đơn hàng hủy</a>
        <a href="${pageContext.request.contextPath}/order-history?status=8" class="filter-tab ${currentStatus == 8 ? 'active' : ''}">Đơn hàng hoàn</a>
    </div>

    <c:choose>
        <c:when test="${empty orders}">
            <div class="card" style="text-align: center; padding: 48px 24px;">
                <div style="font-size: 3rem; margin-bottom: 12px;">📦</div>
                <h3 style="color: #334155; margin-bottom: 6px;">Không tìm thấy đơn hàng nào</h3>
                <p style="color: #64748b; margin-bottom: 20px;">Hiện tại chưa có đơn hàng nào phù hợp với bộ lọc trạng thái được chọn.</p>
                <a href="${pageContext.request.contextPath}/seller-products" class="btn btn-primary" style="padding: 9px 20px;">Mua sắm ngay</a>
            </div>
        </c:when>
        <c:otherwise>
            <div style="display: flex; flex-direction: column; gap: 20px;">
                <c:forEach var="order" items="${orders}">
                    <div class="card" style="padding: 20px;">
                        <div style="display: flex; justify-content: space-between; align-items: center; padding-bottom: 14px; border-bottom: 1px solid #e2e8f0; margin-bottom: 16px;">
                            <div>
                                <span style="font-weight: 700; color: #1e293b; font-size: 1.05rem;">Đơn hàng #${order.cartId}</span>
                                <span style="color: #64748b; font-size: 0.85rem; margin-left: 12px;">Ngày đặt: ${order.formattedBuyDate}</span>
                            </div>
                            <div>
                                <span class="badge ${order.statusBadgeClass}" style="font-size: 0.85rem; padding: 6px 14px;">
                                    ${order.statusName}
                                </span>
                            </div>
                        </div>

                        <div style="display: flex; flex-direction: column; gap: 12px; margin-bottom: 16px;">
                            <c:forEach var="item" items="${order.items}">
                                <div style="display: flex; justify-content: space-between; align-items: center; padding: 8px 0; border-bottom: 1px dashed #f1f5f9;">
                                    <div style="display: flex; align-items: center; gap: 14px;">
                                        <img src="${item.product.images}" alt="${item.product.productName}" style="width: 52px; height: 52px; object-fit: cover; border-radius: 6px; border: 1px solid #e2e8f0;">
                                        <div>
                                            <div style="font-weight: 600; color: #1e293b; font-size: 0.95rem;">${item.product.productName}</div>
                                            <div style="font-size: 0.82rem; color: #64748b; margin-top: 2px;">
                                                Số lượng: x${item.quantity} | Cửa hàng: ${item.product.seller.sellername}
                                            </div>
                                        </div>
                                    </div>
                                    <div style="font-weight: 600; color: #334155;">
                                        ${item.formattedTotalPrice} đ
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                        <div style="display: flex; justify-content: space-between; align-items: flex-end; padding-top: 14px; border-top: 1px solid #e2e8f0;">
                            <div style="font-size: 0.85rem; color: #64748b; line-height: 1.5;">
                                <div><strong>Người nhận:</strong> ${order.user.fullname} (${order.phone})</div>
                                <div><strong>Địa chỉ:</strong> ${order.shippingAddress}</div>
                                <div><strong>Hình thức:</strong> ${order.paymentMethod} (Thanh toán khi nhận hàng)</div>
                                <c:if test="${not empty order.note}">
                                    <div><strong>Ghi chú:</strong> ${order.note}</div>
                                </c:if>
                            </div>

                            <div style="text-align: right;">
                                <div style="font-size: 0.9rem; color: #64748b; margin-bottom: 4px;">Tổng tiền đơn hàng:</div>
                                <div style="font-size: 1.3rem; font-weight: 700; color: #2563eb; margin-bottom: 10px;">${order.formattedTotalAmount} đ</div>
                                
                                <c:if test="${order.status == 1}">
                                    <form action="${pageContext.request.contextPath}/order-cancel" method="post" style="display: inline;" onsubmit="return confirm('Bạn có chắc chắn muốn hủy đơn hàng #${order.cartId}?');">
                                        <input type="hidden" name="cartId" value="${order.cartId}">
                                        <button type="submit" class="btn btn-secondary" style="padding: 6px 14px; color: #ef4444; font-size: 0.85rem;">Hủy đơn hàng</button>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
