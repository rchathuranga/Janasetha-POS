package lk.janasetha.thogakade.service.custom;

import lk.janasetha.thogakade.dto.QueryDTO;
import lk.janasetha.thogakade.service.SuperService;
import lk.janasetha.thogakade.dto.CompleteOrderDTO;

import java.util.List;

public interface OrderService extends SuperService {

    public int addOrder(CompleteOrderDTO order) throws Exception;
    public void cancelOrder(int OrderId) throws Exception;
    public List<QueryDTO> getItem_List();
}
