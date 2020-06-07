package lk.janasetha.thogakade.dto;

import java.sql.Time;
import java.sql.Date;

public class OrderDTO {
    private int orderId;
    private String billNo;
    private Date date;
    private Time time;
    private boolean paymentCompleted;
    private String salesType;
    private double paidAmount;
    private double balance;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(String billNo, boolean paymentCompleted, String salesType, double total) {
        this.billNo = billNo;
        this.paymentCompleted = paymentCompleted;
        this.salesType = salesType;
        this.total = total;
    }

    public OrderDTO(String billNo, String salesType, double paidAmount, double balance, double total) {
        this.billNo = billNo;
        this.salesType = salesType;
        this.paidAmount = paidAmount;
        this.balance = balance;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }

    public String getSalesType() {
        return salesType;
    }

    public void setSalesType(String salesType) {
        this.salesType = salesType;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
