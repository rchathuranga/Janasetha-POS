package lk.janasetha.thogakade.model;

import java.sql.Time;
import java.sql.Date;

public class Order {
    private int orderId;
    private String billNo;
    private Date date;
    private Time time;
    private boolean paymentCompleted;
    private String saleType;
    private double total;

    public Order() {
    }

    public Order(int orderId) {
        this.orderId = orderId;
    }

    public Order(String billNo, Date date, Time time, boolean paymentCompleted, String saleType, double total) {
        this.billNo = billNo;
        this.date = date;
        this.time = time;
        this.paymentCompleted = paymentCompleted;
        this.saleType = saleType;
        this.total = total;
    }

    public Order(int orderId, String billNo, Date date, Time time, boolean paymentCompleted, String saleType, double total) {
        this.orderId = orderId;
        this.billNo = billNo;
        this.date = date;
        this.time = time;
        this.paymentCompleted = paymentCompleted;
        this.saleType = saleType;
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

    public String getSaleType() {
        return saleType;
    }
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", billNo='" + billNo + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", paymentCompleted=" + paymentCompleted +
                ", saleType='" + saleType + '\'' +
                ", total=" + total +
                '}';
    }
}
