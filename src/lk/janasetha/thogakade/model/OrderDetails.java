package lk.janasetha.thogakade.model;

public class OrderDetails {
    private int id;
    private int orderId;
    private int batchItemId;
    private int qty;
    private double unitPrice;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(int id) {
        this.id = id;
    }

    public OrderDetails(int orderId, int batchItemId) {
        this.orderId = orderId;
        this.batchItemId = batchItemId;
    }

    public OrderDetails(int orderId, int batchItemId, int qty, double unitPrice, double total) {
        this.orderId = orderId;
        this.batchItemId = batchItemId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public OrderDetails(int id, int orderId, int batchItemId, int qty, double unitPrice, double total) {
        this.id = id;
        this.orderId = orderId;
        this.batchItemId = batchItemId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBatchItemId() {
        return batchItemId;
    }
    public void setBatchItemId(int batchItemId) {
        this.batchItemId = batchItemId;
    }

    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", batchItemId=" + batchItemId +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
