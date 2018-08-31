package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

public class User {
    private int id;
    private String surname;
    private String email;
    private String password;
    private ROLE role;

    public enum ROLE {
        CASHIER, SENIOR_CASHIER, PRODUCT_MANAGER
    }

    public User(){
    }

    public User (int id, String surname, String email, String password){
        this.id = id;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public User (int id, String surname, String email, String password, ROLE role){
        this.id = id;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public void setRoleFromString(String role){
        this.role = ROLE.valueOf(role.toUpperCase());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname +
                ", email=" + email +
                ", password=" + password +
                ", role=" + role +
                '}';
    }
}
