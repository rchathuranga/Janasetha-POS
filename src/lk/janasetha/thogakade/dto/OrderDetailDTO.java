package lk.janasetha.thogakade.dto;

public class OrderDetailDTO {
    private int id;
    private BatchDetailDTO batchDetailDTO;
    private int qty;
    private double unitPrice;
    private double total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(BatchDetailDTO batchDetailDTO, int qty, double unitPrice, double total) {
        this.batchDetailDTO = batchDetailDTO;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public OrderDetailDTO(int id, BatchDetailDTO batchDetailDTO, int qty, double unitPrice, double total) {
        this.id = id;
        this.batchDetailDTO = batchDetailDTO;
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

    public BatchDetailDTO getBatchDetailDTO() {
        return batchDetailDTO;
    }
    public void setBatchDetailDTO(BatchDetailDTO batchDetailDTO) {
        this.batchDetailDTO = batchDetailDTO;
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
        return "OrderDetailDTO{" +
                "id=" + id +
                ", batchDetail=" + batchDetailDTO +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", total=" + total +
                '}';
    }
}
