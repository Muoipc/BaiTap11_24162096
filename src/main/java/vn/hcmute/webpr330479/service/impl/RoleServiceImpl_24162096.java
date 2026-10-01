package vn.hcmute.webpr330479.service.impl;

import vn.hcmute.webpr330479.dao.IRoleDao_24162096;
import vn.hcmute.webpr330479.dao.impl.RoleDaoImpl_24162096;
import vn.hcmute.webpr330479.entity.UserRole_24162096;
import vn.hcmute.webpr330479.service.IRoleService_24162096;

import java.util.List;

public class RoleServiceImpl_24162096 implements IRoleService_24162096 {
    private final IRoleDao_24162096 roleDao = new RoleDaoImpl_24162096();

    @Override
    public UserRole_24162096 findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public UserRole_24162096 findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public List<UserRole_24162096> findAll() {
        return roleDao.findAll();
    }
}
