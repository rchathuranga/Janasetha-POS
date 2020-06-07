package lk.janasetha.thogakade.dto;

import java.util.List;

public class CompleteStockDTO {
    private BatchDTO batch;
    private List<BatchDetailDTO> batchDetail;

    public CompleteStockDTO() {
    }

    public CompleteStockDTO(BatchDTO batch, List<BatchDetailDTO> batchDetail) {
        this.batch = batch;
        this.batchDetail = batchDetail;
    }

    public BatchDTO getBatch() {
        return batch;
    }

    public void setBatch(BatchDTO batch) {
        this.batch = batch;
    }

    public List<BatchDetailDTO> getBatchDetail() {
        return batchDetail;
    }

    public void setBatchDetail(List<BatchDetailDTO> batchDetail) {
        this.batchDetail = batchDetail;
    }

    public String toString(){
        return ""+batch.getBatchId();
    }
}
