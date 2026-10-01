package vn.hcmute.webpr330479.service.impl;

import vn.hcmute.webpr330479.dao.ICategoryDao_24162096;
import vn.hcmute.webpr330479.dao.impl.CategoryDaoImpl_24162096;
import vn.hcmute.webpr330479.entity.Category_24162096;
import vn.hcmute.webpr330479.service.ICategoryService_24162096;

import java.util.List;

public class CategoryServiceImpl_24162096 implements ICategoryService_24162096 {
    private final ICategoryDao_24162096 categoryDao = new CategoryDaoImpl_24162096();

    @Override
    public Category_24162096 findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category_24162096> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<Category_24162096> findAll(int page, int pageSize) {
        return categoryDao.findAll(page, pageSize);
    }

    @Override
    public int count() {
        return categoryDao.count();
    }

    @Override
    public void insert(Category_24162096 category) {
        categoryDao.insert(category);
    }

    @Override
    public void update(Category_24162096 category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }
}
