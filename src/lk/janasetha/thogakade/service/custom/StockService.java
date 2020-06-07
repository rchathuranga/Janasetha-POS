package lk.janasetha.thogakade.service.custom;

import lk.janasetha.thogakade.dto.ItemDTO;
import lk.janasetha.thogakade.service.SuperService;
import lk.janasetha.thogakade.dto.CompleteStockDTO;
import lk.janasetha.thogakade.dto.QueryDTO;

import java.util.List;

public interface StockService extends SuperService {

    public int addNewStock(CompleteStockDTO stockDTO) throws Exception;

    public List<CompleteStockDTO> getAllStockDetails() throws Exception;

    public List<QueryDTO> getAvailableStock() throws Exception;

    public List<QueryDTO> getAvailableStockByBarcode(String barcode) throws Exception;

    public List<QueryDTO> getAvailableStockByDescription(String description) throws Exception;

    public List<QueryDTO> getBatchDetailsByItemCode(int itemCode) throws Exception;

    public List<ItemDTO> getAllItems() throws Exception;
    public List<ItemDTO> getAllItemsByBarcode(String barcode) throws Exception;
    public List<ItemDTO> getAllItemsByDescription(String description) throws Exception;
    public List<QueryDTO> getLatestBatchDetailsByItemCode(int itemCode) throws Exception;
}
