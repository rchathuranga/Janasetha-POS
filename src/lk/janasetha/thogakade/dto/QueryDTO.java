package lk.janasetha.thogakade.dto;

import lk.janasetha.thogakade.tm.ButtonTM;

import java.sql.Date;

public class QueryDTO {
    private int batchId;
    private int batchDetailId;
    private Date date;


    private int itemCode;
    private String description;
    private double currentQty;
    private String measureUnit;
    private double retailPrice;
    private double midPrice;
    private double wholesalePrice;
    private double buyingPrice;
    private String billDescription;


    private int itemCount;
    private double sellingQty;
    //price after processing sales_type
    private double sellingPrice;
    private double sellingTotal;

    private ButtonTM removeButton;

    public QueryDTO() {
//        removeButton = new ButtonTM("Remove");
    }

    public QueryDTO(int batchId, int batchDetailId, Date date, int itemCode, String description, double currentQty, String measureUnit, double retailPrice, double midPrice, double wholesalePrice, double buyingPrice, String billDescription, int itemCount, double sellingQty, double sellingPrice, double sellingTotal, ButtonTM removeButton) {
        this.batchId = batchId;
        this.batchDetailId = batchDetailId;
        this.date = date;
        this.itemCode = itemCode;
        this.description = description;
        this.currentQty = currentQty;
        this.measureUnit = measureUnit;
        this.retailPrice = retailPrice;
        this.midPrice = midPrice;
        this.wholesalePrice = wholesalePrice;
        this.buyingPrice = buyingPrice;
        this.billDescription = billDescription;
        this.itemCount = itemCount;
        this.sellingQty = sellingQty;
        this.sellingPrice = sellingPrice;
        this.sellingTotal = sellingTotal;
        this.removeButton = removeButton;
    }

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

    public double getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(double currentQty) {
        this.currentQty = currentQty;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
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

    public String getBillDescription() {
        return billDescription;
    }
    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public int getItemCount() {
        return itemCount;
    }
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getSellingQty() {
        return sellingQty;
    }

    public void setSellingQty(double sellingQty) {
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

    public ButtonTM getRemoveButton() {
        return removeButton;
    }
    public void setRemoveButton(ButtonTM removeButton) {
        this.removeButton = removeButton;
        removeButton.setItem(this);
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
                ", sellingQty=" + sellingQty +
                ", retailPrice=" + retailPrice +
                ", midPrice=" + midPrice +
                ", wholesalePrice=" + wholesalePrice +
                ", buyingPrice=" + buyingPrice +
                ", bilDescription='" + billDescription + '\'' +
                '}';
    }

    public QueryDTO clone() {
        return new QueryDTO(
                this.batchId,
                this.batchDetailId,
                this.date,
                this.itemCode,
                this.description,
                this.currentQty,
                this.measureUnit,
                this.retailPrice,
                this.midPrice,
                this.wholesalePrice,
                this.buyingPrice,
                this.billDescription,
                this.itemCount,
                this.sellingQty,
                this.sellingPrice,
                this.sellingTotal,
                this.removeButton
        );
    }
}
