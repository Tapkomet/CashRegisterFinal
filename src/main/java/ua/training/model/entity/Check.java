package ua.training.model.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Check {
    private int id;
    private Timestamp createTime;
    private User cashier;
    private long totalPrice;
    private List<Product> products;
    private Shift shift;

    public Check() {
    }

    public Check(int id, Timestamp createTime, User cashier, long totalPrice, List<Product> products, Shift shift) {
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

    @Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Check other = (Check) o;
        if (this.id != other.id) return false;
        if (this.createTime == null ? other.createTime != null : !this.createTime.equals(other.createTime)) return false;
        if (this.cashier == null ? other.cashier != null : !this.cashier.equals(other.cashier)) return false;
        if (this.totalPrice != other.totalPrice) return false;
        if (this.shift == null ? other.shift != null : !this.shift.equals(other.shift)) return false;
        if (this.products == null ? other.products != null : !this.products.equals(other.products)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + this.id;
        result = result * PRIME + (this.createTime == null ? 0 : this.createTime.hashCode());
        result = result * PRIME + (this.cashier == null ? 0 : this.cashier.hashCode());
        result = result * PRIME + (int) (this.totalPrice ^ (this.totalPrice >>> 32));
        result = result * PRIME + (this.shift == null ? 0 : this.shift.hashCode());
        result = result * PRIME + (this.products == null ? 0 : this.products.hashCode());
        return result;
    }

    public static class Builder {

        private int id;
        private Timestamp createTime;
        private User cashier;
        private long totalPrice;
        private List<Product> products;
        private Shift shift;

        public Builder(User cashier) {
            this.cashier = cashier;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder totalPrice(long totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder shift(Shift shift) {
            this.shift = shift;
            return this;
        }

        public Check build() {
            Check check = new Check();
            check.id = this.id;
            check.createTime = this.createTime;
            check.cashier = this.cashier;
            check.totalPrice = this.totalPrice;
            check.products = this.products;
            this.shift = shift;
            return check;
        }
    }
}
