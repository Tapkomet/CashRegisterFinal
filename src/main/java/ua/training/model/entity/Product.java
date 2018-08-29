package ua.training.model.entity;

import lombok.Data;

@Data
public class Product {
    private int code;
    private String name;
    private long price;
    private boolean soldByWeight;
    private int checkId;
    private int number;
    private long weight;
}
