package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.ICategoryService_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;
import vn.hcmute.webpr330479.service.ISellerService_24162096;
import vn.hcmute.webpr330479.service.IUserService_24162096;
import vn.hcmute.webpr330479.service.impl.CategoryServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.ProductServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.SellerServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.UserServiceImpl_24162096;

import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/home", "/admin/dashboard"})
public class AdminDashboardController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICategoryService_24162096 categoryService = new CategoryServiceImpl_24162096();
    private final IProductService_24162096 productService = new ProductServiceImpl_24162096();
    private final ISellerService_24162096 sellerService = new SellerServiceImpl_24162096();
    private final IUserService_24162096 userService = new UserServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User_24162096 account = session != null ? (User_24162096) session.getAttribute("account") : null;

        if (account == null || account.getRole() == null || !"ADMIN".equalsIgnoreCase(account.getRole().getRoleName())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("totalCategories", categoryService.count());
        request.setAttribute("totalProducts", productService.count());
        request.setAttribute("totalSellers", sellerService.findAll().size());
        request.setAttribute("totalUsers", userService.findAll().size());

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/admin/dashboard.jsp").include(request, response);
    }
}
