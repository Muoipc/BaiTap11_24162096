package vn.hcmute.webpr330479.dao;

import vn.hcmute.webpr330479.entity.CartItem_24162096;
import java.util.List;

public interface ICartItemDao_24162096 {
    void insert(CartItem_24162096 cartItem);
    void update(CartItem_24162096 cartItem);
    void delete(String cartItemId);
    List<CartItem_24162096> findByCartId(String cartId);
}
