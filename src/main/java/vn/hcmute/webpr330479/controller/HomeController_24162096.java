package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.webpr330479.entity.Category_24162096;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.service.ICategoryService_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;
import vn.hcmute.webpr330479.service.impl.CategoryServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.ProductServiceImpl_24162096;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"", "/home", "/products"})
public class HomeController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICategoryService_24162096 categoryService = new CategoryServiceImpl_24162096();
    private final IProductService_24162096 productService = new ProductServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category_24162096> categories = categoryService.findAll();
        List<Product_24162096> products = productService.findAll();

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/home.jsp").include(request, response);
    }
}
