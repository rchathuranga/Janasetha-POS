package lk.janasetha.thogakade.dto;

import java.sql.Date;

public class BatchDetailDTO {
    private int bidId;
    private int batchId;
    private ItemDTO item;
    private double qty;
    private double currentStock;
    private double retailPrice;
    private double midPrice;
    private double wholesalePrice;
    private double buyingPrice;
    private Date manufactureDate;
    private Date expireDate;

    private double totalCost;

    public BatchDetailDTO(int bidId) {
        this.bidId = bidId;
    }

    public BatchDetailDTO(int bidId, ItemDTO item) {
        this.bidId = bidId;
        this.item = item;
    }

    public BatchDetailDTO(int batchId, ItemDTO item, double qty, double currentStock, double retailPrice, double midPrice, double wholesalePrice, double buyingPrice, Date manufactureDate, Date expireDate) {
        this.batchId = batchId;
        this.item = item;
        this.qty = qty;
        this.currentStock = currentStock;
        this.retailPrice = retailPrice;
        this.midPrice = midPrice;
        this.wholesalePrice = wholesalePrice;
        this.buyingPrice = buyingPrice;
        this.manufactureDate = manufactureDate;
        this.expireDate = expireDate;
    }

    public BatchDetailDTO(int bidId, int batchId, ItemDTO item, double qty, double currentStock, double retailPrice, double midPrice, double wholesalePrice, double buyingPrice, Date manufactureDate, Date expireDate) {
        this.bidId = bidId;
        this.batchId = batchId;
        this.item = item;
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

    public ItemDTO getItem() {
        return item;
    }
    public void setItem(ItemDTO item) {
        this.item = item;
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

    public double getBuyingPrice() {
        return buyingPrice;
    }
    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }
    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "BatchDetailDTO{" +
                "bidId=" + bidId +
                ", itemId=" + item +
                ", qty=" + qty +
                ", currentStock=" + currentStock +
                ", sellingPrice=" + retailPrice +
                ", buyingPrice=" + wholesalePrice +
                ", midPrice=" + midPrice +
                ", manufactureDate=" + manufactureDate +
                ", expireDate=" + expireDate +
                '}';
    }
}
