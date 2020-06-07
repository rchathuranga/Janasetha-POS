package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.dto.ItemDTO;
import lk.janasetha.thogakade.repository.CrudDAO;
import lk.janasetha.thogakade.model.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item,Integer> {
    public List<Item> getAllItemsByBarcode(String barcode) throws Exception;
    public List<Item> getAllItemsByDescription(String description) throws Exception;
}
