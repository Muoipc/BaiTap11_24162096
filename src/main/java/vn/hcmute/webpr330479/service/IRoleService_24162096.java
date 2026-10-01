package vn.hcmute.webpr330479.service;

import vn.hcmute.webpr330479.entity.UserRole_24162096;
import java.util.List;

public interface IRoleService_24162096 {
    UserRole_24162096 findById(int id);
    UserRole_24162096 findByName(String name);
    List<UserRole_24162096> findAll();
}
