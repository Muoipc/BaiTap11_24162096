package vn.hcmute.webpr330479.model;

import vn.hcmute.webpr330479.entity.Product_24162096;
import java.io.Serializable;

public class CartItemModel_24162096 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Product_24162096 product;
    private int quantity;

    public CartItemModel_24162096() {
    }

    public CartItemModel_24162096(Product_24162096 product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product_24162096 getProduct() {
        return product;
    }

    public void setProduct(Product_24162096 product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        if (product != null && product.getPrice() != null) {
            return product.getPrice();
        }
        return 0.0;
    }

    public double getTotalPrice() {
        return getUnitPrice() * quantity;
    }

    public String getFormattedUnitPrice() {
        return String.format("%,.0f", getUnitPrice());
    }

    public String getFormattedTotalPrice() {
        return String.format("%,.0f", getTotalPrice());
    }
}
