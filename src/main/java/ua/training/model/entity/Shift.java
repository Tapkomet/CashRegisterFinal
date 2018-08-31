package ua.training.model.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Shift {
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;
    private User cashier;
    private long startingCash;

    public Shift(){
    }

    public Shift(int id, Timestamp startTime, Timestamp endTime, User cashier, long startingCash){
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cashier = cashier;
        this.startingCash = startingCash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public User getCashier() {
        return cashier;
    }

    public void setCashier(User cashier) {
        this.cashier = cashier;
    }

    public long getStartingCash() {
        return startingCash;
    }

    public void setStartingCash(long startingCash) {
        this.startingCash = startingCash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", startTime='" + startTime +
                ", endTime='" + endTime +
                ", cashier=" + cashier +
                ", startingCash=" + startingCash +
                '}';
    }
}
