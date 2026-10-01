package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.webpr330479.entity.UserRole_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.IRoleService_24162096;
import vn.hcmute.webpr330479.service.IUserService_24162096;
import vn.hcmute.webpr330479.service.impl.RoleServiceImpl_24162096;
import vn.hcmute.webpr330479.service.impl.UserServiceImpl_24162096;

import java.io.IOException;

@WebServlet("/register")
public class RegisterController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserService_24162096 userService = new UserServiceImpl_24162096();
    private final IRoleService_24162096 roleService = new RoleServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/register.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        if (username == null || username.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin bắt buộc!");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/register.jsp").include(request, response);
            return;
        }

        UserRole_24162096 role = roleService.findByName("USER");
        if (role == null) {
            role = roleService.findById(2);
        }

        User_24162096 user = new User_24162096();
        user.setUsername(username.trim());
        user.setEmail(email.trim().toLowerCase());
        user.setFullname(fullname != null ? fullname.trim() : username.trim());
        user.setPassword(password.trim());
        user.setPhone(phone != null ? phone.trim() : "");
        user.setImages("https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=500");
        user.setRole(role);

        boolean success = userService.register(user);
        if (success) {
            request.getSession().setAttribute("otpEmail", user.getEmail());
            response.sendRedirect(request.getContextPath() + "/verify-otp");
        } else {
            request.setAttribute("errorMessage", "Username hoặc Email đã được đăng ký!");
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/register.jsp").include(request, response);
        }
    }
}
