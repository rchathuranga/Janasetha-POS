package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.model.Item;
import lk.janasetha.thogakade.repository.CrudDAO;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item,Integer> {
    public List<Item> getAllItemsByBarcode(String barcode) throws Exception;
    public List<Item> getAllItemsByDescription(String description) throws Exception;

    public Item getItemByBatchDetailId(int batchDetailId) throws Exception;
}
