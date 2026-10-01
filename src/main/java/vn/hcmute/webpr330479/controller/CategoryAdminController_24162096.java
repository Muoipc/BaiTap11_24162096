package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.Category_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.ICategoryService_24162096;
import vn.hcmute.webpr330479.service.impl.CategoryServiceImpl_24162096;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/categories",
        "/admin/category/add",
        "/admin/category-add",
        "/admin/category/edit",
        "/admin/category-edit",
        "/admin/category/delete",
        "/admin/category-delete"
})
public class CategoryAdminController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICategoryService_24162096 categoryService = new CategoryServiceImpl_24162096();

    private boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User_24162096 account = session != null ? (User_24162096) session.getAttribute("account") : null;
        if (account == null || account.getRole() == null || !"ADMIN".equalsIgnoreCase(account.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkAdmin(request, response)) {
            return;
        }

        String path = request.getServletPath();

        if ("/admin/category/add".equals(path) || "/admin/category-add".equals(path)) {
            request.setAttribute("mode", "add");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/categories/form.jsp").include(request, response);
            return;
        }

        if ("/admin/category/edit".equals(path) || "/admin/category-edit".equals(path)) {
            String idRaw = request.getParameter("id");
            if (idRaw != null && !idRaw.trim().isEmpty()) {
                Category_24162096 category = categoryService.findById(Integer.parseInt(idRaw.trim()));
                request.setAttribute("category", category);
            }
            request.setAttribute("mode", "edit");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/categories/form.jsp").include(request, response);
            return;
        }

        if ("/admin/category/delete".equals(path) || "/admin/category-delete".equals(path)) {
            String idRaw = request.getParameter("id");
            if (idRaw != null && !idRaw.trim().isEmpty()) {
                categoryService.delete(Integer.parseInt(idRaw.trim()));
            }
            response.sendRedirect(request.getContextPath() + "/admin/categories");
            return;
        }

        int page = 1;
        int pageSize = 5;
        String pageRaw = request.getParameter("page");
        if (pageRaw != null && !pageRaw.trim().isEmpty()) {
            try {
                page = Math.max(1, Integer.parseInt(pageRaw.trim()));
            } catch (NumberFormatException ignored) {
            }
        }

        int totalItems = categoryService.count();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        if (totalPages == 0) totalPages = 1;
        if (page > totalPages) page = totalPages;

        List<Category_24162096> categories = categoryService.findAll(page, pageSize);

        request.setAttribute("categories", categories);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalItems", totalItems);

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/categories/list.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkAdmin(request, response)) {
            return;
        }
        request.setCharacterEncoding("UTF-8");

        String categoryIdRaw = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");
        String images = request.getParameter("images");
        String statusRaw = request.getParameter("status");

        int status = 1;
        try {
            if (statusRaw != null) {
                status = Integer.parseInt(statusRaw.trim());
            }
        } catch (NumberFormatException ignored) {
        }

        if (categoryIdRaw == null || categoryIdRaw.trim().isEmpty()) {
            Category_24162096 category = new Category_24162096();
            category.setCategoryName(categoryName != null ? categoryName.trim() : "");
            category.setImages(images != null && !images.trim().isEmpty() ? images.trim() :
                    "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?w=500");
            category.setStatus(status);
            categoryService.insert(category);
        } else {
            int id = Integer.parseInt(categoryIdRaw.trim());
            Category_24162096 category = categoryService.findById(id);
            if (category != null) {
                category.setCategoryName(categoryName != null ? categoryName.trim() : "");
                category.setImages(images != null && !images.trim().isEmpty() ? images.trim() : category.getImages());
                category.setStatus(status);
                categoryService.update(category);
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }
}
