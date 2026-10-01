package vn.hcmute.webpr330479.service.impl;

import vn.hcmute.webpr330479.dao.ISellerDao_24162096;
import vn.hcmute.webpr330479.dao.impl.SellerDaoImpl_24162096;
import vn.hcmute.webpr330479.entity.Seller_24162096;
import vn.hcmute.webpr330479.service.ISellerService_24162096;

import java.util.List;

public class SellerServiceImpl_24162096 implements ISellerService_24162096 {
    private final ISellerDao_24162096 sellerDao = new SellerDaoImpl_24162096();

    @Override
    public Seller_24162096 findById(int id) {
        return sellerDao.findById(id);
    }

    @Override
    public List<Seller_24162096> findAll() {
        return sellerDao.findAll();
    }
}
