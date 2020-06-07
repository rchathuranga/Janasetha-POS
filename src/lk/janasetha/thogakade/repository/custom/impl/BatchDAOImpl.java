package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.Batch;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.BatchDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BatchDAOImpl implements BatchDAO {

    private Connection connection;
    private String sql;

    @Override
    public Integer add(Batch batch) throws Exception{
        sql = "INSERT INTO batch(supplier,date,time,status,invoice_no,bill_total) VALUES(?,CURDATE(),CURTIME(),?,?,?)";

        boolean batchAdded = CrudUtil.executeUpdate( sql, batch.getSupplier(), batch.getStatus(),batch.getInvoiceNo(),batch.getBillTotal());
        if(batchAdded){

            sql = "SELECT LAST_INSERT_ID()";
            ResultSet rst = CrudUtil.executeQuery(sql);
            if(rst.next()){
                int lastAddId = rst.getInt(1);
                return lastAddId;
            }
        }
        return -1;
    }

    @Override
    public boolean update(Batch batch) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Batch search(Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<Batch> getAll() throws Exception {
        sql = "SELECT * FROM batch";

        List<Batch> batches = new ArrayList<>();

        ResultSet rst = CrudUtil.executeQuery( sql);

        while (rst.next()){
            Batch batch = new Batch();

            batch.setBatchId(rst.getInt("batch_id"));
            batch.setSupplier(rst.getString("supplier"));
            batch.setDate(rst.getDate("date"));
            batch.setTime(rst.getTime("time"));
            batch.setInvoiceNo(rst.getString("invoice_no"));
            batch.setBillTotal(rst.getDouble("bill_total"));
            batch.setStatus(rst.getString("status"));

            batches.add(batch);
        }

        if (batches.isEmpty()) batches = null;
        return batches;
    }
}
