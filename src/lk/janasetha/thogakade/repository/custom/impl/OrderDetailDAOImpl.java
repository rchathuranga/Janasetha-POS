package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.OrderDetails;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.OrderDetailDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    private Connection connection;
    private String sql;

    @Override
    public Integer add(OrderDetails orderDetails) {
        try {
            sql = "Insert into order_details(order_id,batch_item_id,qty,unit_price,total) values(?,?,?,?,?)";

            boolean orderAdded = CrudUtil.executeUpdate(
                    sql,
                    orderDetails.getOrderId(),
                    orderDetails.getBatchItemId(),
                    orderDetails.getQty(),
                    orderDetails.getUnitPrice(),
                    orderDetails.getTotal());

            if(orderAdded){
                return getLastInsertedId();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

       return -1;
    }

    @Override
    public boolean update(OrderDetails orderDetails) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public OrderDetails search(Integer integer) {
        return null;
    }

    @Override
    public List<OrderDetails> getAll() {
        return null;
    }

    private int getLastInsertedId() throws SQLException, ClassNotFoundException {
        sql = "select LAST_INSERT_ID()";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if(rst.next()){
            int lastAddId = rst.getInt(1);
            return lastAddId;
        }
        return -1;
    }

    @Override
    public List<OrderDetails> getOrderDetailsByOrderId(int orderId) throws Exception {
        List<OrderDetails> detailsList = new ArrayList<>();
        sql = "Select * from order_details where order_id=?";

        ResultSet rst = CrudUtil.executeQuery(sql, orderId);
        while (rst.next()) {
            OrderDetails orderDetails = new OrderDetails();

            orderDetails.setId(rst.getInt("id"));
            orderDetails.setOrderId(rst.getInt("order_id"));
            orderDetails.setBatchItemId(rst.getInt("batch_item_id"));
            orderDetails.setQty(rst.getInt("qty"));
            orderDetails.setUnitPrice(rst.getDouble("unit_price"));
            orderDetails.setTotal(rst.getDouble("total"));

            detailsList.add(orderDetails);
        }

        return detailsList;
    }
}
