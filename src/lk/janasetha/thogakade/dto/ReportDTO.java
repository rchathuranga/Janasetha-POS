package lk.janasetha.thogakade.dto;

public class ReportDTO {
    private String itemId;
    private String billDescription;
    private String total;

    public ReportDTO(String itemId, String billDescription, String total) {
        this.itemId = itemId;
        this.billDescription = billDescription;
        this.total = total;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBillDescription() {
        return billDescription;
    }

    public void setBillDescription(String billDescription) {
        this.billDescription = billDescription;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
