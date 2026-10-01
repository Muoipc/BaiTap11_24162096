<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${mode == 'add' ? 'Thêm mới' : 'Cập nhật'} Product</title>
</head>
<body>

<div class="page-title">
    <span>${mode == 'add' ? 'Thêm mới' : 'Cập nhật'} Product</span>
    <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary btn-sm">&laquo; Quay lại</a>
</div>

<div class="card" style="max-width: 760px;">
    <form action="${pageContext.request.contextPath}/admin/products" method="post">
        <input type="hidden" name="productId" value="${product != null ? product.productId : ''}">

        <div class="form-group">
            <label for="productName">Tên Sản phẩm *</label>
            <input type="text" id="productName" name="productName" class="form-control" required value="${product != null ? product.productName : ''}" placeholder="Nhập tên sản phẩm">
        </div>

        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 16px;">
            <div class="form-group">
                <label for="productCode">Mã Sản phẩm (Code) *</label>
                <input type="number" id="productCode" name="productCode" class="form-control" required value="${product != null ? product.productCode : '100001'}">
            </div>

            <div class="form-group">
                <label for="price">Đơn giá (VNĐ) *</label>
                <input type="number" step="1000" id="price" name="price" class="form-control" required value="${product != null ? product.price : '1000000'}">
            </div>
        </div>

        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 16px;">
            <div class="form-group">
                <label for="categoryId">Danh mục (Category) *</label>
                <select id="categoryId" name="categoryId" class="form-control" required>
                    <c:forEach var="c" items="${categories}">
                        <option value="${c.categoryId}" ${product != null && product.category != null && product.category.categoryId == c.categoryId ? 'selected' : ''}>
                            ${c.categoryName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="sellerId">Cửa hàng (Seller) *</label>
                <select id="sellerId" name="sellerId" class="form-control" required>
                    <c:forEach var="s" items="${sellers}">
                        <option value="${s.sellerId}" ${product != null && product.seller != null && product.seller.sellerId == s.sellerId ? 'selected' : ''}>
                            #${s.sellerId} - ${s.sellername}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 16px;">
            <div class="form-group">
                <label for="amount">Số lượng nhập (Amount)</label>
                <input type="number" id="amount" name="amount" class="form-control" value="${product != null ? product.amount : '20'}">
            </div>

            <div class="form-group">
                <label for="stock">Tồn kho (Stock)</label>
                <input type="number" id="stock" name="stock" class="form-control" value="${product != null ? product.stock : '20'}">
            </div>
        </div>

        <div class="form-group">
            <label for="images">Đường dẫn Hình ảnh (URL)</label>
            <input type="text" id="images" name="images" class="form-control" value="${product != null ? product.images : ''}" placeholder="https://images.unsplash.com/...">
        </div>

        <div class="form-group">
            <label for="description">Mô tả Sản phẩm (Description)</label>
            <textarea id="description" name="description" rows="4" class="form-control" placeholder="Mô tả thông số chi tiết sản phẩm...">${product != null ? product.description : ''}</textarea>
        </div>

        <div class="form-group">
            <label for="status">Trạng thái hoạt động</label>
            <select id="status" name="status" class="form-control">
                <option value="1" ${product == null || product.status == 1 ? 'selected' : ''}>Hoạt động</option>
                <option value="0" ${product != null && product.status == 0 ? 'selected' : ''}>Đã ẩn / Khóa</option>
            </select>
        </div>

        <div style="margin-top: 24px; display: flex; gap: 12px;">
            <button type="submit" class="btn btn-primary">${mode == 'add' ? 'Tạo Sản phẩm' : 'Lưu Thay đổi'}</button>
            <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary">Hủy bỏ</a>
        </div>
    </form>
</div>

</body>
</html>
