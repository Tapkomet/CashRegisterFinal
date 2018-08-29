package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class User {
    private int id;
    private String surname;
    private String email;
    private String password;
    private ROLE role;

    public enum ROLE {
        CASHIER, SENIOR_CASHIER, PRODUCT_MANAGER
    }

    public void setRoleFromString(String role){
        this.role = ROLE.valueOf(role.toUpperCase());
    }

    List<User> users = new ArrayList<>();
}
