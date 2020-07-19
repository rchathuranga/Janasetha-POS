package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.Order;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.OrderDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private Connection connection;
    private String sql;

    @Override
    public Integer add(Order order) throws Exception {
        sql = "INSERT INTO `order`(bill_no,date,time,payment_complete,sales_type,total) VALUES(?,curdate(),current_time(),?,?,?)";

        boolean orderAdded = CrudUtil.executeUpdate(
                sql,
                order.getBillNo(),
                order.isPaymentCompleted(),
                order.getSaleType(),
                order.getTotal());

        if (orderAdded) {
            sql = "SELECT LAST_INSERT_ID()";
            ResultSet rst = CrudUtil.executeQuery(sql);
            if (rst.next()) {
                return rst.getInt(1);
            }
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
    public Order search(Integer integer) throws Exception {
        sql = "SELECT * FROM `order` where order_id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, integer);
        Order order = null;
        if (rst.next()) {
            order = new Order();
            order.setOrderId(rst.getInt("order_id"));
            order.setBillNo(rst.getString("bill_no"));
            order.setDate(rst.getDate("date"));
            order.setTime(rst.getTime("time"));
            order.setPaymentCompleted(rst.getBoolean("payment_complete"));
            order.setSaleType(rst.getString("sales_type"));
            order.setTotal(rst.getDouble("total"));
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws Exception {
        List<Order> list = new ArrayList<>();

        sql = "SELECT * FROM `order`";
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()) {
            Order order = new Order();

            order.setDate(rst.getDate("date"));
            order.setTime(rst.getTime("time"));
            order.setOrderId(rst.getInt("order_id"));
            order.setPaymentCompleted(rst.getBoolean("payment_complete"));
            order.setSaleType(rst.getString("sales_type"));
            order.setBillNo(rst.getString("bill_no"));
            order.setTotal(rst.getDouble("total"));

            list.add(order);
        }
        return list;
    }

    @Override
    public List<Order> getOrdersByDate(Date date) throws Exception {
        List<Order> list = new ArrayList<>();

        sql = "SELECT * FROM `order` where date=?";
        ResultSet rst = CrudUtil.executeQuery(sql, date);
        while (rst.next()) {
            Order order = new Order();

            order.setOrderId(rst.getInt("order_id"));
            order.setDate(rst.getDate("date"));
            order.setBillNo(rst.getString("bill_no"));
            order.setTime(rst.getTime("time"));
            order.setPaymentCompleted(rst.getBoolean("payment_complete"));
            order.setSaleType(rst.getString("sales_type"));
            order.setTotal(rst.getDouble("total"));

            list.add(order);
        }
        return list;
    }
}
