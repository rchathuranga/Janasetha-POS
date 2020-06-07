package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.repository.CrudDAO;
import lk.janasetha.thogakade.model.BatchDetail;

import java.util.List;

public interface BatchDetailDAO extends CrudDAO<BatchDetail, Integer> {
    int getCurrentStock(int batchDetailId) throws Exception;

    List<BatchDetail> getMaxCurrentStockByItem(int itemCode) throws Exception;

    List<BatchDetail> getMaxCurrentStock() throws Exception;

    boolean deductCurrentStock(int bidID, int deductQty) throws Exception;

    List<BatchDetail> getAllByBatchId(int batchId) throws Exception;

    List<BatchDetail> getOldestBatchDetail(int itemCode) throws Exception;

    int getQtyOnHand(int itemCode) throws Exception;
}
