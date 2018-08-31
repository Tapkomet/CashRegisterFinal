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
    private User user;

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
}
