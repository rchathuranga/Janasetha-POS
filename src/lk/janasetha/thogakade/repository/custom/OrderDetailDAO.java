package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.model.OrderDetails;
import lk.janasetha.thogakade.repository.CrudDAO;

import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetails, Integer> {
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) throws Exception;
}
