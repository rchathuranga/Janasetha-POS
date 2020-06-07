package lk.janasetha.thogakade.dto;

import java.sql.Date;

public class QueryDTO {
    private int batchId;
    private int batchDetailId;
    private Date date;


    private int itemCode;
    private String description;
    private int currentQty;
    private double retailPrice;
    private double midPrice;
    private double wholesalePrice;
    private double buyingPrice;
    private String bilDescription;

    public int getBatchId() {
        return batchId;
    }
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public int getBatchDetailId() {
        return batchDetailId;
    }
    public void setBatchDetailId(int batchDetailId) {
        this.batchDetailId = batchDetailId;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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

    public int getCurrentQty() {
        return currentQty;
    }
    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
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

    public String getBilDescription() {
        return bilDescription;
    }
    public void setBilDescription(String bilDescription) {
        this.bilDescription = bilDescription;
    }

    @Override
    public String toString() {
        return "QueryDTO{" +
                "batchId=" + batchId +
                ", batchDetailId=" + batchDetailId +
                ", date=" + date +
                ", itemCode=" + itemCode +
                ", description='" + description + '\'' +
                ", currentQty=" + currentQty +
                ", retailPrice=" + retailPrice +
                ", midPrice=" + midPrice +
                ", wholesalePrice=" + wholesalePrice +
                ", buyingPrice=" + buyingPrice +
                ", bilDescription='" + bilDescription + '\'' +
                '}';
    }
}
