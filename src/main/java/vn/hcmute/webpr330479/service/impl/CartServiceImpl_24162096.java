package vn.hcmute.webpr330479.service.impl;

import vn.hcmute.webpr330479.dao.ICartDao_24162096;
import vn.hcmute.webpr330479.dao.ICartItemDao_24162096;
import vn.hcmute.webpr330479.dao.IProductDao_24162096;
import vn.hcmute.webpr330479.dao.impl.CartDaoImpl_24162096;
import vn.hcmute.webpr330479.dao.impl.CartItemDaoImpl_24162096;
import vn.hcmute.webpr330479.dao.impl.ProductDaoImpl_24162096;
import vn.hcmute.webpr330479.entity.CartItem_24162096;
import vn.hcmute.webpr330479.entity.Cart_24162096;
import vn.hcmute.webpr330479.entity.Product_24162096;
import vn.hcmute.webpr330479.entity.User_24162096;
import vn.hcmute.webpr330479.model.CartItemModel_24162096;
import vn.hcmute.webpr330479.model.CartModel_24162096;
import vn.hcmute.webpr330479.service.ICartService_24162096;

import java.time.LocalDateTime;
import java.util.List;

public class CartServiceImpl_24162096 implements ICartService_24162096 {

    private final ICartDao_24162096 cartDao = new CartDaoImpl_24162096();
    private final ICartItemDao_24162096 cartItemDao = new CartItemDaoImpl_24162096();
    private final IProductDao_24162096 productDao = new ProductDaoImpl_24162096();

    @Override
    public Cart_24162096 checkout(User_24162096 user, CartModel_24162096 cartModel, String shippingAddress, String phone, String note) {
        if (user == null || cartModel == null || cartModel.isEmpty()) {
            return null;
        }

        String cartId = "HD" + System.currentTimeMillis();
        Cart_24162096 cart = new Cart_24162096();
        cart.setCartId(cartId);
        cart.setUser(user);
        cart.setBuyDate(LocalDateTime.now());
        cart.setStatus(1);
        cart.setPaymentMethod("COD");
        cart.setShippingAddress(shippingAddress);
        cart.setPhone(phone);
        cart.setNote(note);
        cart.setTotalAmount(cartModel.getTotalAmount());

        cartDao.insert(cart);

        for (CartItemModel_24162096 item : cartModel.getItems()) {
            CartItem_24162096 cartItem = new CartItem_24162096();
            String cartItemId = "CI_" + cartId + "_" + item.getProduct().getProductId();
            cartItem.setCartItemId(cartItemId);
            cartItem.setCart(cart);
            cartItem.setProduct(item.getProduct());
            cartItem.setQuantity(item.getQuantity());
            cartItem.setUnitPrice(item.getUnitPrice());

            cartItemDao.insert(cartItem);

            Product_24162096 product = productDao.findById(item.getProduct().getProductId());
            if (product != null) {
                int currentAmount = product.getAmount() != null ? product.getAmount() : 0;
                product.setAmount(Math.max(0, currentAmount - item.getQuantity()));
                productDao.update(product);
            }
        }

        return cart;
    }

    @Override
    public Cart_24162096 findById(String cartId) {
        Cart_24162096 cart = cartDao.findById(cartId);
        if (cart != null) {
            cart.setItems(cartItemDao.findByCartId(cartId));
        }
        return cart;
    }

    @Override
    public List<Cart_24162096> findByUserId(int userId) {
        List<Cart_24162096> carts = cartDao.findByUserId(userId);
        for (Cart_24162096 cart : carts) {
            cart.setItems(cartItemDao.findByCartId(cart.getCartId()));
        }
        return carts;
    }

    @Override
    public List<Cart_24162096> findByUserIdAndStatus(int userId, int status) {
        List<Cart_24162096> carts = cartDao.findByUserIdAndStatus(userId, status);
        for (Cart_24162096 cart : carts) {
            cart.setItems(cartItemDao.findByCartId(cart.getCartId()));
        }
        return carts;
    }

    @Override
    public boolean cancelOrder(String cartId, int userId) {
        Cart_24162096 cart = cartDao.findById(cartId);
        if (cart == null || cart.getUser() == null || cart.getUser().getUserId() != userId) {
            return false;
        }

        if (cart.getStatus() != null && cart.getStatus() == 1) {
            cart.setStatus(7);
            cartDao.update(cart);

            List<CartItem_24162096> items = cartItemDao.findByCartId(cartId);
            for (CartItem_24162096 item : items) {
                if (item.getProduct() != null) {
                    Product_24162096 product = productDao.findById(item.getProduct().getProductId());
                    if (product != null) {
                        int amount = product.getAmount() != null ? product.getAmount() : 0;
                        product.setAmount(amount + item.getQuantity());
                        productDao.update(product);
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void updateOrderStatus(String cartId, int status) {
        Cart_24162096 cart = cartDao.findById(cartId);
        if (cart != null) {
            cart.setStatus(status);
            cartDao.update(cart);
        }
    }

    @Override
    public List<CartItem_24162096> getItemsByCartId(String cartId) {
        return cartItemDao.findByCartId(cartId);
    }
}
