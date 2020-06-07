package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.repository.SuperDAO;
import lk.janasetha.thogakade.dto.QueryDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<QueryDTO> getAvailableStock() throws Exception;
    public List<QueryDTO> getAvailableStockByBarcode(String barcode) throws Exception;
    public List<QueryDTO> getAvailableStockByDescription(String description) throws Exception;

    public List<QueryDTO> getBatchDetailsByItemCode(int itemCode,boolean onlyAvailable) throws Exception;
    public List<QueryDTO> getLatestBatchDetailsByItemCode(int itemCode) throws Exception;
}
