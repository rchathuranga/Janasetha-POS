package lk.janasetha.thogakade.tm;

import lk.janasetha.thogakade.dto.BatchDetailDTO;

import java.sql.Date;

public class BatchDetailTM {
    private int listNo;

    private int bidId;
    private int itemCode;
    private String itemDescription;
    private int qty;
    private int currentStock;
    private double retailPrice;
    private double midPrice;
    private double wholesalePrice;
    private double buyingPrice;
    private Date manufactureDate;
    private Date expireDate;

    private BatchDetailDTO batchDetailDTO;

    private double totalCost;


    public BatchDetailTM(int no,BatchDetailDTO dto) {
        this.batchDetailDTO = dto;
        this.listNo=no;
        this.bidId = dto.getBidId();
        if (dto.getItem() != null) {
            this.itemCode = dto.getItem().getItemCode();
            this.itemDescription = dto.getItem().getDescription();
        }
        this.qty = dto.getQty();
        this.currentStock = dto.getCurrentStock();
        this.retailPrice = dto.getRetailPrice();
        this.midPrice = dto.getMidPrice();
        this.wholesalePrice = dto.getWholesalePrice();
        this.buyingPrice = dto.getBuyingPrice();
        this.manufactureDate = dto.getManufactureDate();
        this.expireDate = dto.getExpireDate();
        this.totalCost = dto.getTotalCost();
    }

    public int getListNo() {
        return listNo;
    }

    public void setListNo(int listNo) {
        this.listNo = listNo;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
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

    public BatchDetailDTO getBatchDetailDTO() {
        return batchDetailDTO;
    }

    public void setBatchDetailDTO(BatchDetailDTO batchDetailDTO) {
        this.batchDetailDTO = batchDetailDTO;
    }
}
