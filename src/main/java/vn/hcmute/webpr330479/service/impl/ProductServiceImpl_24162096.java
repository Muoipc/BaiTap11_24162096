package vn.hcmute.webpr330479.service.impl;

import vn.hcmute.webpr330479.dao.IProductDao_24162096;
import vn.hcmute.webpr330479.dao.impl.ProductDaoImpl_24162096;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.service.IProductService_24162096;

import java.util.List;

public class ProductServiceImpl_24162096 implements IProductService_24162096 {
    private final IProductDao_24162096 productDao = new ProductDaoImpl_24162096();

    @Override
    public Product_24162096 findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product_24162096> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product_24162096> findAll(int page, int pageSize) {
        return productDao.findAll(page, pageSize);
    }

    @Override
    public int count() {
        return productDao.count();
    }

    @Override
    public List<Product_24162096> findBySellerId(int sellerId) {
        return productDao.findBySellerId(sellerId);
    }

    @Override
    public void insert(Product_24162096 product) {
        productDao.insert(product);
    }

    @Override
    public void update(Product_24162096 product) {
        productDao.update(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
}
