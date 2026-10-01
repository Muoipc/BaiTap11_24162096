package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.Cart_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.ICartService_24162096;
import vn.hcmute.webpr330479.service.impl.CartServiceImpl_24162096;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/order-history", "/order-cancel"})
public class OrderHistoryController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ICartService_24162096 cartService = new CartServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User_24162096 account = (User_24162096) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login?redirect=/order-history");
            return;
        }

        String statusParam = request.getParameter("status");
        List<Cart_24162096> orders;

        Integer filterStatus = null;
        if (statusParam != null && !statusParam.trim().isEmpty() && !"all".equalsIgnoreCase(statusParam)) {
            try {
                filterStatus = Integer.parseInt(statusParam);
                orders = cartService.findByUserIdAndStatus(account.getUserId(), filterStatus);
            } catch (Exception e) {
                orders = cartService.findByUserId(account.getUserId());
            }
        } else {
            orders = cartService.findByUserId(account.getUserId());
        }

        request.setAttribute("orders", orders);
        request.setAttribute("currentStatus", filterStatus);
        request.setAttribute("msg", request.getParameter("msg"));
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/order-history.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession();

        User_24162096 account = (User_24162096) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if ("/order-cancel".equals(path)) {
            String cartId = request.getParameter("cartId");
            if (cartId != null && !cartId.trim().isEmpty()) {
                boolean success = cartService.cancelOrder(cartId, account.getUserId());
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/order-history?msg=cancelled");
                    return;
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/order-history");
    }
}
