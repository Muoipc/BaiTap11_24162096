package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.model.CartModel_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;
import vn.hcmute.webpr330479.service.impl.ProductServiceImpl_24162096;

import java.io.IOException;

@WebServlet(urlPatterns = {"/cart", "/cart/add", "/cart/update", "/cart/delete", "/cart/clear"})
public class CartController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProductService_24162096 productService = new ProductServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        HttpSession session = request.getSession();
        CartModel_24162096 cart = (CartModel_24162096) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartModel_24162096();
            session.setAttribute("cart", cart);
        }

        if ("/cart/delete".equals(path)) {
            String pIdStr = request.getParameter("productId");
            if (pIdStr != null) {
                try {
                    int pId = Integer.parseInt(pIdStr);
                    cart.removeItem(pId);
                } catch (Exception ignored) {
                }
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        if ("/cart/clear".equals(path)) {
            cart.clear();
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        request.setAttribute("cart", cart);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/cart.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();

        HttpSession session = request.getSession();
        CartModel_24162096 cart = (CartModel_24162096) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartModel_24162096();
            session.setAttribute("cart", cart);
        }

        if ("/cart/add".equals(path)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = 1;
                String qtyStr = request.getParameter("quantity");
                if (qtyStr != null && !qtyStr.isEmpty()) {
                    quantity = Integer.parseInt(qtyStr);
                }

                Product_24162096 product = productService.findById(productId);
                if (product != null) {
                    cart.addItem(product, quantity);
                }

                String action = request.getParameter("action");
                if ("buy_now".equalsIgnoreCase(action)) {
                    response.sendRedirect(request.getContextPath() + "/checkout");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        if ("/cart/update".equals(path)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                Product_24162096 product = productService.findById(productId);
                int maxStock = (product != null && product.getAmount() != null) ? product.getAmount() : 9999;
                cart.updateQuantity(productId, quantity, maxStock);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        if ("/cart/delete".equals(path)) {
            try {
                int productId = Integer.parseInt(request.getParameter("productId"));
                cart.removeItem(productId);
            } catch (Exception ignored) {
            }
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}
