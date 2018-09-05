package ua.training.model.entity;

import lombok.Data;

import java.util.Comparator;

@Data
public class Product {
    private int code;
    private String name;
    private long price;
    private boolean soldByWeight;
    private Check check;
    private int number;
    private long weight;
    private User manager;

    public static Comparator<Product> ProductCodeComparator = (s1, s2) -> {
        int code1 = s1.getCode();
        int code2 = s2.getCode();
        return code1-code2;
    };

    public static Comparator<Product> ProductNameComparator = (s1, s2) -> {
        String name1 = s1.getName().toUpperCase();
        String name2 = s2.getName().toUpperCase();
        return name1.compareTo(name2);
    };

    public static Comparator<Product> ProductPriceComparator = (s1, s2) -> {
        long price1 = s1.getPrice();
        long price2 = s2.getPrice();
        return Long.compare(price1, price2);
    };
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + code +
                ", name='" + name +
                ", price=" + price +
                ", soldByWeight=" + soldByWeight +
                ", number=" + number +
                ", weight=" + weight +
                '}';
    }


    public static class Builder {

        private int code;
        private String name;
        private long price;
        private boolean soldByWeight;
        private Check check;
        private int number;
        private long weight;
        private User manager;

        public Builder(int code) {
            this.code = code;
        }

        public Builder productName(String name) {
            this.name = name;
            return this;
        }

        public Builder price(long price) {
            this.price = price;
            return this;
        }

        public Builder isSoldByWeight(boolean soldByWeight) {
            this.soldByWeight = soldByWeight;
            return this;
        }

        public Builder fromCheck(Check check) {
            this.check = check;
            return this;
        }

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder weight(long weight) {
            this.weight = weight;
            return this;
        }

        public Builder byManager(User manager) {
            this.manager = manager;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.code = this.code;
            product.name = this.name;
            product.price = this.price;
            product.soldByWeight = this.soldByWeight;
            product.check = this.check;
            product.number = this.number;
            product.weight = this.weight;
            product.manager = this.manager;
            return product;
        }
    }

}
