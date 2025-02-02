package lk.janasetha.thogakade.tm;

public class OrderDetailTM {
    private int id;
    private int itemId;
    private String itemDescription;
    private String qty;
    private double unitPrice;
    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getQty() {
        return Double.parseDouble(qty);
    }

    public void setQty(double qty) {
        this.qty = String.valueOf(qty);
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderDetailTM{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", itemDescription='" + itemDescription + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                '}';
    }
}
