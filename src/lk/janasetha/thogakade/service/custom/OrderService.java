package lk.janasetha.thogakade.service.custom;

import lk.janasetha.thogakade.dto.CompleteOrderDTO;
import lk.janasetha.thogakade.dto.OrderDTO;
import lk.janasetha.thogakade.dto.OrderDetailDTO;
import lk.janasetha.thogakade.dto.QueryDTO;
import lk.janasetha.thogakade.service.SuperService;

import java.sql.Date;
import java.util.List;

public interface OrderService extends SuperService {

    public int addOrder(CompleteOrderDTO order) throws Exception;
    public void cancelOrder(int OrderId) throws Exception;
    public List<QueryDTO> getItem_List();

    public List<OrderDTO> getOrders() throws Exception;

    public List<OrderDetailDTO> getOrderDetailByOrderId(int orderId) throws Exception;

    public OrderDTO searchOrder(int orderId) throws Exception;

    public List<OrderDTO> getOrdersByDate(Date date) throws Exception;
}
