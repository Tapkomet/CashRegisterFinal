package ua.training.model.entity;

import lombok.Data;

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
