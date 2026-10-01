package vn.hcmute.webpr330479.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CartItem")
public class CartItem_24162096 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cartItemId", length = 50)
    private String cartItemId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unitPrice")
    private Double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product_24162096 product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    private Cart_24162096 cart;

    public CartItem_24162096() {
    }

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Product_24162096 getProduct() {
        return product;
    }

    public void setProduct(Product_24162096 product) {
        this.product = product;
    }

    public Cart_24162096 getCart() {
        return cart;
    }

    public void setCart(Cart_24162096 cart) {
        this.cart = cart;
    }

    public Double getTotalPrice() {
        if (unitPrice == null || quantity == null) {
            return 0.0;
        }
        return unitPrice * quantity;
    }

    public String getFormattedUnitPrice() {
        if (unitPrice == null) {
            return "0";
        }
        return String.format("%,.0f", unitPrice);
    }

    public String getFormattedTotalPrice() {
        return String.format("%,.0f", getTotalPrice());
    }
}
