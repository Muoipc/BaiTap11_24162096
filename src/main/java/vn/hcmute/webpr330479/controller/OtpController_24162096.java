package vn.hcmute.webpr330479.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.webpr330479.service.IUserService_24162096;
import vn.hcmute.webpr330479.service.impl.UserServiceImpl_24162096;

import java.io.IOException;

@WebServlet("/verify-otp")
public class OtpController_24162096 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IUserService_24162096 userService = new UserServiceImpl_24162096();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("otpEmail");
        if (email == null) {
            email = request.getParameter("email");
        }
        request.setAttribute("email", email);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/verify-otp.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String otp = request.getParameter("otp");

        if (email == null || otp == null || otp.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập mã OTP!");
            request.setAttribute("email", email);
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/verify-otp.jsp").include(request, response);
            return;
        }

        boolean verified = userService.verifyOtp(email.trim(), otp.trim());
        if (verified) {
            request.getSession().removeAttribute("otpEmail");
            response.sendRedirect(request.getContextPath() + "/login?verified=true");
        } else {
            request.setAttribute("errorMessage", "Mã OTP không hợp lệ hoặc đã hết hạn!");
            request.setAttribute("email", email);
            response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/auth/verify-otp.jsp").include(request, response);
        }
    }
}
