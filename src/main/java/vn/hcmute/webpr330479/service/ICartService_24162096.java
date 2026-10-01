package vn.hcmute.webpr330479.service;

import vn.hcmute.webpr330479.entity.CartItem_24162096;
import vn.hcmute.webpr330479.entity.Cart_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.model.CartModel_24162096;

import java.util.List;

public interface ICartService_24162096 {
    Cart_24162096 checkout(User_24162096 user, CartModel_24162096 cartModel, String shippingAddress, String phone, String note);
    Cart_24162096 findById(String cartId);
    List<Cart_24162096> findByUserId(int userId);
    List<Cart_24162096> findByUserIdAndStatus(int userId, int status);
    boolean cancelOrder(String cartId, int userId);
    void updateOrderStatus(String cartId, int status);
    List<CartItem_24162096> getItemsByCartId(String cartId);
}
