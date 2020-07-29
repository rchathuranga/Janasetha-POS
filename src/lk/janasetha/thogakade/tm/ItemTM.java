package lk.janasetha.thogakade.tm;

public class ItemTM {
    private int listNumber;
    private int itemCode;
    private String description;
    private String billDescription;
    private String qty;
    private double retailPrice;
    private double midPrice;
    private double wholesalePrice;

    private int sellingQty=1;
    private double sellingPrice;
    private double sellingTotal;


    public ItemTM() {
    }

    public ItemTM(int itemCode, String description, double qty, double retailPrice, double midPrice, double wholesalePrice) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = String.valueOf(qty);
        this.retailPrice = retailPrice;
        this.midPrice = midPrice;
        this.wholesalePrice = wholesalePrice;
    }

    public int getListNumber() {
        return listNumber;
    }
    public void setListNumber(int listNumber) {
        this.listNumber = listNumber;
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

    public double getQty() {
        return Double.parseDouble(qty);
    }

    public void setQty(double qty) {
        this.qty = String.valueOf(qty);
    }

    public double getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getMidPrice() {
        return midPrice;
    }
    public void setMidPrice(double midPrice) {
        this.midPrice = midPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }
    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public int getSellingQty() {
        return sellingQty;
    }
    public void setSellingQty(int sellingQty) {
        this.sellingQty = sellingQty;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getSellingTotal() {
        return sellingTotal;
    }
    public void setSellingTotal(double sellingTotal) {
        this.sellingTotal = sellingTotal;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "listNumber=" + listNumber +
                ", itemCode=" + itemCode +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", retailPrice=" + retailPrice +
                ", midPrice=" + midPrice +
                ", wholesalePrice=" + wholesalePrice +
                ",[ sellingQty=" + sellingQty +
                "| sellingPrice=" + sellingPrice +
                "| sellingTotal=" + sellingTotal +
                "]}";
    }
}
