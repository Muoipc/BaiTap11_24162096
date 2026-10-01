<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết Sản phẩm - ${product.productName}</title>
</head>
<body>

<div class="page-title">
    <span>Chi tiết Sản phẩm</span>
    <a href="${pageContext.request.contextPath}/seller-products">&laquo; Quay lại danh sách</a>
</div>

<div class="product-detail-box">
    <div>
        <img src="${product.images}" alt="${product.productName}">
    </div>

    <div class="product-detail-info">
        <h2>Tên sản phẩm: ${product.productName}</h2>
        <div><strong>Mã sản phẩm:</strong> ${product.productCode}</div>
        <div><strong>Danh mục:</strong> ${product.category != null ? product.category.categoryName : 'Chưa phân loại'}</div>
        <div><strong>Cửa hàng (Seller):</strong> ${product.seller != null ? product.seller.sellername : 'Chính hãng'}</div>
        <div><strong>Giá:</strong> <span class="price-tag">${product.formattedPrice} đ</span></div>
        <div><strong>Amount:</strong> ${product.amount} (Tồn kho: ${product.stock})</div>
        <div style="margin-top: 12px; padding: 12px; background: #f8fafc; border-radius: 6px; border: 1px solid #e2e8f0;">
            <strong>Description:</strong>
            <p style="margin-top: 6px; color: #334155;">${product.description}</p>
        </div>

        <div style="margin-top: 20px; padding-top: 16px; border-top: 1px solid #e2e8f0;">
            <form action="${pageContext.request.contextPath}/cart/add" method="post">
                <input type="hidden" name="productId" value="${product.productId}">
                <div style="display: flex; align-items: center; gap: 14px; margin-bottom: 16px;">
                    <label style="font-weight: 600; color: #334155; margin: 0;">Số lượng mua:</label>
                    <div style="display: flex; align-items: center; gap: 4px;">
                        <input type="number" name="quantity" value="1" min="1" max="${product.amount}" style="width: 70px; text-align: center; padding: 7px; border: 1px solid #cbd5e1; border-radius: 6px; font-weight: 600;">
                        <span style="font-size: 0.85rem; color: #64748b;">(Tối đa ${product.amount} sản phẩm)</span>
                    </div>
                </div>

                <div style="display: flex; gap: 12px;">
                    <button type="submit" name="action" value="add" class="btn btn-secondary" style="padding: 10px 20px; border-color: #2563eb; color: #2563eb; font-weight: 600;">
                        🛒 Thêm vào giỏ hàng
                    </button>
                    <button type="submit" name="action" value="buy_now" class="btn btn-primary" style="padding: 10px 24px; font-weight: 600;">
                        ⚡ Mua ngay (COD)
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
