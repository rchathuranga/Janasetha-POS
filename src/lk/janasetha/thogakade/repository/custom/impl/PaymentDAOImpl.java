package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.Payment;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.PaymentDAO;

import java.sql.ResultSet;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private String sql;

    @Override
    public Integer add(Payment payment) throws Exception {
        sql = "INSERT INTO payment(date,time,amount,balance,order_id) VALUES(CURDATE(),CURTIME(),?,?,?)";
        boolean paymentAdded = CrudUtil.executeUpdate(sql, payment.getAmount(), payment.getBalance(), payment.getOrderId());

        if (paymentAdded) {
            sql = "SELECT LAST_INSERT_ID()";
            ResultSet rst = CrudUtil.executeQuery(sql);
            if (rst.next()) {
                int lastAddId = rst.getInt(1);
                return lastAddId;
            }
        }
        return -1;
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Payment search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<Payment> getAll() throws Exception {
        return null;
    }
}
