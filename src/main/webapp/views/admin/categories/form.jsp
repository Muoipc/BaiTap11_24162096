<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${mode == 'add' ? 'Thêm mới' : 'Cập nhật'} Category</title>
</head>
<body>

<div class="page-title">
    <span>${mode == 'add' ? 'Thêm mới' : 'Cập nhật'} Category</span>
    <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary btn-sm">&laquo; Quay lại</a>
</div>

<div class="card" style="max-width: 600px;">
    <form action="${pageContext.request.contextPath}/admin/categories" method="post">
        <input type="hidden" name="categoryId" value="${category != null ? category.categoryId : ''}">

        <div class="form-group">
            <label for="categoryName">Tên Danh mục *</label>
            <input type="text" id="categoryName" name="categoryName" class="form-control" required value="${category != null ? category.categoryName : ''}" placeholder="Ví dụ: Thiết bị Thông minh">
        </div>

        <div class="form-group">
            <label for="images">Đường dẫn Hình ảnh (URL)</label>
            <input type="text" id="images" name="images" class="form-control" value="${category != null ? category.images : ''}" placeholder="https://images.unsplash.com/...">
        </div>

        <div class="form-group">
            <label for="status">Trạng thái hoạt động</label>
            <select id="status" name="status" class="form-control">
                <option value="1" ${category == null || category.status == 1 ? 'selected' : ''}>Hoạt động</option>
                <option value="0" ${category != null && category.status == 0 ? 'selected' : ''}>Đã khóa</option>
            </select>
        </div>

        <div style="margin-top: 24px; display: flex; gap: 12px;">
            <button type="submit" class="btn btn-primary">${mode == 'add' ? 'Tạo Category' : 'Lưu Thay đổi'}</button>
            <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary">Hủy bỏ</a>
        </div>
    </form>
</div>

</body>
</html>
