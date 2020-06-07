package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.OrderDAO;
import lk.janasetha.thogakade.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;
    private String sql;

    @Override
    public Integer add(Order order) {
        try {
            sql = "INSERT INTO `order`(bill_no,date,time,payment_complete,sales_type,total) VALUES(?,curdate(),current_time(),?,?,?)";

            boolean orderAdded = CrudUtil.executeUpdate(
                    
                    sql,
                    order.getBillNo(),
                    order.isPaymentCompleted(),
                    order.getSaleType(),
                    order.getTotal());

            if(orderAdded){

                sql = "SELECT LAST_INSERT_ID()";
                ResultSet rst = CrudUtil.executeQuery(sql);
                if(rst.next()){
                    int lastAddId = rst.getInt(1);
                    return lastAddId;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public Order search(Integer integer) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
