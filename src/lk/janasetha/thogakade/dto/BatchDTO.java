package lk.janasetha.thogakade.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class BatchDTO {

    private int batchId;
    private String supplier;
    private Date date;
    private Time time;
    private String status;
    private String invoiceNo;
    private double billTotal;
    private List<BatchDetailDTO> batchDetails;

    public BatchDTO(String supplier, Date date, Time time, String status) {
        this.supplier = supplier;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public BatchDTO(String supplier, Date date, Time time, String status, String invoiceNo, double billTotal) {
        this.supplier = supplier;
        this.date = date;
        this.time = time;
        this.status = status;
        this.invoiceNo = invoiceNo;
        this.billTotal = billTotal;
    }

    public BatchDTO(int batchId, String supplier, Date date, Time time, String status) {
        this.batchId = batchId;
        this.supplier = supplier;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public int getBatchId() {
        return batchId;
    }
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }
    public void setTime(Time time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }
    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public double getBillTotal() {
        return billTotal;
    }
    public void setBillTotal(double billTotal) {
        this.billTotal = billTotal;
    }

    public List<BatchDetailDTO> getBatchDetails() {
        return batchDetails;
    }
    public void setBatchDetails(List<BatchDetailDTO> batchDetails) {
        this.batchDetails = batchDetails;
    }

    @Override
    public String toString() {
        return "BatchDTO{" +
                "batchId=" + batchId +
                ", supplier='" + supplier + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", status='" + status + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", billTotal=" + billTotal +
                ", batchDetails=" + batchDetails +
                '}';
    }
}
