package vn.hcmute.webpr330479.model;

import vn.hcmute.webpr330479.entity.Product_24162096;

import java.io.Serializable;
import java.util.*;

public class CartModel_24162096 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<Integer, CartItemModel_24162096> items = new LinkedHashMap<>();

    public CartModel_24162096() {
    }

    public boolean addItem(Product_24162096 product, int quantity) {
        if (product == null || product.getProductId() == null || quantity <= 0) {
            return false;
        }

        int maxStock = product.getAmount() != null ? product.getAmount() : (product.getStock() != null ? product.getStock() : 0);
        if (maxStock <= 0) {
            return false;
        }

        int pId = product.getProductId();
        if (items.containsKey(pId)) {
            CartItemModel_24162096 item = items.get(pId);
            int newQty = item.getQuantity() + quantity;
            if (newQty > maxStock) {
                newQty = maxStock;
            }
            item.setQuantity(newQty);
        } else {
            int initialQty = Math.min(quantity, maxStock);
            items.put(pId, new CartItemModel_24162096(product, initialQty));
        }
        return true;
    }

    public boolean updateQuantity(int productId, int quantity, int maxStock) {
        if (!items.containsKey(productId)) {
            return false;
        }

        if (quantity <= 0) {
            items.remove(productId);
            return true;
        }

        if (maxStock > 0 && quantity > maxStock) {
            quantity = maxStock;
        }

        items.get(productId).setQuantity(quantity);
        return true;
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

    public List<CartItemModel_24162096> getItems() {
        return new ArrayList<>(items.values());
    }

    public int getTotalQuantity() {
        int sum = 0;
        for (CartItemModel_24162096 item : items.values()) {
            sum += item.getQuantity();
        }
        return sum;
    }

    public double getTotalAmount() {
        double sum = 0.0;
        for (CartItemModel_24162096 item : items.values()) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public String getFormattedTotalAmount() {
        return String.format("%,.0f", getTotalAmount());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
