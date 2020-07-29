package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.BatchDetail;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.BatchDetailDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BatchDetailDAOImpl implements BatchDetailDAO {

    private Connection connection;
    private String sql;

    @Override
    public Integer add(BatchDetail bd) throws Exception {
        sql = "INSERT INTO batch_details(batch_id, item_code, qty, current_stock, retail_price,buying_price, mid_price, wholesale_price, exp_date, manu_date) VALUES(?,?,?,?,?,?,?,?,?,?)";

        System.out.println(bd.getBatchId());

        boolean batchDetailAdded = CrudUtil.executeUpdate( sql, bd.getBatchId(), bd.getItemCode(), bd.getQty(),
                bd.getCurrentStock(), bd.getRetailPrice(), bd.getBuyingPrice(), bd.getMidPrice(), bd.getWholesalePrice(),bd.getExpireDate(), bd.getManufactureDate());
        if (batchDetailAdded) {

            sql = "SELECT LAST_INSERT_ID()";
            ResultSet rst = CrudUtil.executeQuery( sql);
            if (rst.next()) {
                int lastAddId = rst.getInt(1);
                return lastAddId;
            }
        }
        return -1;
    }

    @Override
    public boolean update(BatchDetail batchDetail) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public BatchDetail search(Integer integer) {
        return null;
    }

    @Override
    public List<BatchDetail> getAll() throws Exception{
        sql = "SELECT * FROM batch_details";

        List<BatchDetail> batchDetails;

        ResultSet rst = CrudUtil.executeQuery( sql);

        batchDetails = convertRSTtoModel(rst);

        if (batchDetails.isEmpty()) batchDetails = null;
        return batchDetails;
    }


    @Override
    public int getCurrentStock(int batchDetailId) throws Exception {
        sql = "SELECT current_stock FROM batch_details WHERE bid_id = ?";

        ResultSet rst = CrudUtil.executeQuery( sql, batchDetailId);
        if (rst.next()) {
            return rst.getInt(1);
        }

        return -1;
    }

    @Override
    public List<BatchDetail> getMaxCurrentStockByItem(int itemCode) throws Exception {
        sql = "SELECT * FROM batch_details WHERE item_code = ? && current_stock>0 ORDER BY current_stock DESC";

        List<BatchDetail> batchDetails = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery( sql, itemCode);

        batchDetails = convertRSTtoModel(rst);

        if (batchDetails.isEmpty()) batchDetails = null;
        return batchDetails;
    }

    @Override
    public List<BatchDetail> getMaxCurrentStock() throws Exception {
        sql = "SELECT * FROM batch_details WHERE && current_stock>0 ORDER BY current_stock DESC";

        List<BatchDetail> batchDetails = new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery( sql);

        while (rst.next()) {
            BatchDetail batchDetail = new BatchDetail(
                    rst.getInt("bid_id"),
                    rst.getInt("batch_id"),
                    rst.getInt("item_code"),
                    rst.getInt("qty"),
                    rst.getInt("current_stock"),
                    rst.getDouble("selling_price"),
                    rst.getDouble("buying_price"),
                    rst.getDouble("mid_price"),
                    rst.getDate("exp_date"),
                    rst.getDate("manu_date"));
            batchDetails.add(batchDetail);
        }

        if (batchDetails.size() == 0) batchDetails = null;
        return batchDetails;
    }

    @Override
    public boolean deductCurrentStock(int bidID, double deductQty) throws Exception {
        sql = "UPDATE batch_details SET current_stock = current_stock - ? WHERE bid_id = ?";
        return CrudUtil.executeUpdate( sql, deductQty, bidID);
    }

    @Override
    public List<BatchDetail> getAllByBatchId(int batchId) throws Exception {
        sql = "SELECT * FROM batch_details where batch_id = ?";

        List<BatchDetail> batchDetails;

        ResultSet rst = CrudUtil.executeQuery( sql, batchId);

        batchDetails = convertRSTtoModel(rst);

        if (batchDetails.isEmpty()) batchDetails = null;
        return batchDetails;
    }

    @Override
    public List<BatchDetail> getOldestBatchDetail(int itemCode) throws Exception {
        sql = "select * from batch b,batch_details bd where b.date < curdate() && b.batch_id=bd.batch_id && bd.item_code=?  && bd.current_stock>0 order by b.date asc";

        ResultSet rst = CrudUtil.executeQuery( sql, itemCode);
        return convertRSTtoModel(rst);
    }

    @Override
    public int getQtyOnHand(int itemCode) throws Exception {
        sql = "select sum(current_stock) as qtyOnHand from batch_details where item_code=? group by item_code";

        ResultSet rst = CrudUtil.executeQuery( sql, itemCode);
        int count=0;
        if(rst.next()){
            count = rst.getInt("qtyOnHand");
        }
        return count;
    }

    private List<BatchDetail> convertRSTtoModel(ResultSet rst) throws Exception{
        List<BatchDetail> batchDetails = new ArrayList<>();

        while (rst.next()) {
            BatchDetail batchDetail = new BatchDetail(
                    rst.getInt("bid_id"),
                    rst.getInt("batch_id"),
                    rst.getInt("item_code"),
                    rst.getInt("qty"),
                    rst.getInt("current_stock"),
                    rst.getDouble("retail_price"),
                    rst.getDouble("mid_price"),
                    rst.getDouble("wholesale_price"),
                    rst.getDouble("buying_price"),
                    rst.getDate("exp_date"),
                    rst.getDate("manu_date"));
            batchDetails.add(batchDetail);
        }

        return batchDetails;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
