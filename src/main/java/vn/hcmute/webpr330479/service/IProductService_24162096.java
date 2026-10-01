package vn.hcmute.webpr330479.service;

import vn.hcmute.webpr330479.entity.Product_24162096;
import java.util.List;

public interface IProductService_24162096 {
    Product_24162096 findById(int id);
    List<Product_24162096> findAll();
    List<Product_24162096> findAll(int page, int pageSize);
    int count();
    List<Product_24162096> findBySellerId(int sellerId);
    void insert(Product_24162096 product);
    void update(Product_24162096 product);
    void delete(int id);
}
