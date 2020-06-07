package lk.janasetha.thogakade.model;

import java.sql.Date;
import java.sql.Time;

public class Payment {
    private int paymentId;
    private int orderId;
    private Date date;
    private Time time;
    private double paidAmount;
    private double amount;
    private double balance;

    public Payment() {
    }

    public Payment(int orderId, Date date, Time time, double amount, double balance) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.amount = amount;
        this.balance = balance;
    }

    public Payment(int paymentId, int orderId, Date date, Time time, double paidAmount, double amount, double balance) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.paidAmount = paidAmount;
        this.amount = amount;
        this.balance = balance;
    }

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", orderId=" + orderId +
                ", date=" + date +
                ", time=" + time +
                ", paidAmount=" + paidAmount +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
