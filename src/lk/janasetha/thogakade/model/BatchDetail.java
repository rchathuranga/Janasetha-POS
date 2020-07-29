package lk.janasetha.thogakade.model;

import java.sql.Date;

public class BatchDetail {
    private int bidId;
    private int batchId;
    private int itemCode;
    private double qty;
    private double currentStock;
    private double retailPrice;
    private double midPrice;
    private double wholesalePrice;
    private double buyingPrice;
    private Date manufactureDate;
    private Date expireDate;

    public BatchDetail() {
    }

    public BatchDetail(int batchId, int itemCode) {
        this.batchId = batchId;
        this.itemCode = itemCode;
    }

    public BatchDetail(int batchId, int itemCode, double qty, double currentStock, double retailPrice, double midPrice, double wholesalePrice, double buyingPrice, Date manufactureDate, Date expireDate) {
        this.batchId = batchId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.currentStock = currentStock;
        this.retailPrice = retailPrice;
        this.midPrice = midPrice;
        this.wholesalePrice = wholesalePrice;
        this.buyingPrice = buyingPrice;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
    }

    public BatchDetail(int bidId, int batchId, int itemCode, double qty, double currentStock, double retailPrice, double midPrice, double wholesalePrice, double buyingPrice, Date manufactureDate, Date expireDate) {
        this.bidId = bidId;
        this.batchId = batchId;
        this.itemCode = itemCode;
        this.qty = qty;
        this.currentStock = currentStock;
        this.retailPrice = retailPrice;
        this.midPrice = midPrice;
        this.wholesalePrice = wholesalePrice;
        this.buyingPrice = buyingPrice;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
    }

    public int getBidId() {
        return bidId;
    }
    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getBatchId() {
        return batchId;
    }
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getItemCode() {
        return itemCode;
    }
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(double currentStock) {
        this.currentStock = currentStock;
    }

    public double getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }
    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }
    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getMidPrice() {
        return midPrice;
    }
    public void setMidPrice(double midPrice) {
        this.midPrice = midPrice;
    }

    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    @Override
    public String toString() {
        return "BatchDetail{" +
                "bidId=" + bidId +
                ", batchId=" + batchId +
                ", itemCode=" + itemCode +
                ", qty=" + qty +
                ", currentStock=" + currentStock +
                ", retailPrice=" + retailPrice +
                ", midPrice=" + midPrice +
                ", wholesalePrice=" + wholesalePrice +
                ", buyingPrice=" + buyingPrice +
                ", manufactureDate=" + manufactureDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
