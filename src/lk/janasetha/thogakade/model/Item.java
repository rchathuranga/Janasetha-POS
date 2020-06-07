package lk.janasetha.thogakade.model;

public class Item {
    private int itemCode;
    private String description;
    private String billDescription;
    private String status;
    private int categoryId;
    private double regularPrice;
    private String barcode;


    public Item() {
    }

    public Item(int itemCode) {
        this.itemCode = itemCode;
    }

    public Item(String description, String billDescription, String status, int categoryId, String barcode) {
        this.description = description;
        this.billDescription = billDescription;
        this.status = status;
        this.categoryId = categoryId;
        this.barcode = barcode;
    }

    public Item(int itemCode, String description, String status, int categoryId, String barcode) {
        this.itemCode = itemCode;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
        this.barcode = barcode;
    }

    public int getItemCode() {
        return itemCode;
    }
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillDescription() {
        return billDescription;
    }
    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getRegularPrice() {
        return regularPrice;
    }
    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode=" + itemCode +
                ", description='" + description + '\'' +
                ", billDescription='" + billDescription + '\'' +
                ", status='" + status + '\'' +
                ", categoryId=" + categoryId +
                ", regularPrice=" + regularPrice +
                ", barcode='" + barcode + '\'' +
                '}';
    }
}
