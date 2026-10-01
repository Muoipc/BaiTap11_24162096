package vn.hcmute.webpr330479.service;

import vn.hcmute.webpr330479.entity.User_24162096;
import java.util.List;

public interface IUserService_24162096 {
    User_24162096 findById(int id);
    User_24162096 findByUsername(String username);
    User_24162096 findByEmail(String email);
    User_24162096 login(String login, String password);
    boolean register(User_24162096 user);
    boolean verifyOtp(String email, String otp);
    void update(User_24162096 user);
    boolean checkExistUsername(String username);
    boolean checkExistEmail(String email);
    List<User_24162096> findAll();
}
