package ua.training.model.entity;

import java.sql.Timestamp;
import java.util.List;

public class Check {
    private int id;
    private Timestamp createTime;
    private User cashier;
    private long totalPrice;
    private List<Product> products;
    private Shift shift;

    public Check(){
    }

    public Check(int id, Timestamp createTime, User cashier, long totalPrice, List<Product> products, Shift shift){
        this.id = id;
        this.createTime = createTime;
        this.cashier = cashier;
        this.totalPrice = totalPrice;
        this.products = products;
        this.shift = shift;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createTime='" + createTime +
                ", cashier=" + cashier.getEmail() +
                ", totalPrice=" + totalPrice +
                ", shift=" + shift.getId() +
                '}';
    }
}
