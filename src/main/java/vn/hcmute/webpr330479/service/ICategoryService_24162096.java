package vn.hcmute.webpr330479.service;

import vn.hcmute.webpr330479.entity.Category_24162096;
import java.util.List;

public interface ICategoryService_24162096 {
    Category_24162096 findById(int id);
    List<Category_24162096> findAll();
    List<Category_24162096> findAll(int page, int pageSize);
    int count();
    void insert(Category_24162096 category);
    void update(Category_24162096 category);
    void delete(int id);
}
