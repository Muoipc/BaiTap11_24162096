<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sản phẩm theo Cửa hàng (Seller)</title>
</head>
<body>

<div class="page-title">
    <span>Danh sách Sản phẩm theo Cửa hàng</span>
</div>

<c:forEach var="entry" items="${sellerMap}">
    <div class="card-seller">
        <div class="card-seller-header">
            Mã cửa hàng: #${entry.key.sellerId} - ${entry.key.sellername}
        </div>

        <div>
            <c:choose>
                <c:when test="${not empty entry.value}">
                    <c:forEach var="p" items="${entry.value}">
                        <div class="product-row-box">
                            <div>
                                <a href="${pageContext.request.contextPath}/product-detail?id=${p.productId}">
                                    <img src="${p.images}" alt="${p.productName}">
                                </a>
                            </div>

                            <div class="product-row-info">
                                <a href="${pageContext.request.contextPath}/product-detail?id=${p.productId}" class="p-title">
                                    Tên sản phẩm: ${p.productName}
                                </a>
                                <div><strong>Mã sản phẩm:</strong> ${p.productCode}</div>
                                <div><strong>Danh mục:</strong> ${p.category != null ? p.category.categoryName : 'Khác'}</div>
                                <div><strong>Giá:</strong> <span class="price-tag">${p.formattedPrice} đ</span></div>
                                <div><strong>Amount:</strong> ${p.amount}</div>
                                
                                <div style="margin-top: 10px; display: flex; gap: 8px;">
                                    <form action="${pageContext.request.contextPath}/cart/add" method="post" style="display: inline;">
                                        <input type="hidden" name="productId" value="${p.productId}">
                                        <input type="hidden" name="quantity" value="1">
                                        <button type="submit" class="btn btn-primary" style="padding: 5px 12px; font-size: 0.85rem;">
                                            🛒 Thêm vào giỏ
                                        </button>
                                    </form>
                                    <a href="${pageContext.request.contextPath}/product-detail?id=${p.productId}" class="btn btn-secondary" style="padding: 5px 12px; font-size: 0.85rem;">
                                        Chi tiết
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div style="padding: 20px; color: #64748b; font-style: italic;">
                        Cửa hàng này hiện chưa có sản phẩm nào.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</c:forEach>

</body>
</html>
