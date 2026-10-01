package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;
import vn.hcmute.webpr330479.service.impl.ProductServiceImpl_24162096;

import java.io.IOException;

@WebServlet("/product-detail")
public class ProductDetailController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProductService_24162096 productService = new ProductServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRaw = request.getParameter("id");
        if (idRaw == null || idRaw.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/seller-products");
            return;
        }

        try {
            int id = Integer.parseInt(idRaw.trim());
            Product_24162096 product = productService.findById(id);
            if (product == null) {
                response.sendRedirect(request.getContextPath() + "/seller-products");
                return;
            }
            request.setAttribute("product", product);
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/product-detail.jsp").include(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/seller-products");
        }
    }
}
