package vn.hcmute.webpr330479.service.impl;

import vn.hcmute.webpr330479.dao.IUserDao_24162096;
import vn.hcmute.webpr330479.dao.impl.UserDaoImpl_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.service.IUserService_24162096;
import vn.hcmute.webpr330479.util.EmailUtil_24162096;

import java.util.List;

public class UserServiceImpl_24162096 implements IUserService_24162096 {
    private final IUserDao_24162096 userDao = new UserDaoImpl_24162096();

    @Override
    public User_24162096 findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User_24162096 findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User_24162096 findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User_24162096 login(String login, String password) {
        return userDao.login(login, password);
    }

    @Override
    public boolean register(User_24162096 user) {
        if (userDao.checkExistUsername(user.getUsername()) || userDao.checkExistEmail(user.getEmail())) {
            return false;
        }
        String otp = EmailUtil_24162096.generateOtp();
        user.setCode(otp);
        user.setStatus(0);
        userDao.insert(user);
        EmailUtil_24162096.sendOtpEmail(user.getEmail(), otp);
        return true;
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        User_24162096 user = userDao.findByEmail(email);
        if (user != null && otp != null && otp.trim().equals(user.getCode())) {
            user.setStatus(1);
            user.setCode(null);
            userDao.update(user);
            return true;
        }
        return false;
    }

    @Override
    public void update(User_24162096 user) {
        userDao.update(user);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public List<User_24162096> findAll() {
        return userDao.findAll();
    }
}
