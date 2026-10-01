package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.Cart_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.model.CartModel_24162096;
import vn.hcmute.webpr330479.service.ICartService_24162096;
import vn.hcmute.webpr330479.service.impl.CartServiceImpl_24162096;

import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout", "/order-success"})
public class CheckoutController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICartService_24162096 cartService = new CartServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession();

        if ("/order-success".equals(path)) {
            String orderId = request.getParameter("id");
            if (orderId != null && !orderId.trim().isEmpty()) {
                Cart_24162096 order = cartService.findById(orderId);
                request.setAttribute("order", order);
            }
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/views/order-success.jsp").include(request, response);
            return;
        }

        User_24162096 account = (User_24162096) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login?redirect=/checkout");
            return;
        }

        CartModel_24162096 cart = (CartModel_24162096) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        request.setAttribute("account", account);
        request.setAttribute("cart", cart);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/checkout.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        User_24162096 account = (User_24162096) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login?redirect=/checkout");
            return;
        }

        CartModel_24162096 cart = (CartModel_24162096) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        String shippingAddress = request.getParameter("shippingAddress");
        String phone = request.getParameter("phone");
        String note = request.getParameter("note");

        if (shippingAddress == null || shippingAddress.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ địa chỉ giao hàng và số điện thoại liên hệ!");
            request.setAttribute("account", account);
            request.setAttribute("cart", cart);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/views/checkout.jsp").include(request, response);
            return;
        }

        Cart_24162096 order = cartService.checkout(account, cart, shippingAddress, phone, note);
        if (order != null) {
            session.removeAttribute("cart");
            response.sendRedirect(request.getContextPath() + "/order-success?id=" + order.getCartId());
        } else {
            request.setAttribute("error", "Không thể xử lý đơn hàng. Vui lòng kiểm tra lại giỏ hàng!");
            request.setAttribute("account", account);
            request.setAttribute("cart", cart);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/views/checkout.jsp").include(request, response);
        }
    }
}
