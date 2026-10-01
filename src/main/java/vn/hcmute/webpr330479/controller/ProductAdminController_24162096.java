package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.Category_24162096;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.entity.Seller_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.ICategoryService_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;
import vn.hcmute.webpr330479.service.ISellerService_24162096;
import vn.hcmute.webpr330479.service.impl.CategoryServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.ProductServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.SellerServiceImpl_24162096;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {
        "/admin/products",
        "/admin/product/add",
        "/admin/product-add",
        "/admin/product/edit",
        "/admin/product-edit",
        "/admin/product/delete",
        "/admin/product-delete"
})
public class ProductAdminController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProductService_24162096 productService = new ProductServiceImpl_24162096();
    private final ICategoryService_24162096 categoryService = new CategoryServiceImpl_24162096();
    private final ISellerService_24162096 sellerService = new SellerServiceImpl_24162096();

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

        if ("/admin/product/add".equals(path) || "/admin/product-add".equals(path)) {
            request.setAttribute("categories", categoryService.findAll());
            request.setAttribute("sellers", sellerService.findAll());
            request.setAttribute("mode", "add");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/products/form.jsp").include(request, response);
            return;
        }

        if ("/admin/product/edit".equals(path) || "/admin/product-edit".equals(path)) {
            String idRaw = request.getParameter("id");
            if (idRaw != null && !idRaw.trim().isEmpty()) {
                Product_24162096 product = productService.findById(Integer.parseInt(idRaw.trim()));
                request.setAttribute("product", product);
            }
            request.setAttribute("categories", categoryService.findAll());
            request.setAttribute("sellers", sellerService.findAll());
            request.setAttribute("mode", "edit");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/products/form.jsp").include(request, response);
            return;
        }

        if ("/admin/product/delete".equals(path) || "/admin/product-delete".equals(path)) {
            String idRaw = request.getParameter("id");
            if (idRaw != null && !idRaw.trim().isEmpty()) {
                productService.delete(Integer.parseInt(idRaw.trim()));
            }
            response.sendRedirect(request.getContextPath() + "/admin/products");
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

        int totalItems = productService.count();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        if (totalPages == 0) totalPages = 1;
        if (page > totalPages) page = totalPages;

        List<Product_24162096> products = productService.findAll(page, pageSize);

        request.setAttribute("products", products);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalItems", totalItems);

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/products/list.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!checkAdmin(request, response)) {
            return;
        }
        request.setCharacterEncoding("UTF-8");

        String productIdRaw = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String productCodeRaw = request.getParameter("productCode");
        String categoryIdRaw = request.getParameter("categoryId");
        String sellerIdRaw = request.getParameter("sellerId");
        String priceRaw = request.getParameter("price");
        String amountRaw = request.getParameter("amount");
        String stockRaw = request.getParameter("stock");
        String description = request.getParameter("description");
        String images = request.getParameter("images");
        String statusRaw = request.getParameter("status");

        Long productCode = 100000L;
        try {
            if (productCodeRaw != null) productCode = Long.parseLong(productCodeRaw.trim());
        } catch (NumberFormatException ignored) {}

        Double price = 0.0;
        try {
            if (priceRaw != null) price = Double.parseDouble(priceRaw.trim());
        } catch (NumberFormatException ignored) {}

        Integer amount = 10;
        try {
            if (amountRaw != null) amount = Integer.parseInt(amountRaw.trim());
        } catch (NumberFormatException ignored) {}

        Integer stock = amount;
        try {
            if (stockRaw != null) stock = Integer.parseInt(stockRaw.trim());
        } catch (NumberFormatException ignored) {}

        Integer status = 1;
        try {
            if (statusRaw != null) status = Integer.parseInt(statusRaw.trim());
        } catch (NumberFormatException ignored) {}

        Category_24162096 category = null;
        if (categoryIdRaw != null && !categoryIdRaw.trim().isEmpty()) {
            category = categoryService.findById(Integer.parseInt(categoryIdRaw.trim()));
        }

        Seller_24162096 seller = null;
        if (sellerIdRaw != null && !sellerIdRaw.trim().isEmpty()) {
            seller = sellerService.findById(Integer.parseInt(sellerIdRaw.trim()));
        }

        if (productIdRaw == null || productIdRaw.trim().isEmpty()) {
            Product_24162096 p = new Product_24162096();
            p.setProductName(productName != null ? productName.trim() : "");
            p.setProductCode(productCode);
            p.setCategory(category);
            p.setSeller(seller);
            p.setPrice(price);
            p.setAmount(amount);
            p.setStock(stock);
            p.setDescription(description != null ? description.trim() : "");
            p.setImages(images != null && !images.trim().isEmpty() ? images.trim() :
                    "https://images.unsplash.com/photo-1510557880182-3d4d3cba35a5?w=500");
            p.setWishlist(0);
            p.setStatus(status);
            p.setCreateDate(LocalDate.now());
            productService.insert(p);
        } else {
            int id = Integer.parseInt(productIdRaw.trim());
            Product_24162096 p = productService.findById(id);
            if (p != null) {
                p.setProductName(productName != null ? productName.trim() : "");
                p.setProductCode(productCode);
                p.setCategory(category);
                p.setSeller(seller);
                p.setPrice(price);
                p.setAmount(amount);
                p.setStock(stock);
                p.setDescription(description != null ? description.trim() : "");
                p.setImages(images != null && !images.trim().isEmpty() ? images.trim() : p.getImages());
                p.setStatus(status);
                productService.update(p);
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/products");
    }
}
