package vn.hcmute.webpr330479.dao;

import vn.hcmute.webpr330479.entity.Cart_24162096;
import java.util.List;

public interface ICartDao_24162096 {
    void insert(Cart_24162096 cart);
    void update(Cart_24162096 cart);
    void delete(String cartId);
    Cart_24162096 findById(String cartId);
    List<Cart_24162096> findByUserId(int userId);
    List<Cart_24162096> findByUserIdAndStatus(int userId, int status);
    List<Cart_24162096> findAll();
}
