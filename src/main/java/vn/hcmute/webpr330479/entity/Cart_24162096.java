package vn.hcmute.webpr330479.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart_24162096 implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cartId", length = 50)
    private String cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User_24162096 user;

    @Column(name = "buyDate")
    private LocalDateTime buyDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "shippingAddress", length = 255)
    private String shippingAddress;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "paymentMethod", length = 50)
    private String paymentMethod;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Transient
    private List<CartItem_24162096> items = new ArrayList<>();

    public Cart_24162096() {
        this.paymentMethod = "COD";
        this.totalAmount = 0.0;
        this.status = 1;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public User_24162096 getUser() {
        return user;
    }

    public void setUser(User_24162096 user) {
        this.user = user;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartItem_24162096> getItems() {
        return items;
    }

    public void setItems(List<CartItem_24162096> items) {
        this.items = items;
    }

    public String getFormattedTotalAmount() {
        if (totalAmount == null) {
            return "0";
        }
        return String.format("%,.0f", totalAmount);
    }

    public String getFormattedBuyDate() {
        if (buyDate == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return buyDate.format(formatter);
    }

    public String getStatusName() {
        if (status == null) return "Chờ xử lý";
        switch (status) {
            case 1:
                return "Đơn hàng mới";
            case 2:
                return "Đã xác nhận";
            case 3:
                return "Chuẩn bị hàng";
            case 4:
                return "Vận chuyển";
            case 5:
                return "Giao hàng";
            case 6:
                return "Đã giao";
            case 7:
                return "Đơn hàng hủy";
            case 8:
                return "Đơn hàng hoàn";
            default:
                return "Trạng thái khác";
        }
    }

    public String getStatusBadgeClass() {
        if (status == null) return "badge-secondary";
        switch (status) {
            case 1:
                return "badge-primary";
            case 2:
                return "badge-info";
            case 3:
                return "badge-warning";
            case 4:
                return "badge-purple";
            case 5:
                return "badge-orange";
            case 6:
                return "badge-success";
            case 7:
                return "badge-danger";
            case 8:
                return "badge-dark";
            default:
                return "badge-secondary";
        }
    }
}
