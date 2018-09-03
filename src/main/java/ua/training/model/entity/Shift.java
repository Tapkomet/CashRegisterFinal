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

    public Shift() {
    }

    public Shift(int id, Timestamp startTime, Timestamp endTime, User cashier, long startingCash) {
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

    @Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Shift other = (Shift) o;
        if (this.id != other.id) return false;
        if (this.startTime == null ? other.startTime != null : !this.startTime.equals(other.startTime)) return false;
        if (this.endTime == null ? other.endTime != null : !this.endTime.equals(other.endTime)) return false;
        if (this.cashier == null ? other.cashier != null : !this.cashier.equals(other.cashier)) return false;
        if (this.startingCash != other.startingCash) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + this.id;
        result = result * PRIME + (this.startTime == null ? 0 : this.startTime.hashCode());
        result = result * PRIME + (this.endTime == null ? 0 : this.endTime.hashCode());
        result = result * PRIME + (this.cashier == null ? 0 : this.cashier.hashCode());
        result = result * PRIME + (int) (this.startingCash ^ (this.startingCash >>> 32));
        return result;
    }

    public static class Builder {

        private int id;
        private Timestamp startTime;
        private Timestamp endTime;
        private User cashier;
        private long startingCash;

        public Builder(User cashier) {
            this.cashier = cashier;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder startingCash(long startingCash) {
            this.startingCash = startingCash;
            return this;
        }

        public Builder byCashier(User cashier) {
            this.cashier = cashier;
            return this;
        }

        public Builder startTime(Timestamp startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(Timestamp endTime) {
            this.endTime = endTime;
            return this;
        }

        public Shift build() {
            Shift shift = new Shift();
            shift.id = this.id;
            shift.startTime = this.startTime;
            shift.endTime = this.endTime;
            shift.cashier = this.cashier;
            return shift;
        }
    }
}
