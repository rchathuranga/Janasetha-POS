package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.model.Order;
import lk.janasetha.thogakade.repository.CrudDAO;

import java.sql.Date;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order,Integer> {
    public List<Order> getOrdersByDate(Date date) throws Exception;
}
