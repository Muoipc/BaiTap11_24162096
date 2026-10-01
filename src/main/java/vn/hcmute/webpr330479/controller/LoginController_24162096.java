package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.IUserService_24162096;
import vn.hcmute.webpr330479.service.impl.UserServiceImpl_24162096;

import java.io.IOException;

@WebServlet("/login")
public class LoginController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserService_24162096 userService = new UserServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/login.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null || password == null || login.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin!");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/login.jsp").include(request, response);
            return;
        }

        User_24162096 user = userService.login(login.trim(), password.trim());
        if (user == null) {
            request.setAttribute("errorMessage", "Tên đăng nhập / Email hoặc mật khẩu không đúng!");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/login.jsp").include(request, response);
            return;
        }

        if (user.getStatus() == null || user.getStatus() != 1) {
            request.getSession().setAttribute("otpEmail", user.getEmail());
            request.setAttribute("errorMessage", "Tài khoản chưa được kích hoạt OTP! Vui lòng xác thực.");
            response.sendRedirect(request.getContextPath() + "/verify-otp?email=" + user.getEmail());
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("account", user);

        String roleName = user.getRole() != null ? user.getRole().getRoleName() : "USER";
        if ("ADMIN".equalsIgnoreCase(roleName)) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if ("SELLER".equalsIgnoreCase(roleName)) {
            response.sendRedirect(request.getContextPath() + "/seller-products");
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }
}
