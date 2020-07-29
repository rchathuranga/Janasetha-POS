package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.dto.QueryDTO;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.QueryDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<QueryDTO> getAvailableStock() throws Exception {
        String sql = "Select *,sum(bd.current_stock) as qtyOnHand from item i, batch_details bd where i.item_code=bd.item_code group by i.item_code having qtyOnHand>0 limit 20";

        ResultSet rst = CrudUtil.executeQuery(sql);

        List<QueryDTO> queryDTOList = new ArrayList<>();

        while (rst.next()){

            QueryDTO queryDTO = new QueryDTO();

            queryDTO.setItemCode(rst.getInt("item_code"));
            queryDTO.setDescription(rst.getString("description"));
            queryDTO.setMeasureUnit(rst.getString("measure_unit"));
            queryDTO.setCurrentQty(rst.getInt("qtyOnHand"));
            queryDTO.setRetailPrice(rst.getDouble("retail_price"));
            queryDTO.setMidPrice(rst.getDouble("mid_price"));
            queryDTO.setWholesalePrice(rst.getDouble("wholesale_price"));
            queryDTO.setBillDescription(rst.getString("bill_description"));

            queryDTOList.add(queryDTO);
        }

        return queryDTOList;
    }

    @Override
    public List<QueryDTO> getAvailableStockByBarcode(String barcode) throws Exception {
        String sql = "SELECT *,SUM(BD.current_stock) AS qtyOnHand FROM item I, batch_details BD WHERE I.item_code=BD.item_code && i.barcode=?  GROUP BY I.item_code";

        ResultSet rst = CrudUtil.executeQuery(sql,barcode);

        List<QueryDTO> queryDTOList = new ArrayList<>();

        while (rst.next()){

            QueryDTO queryDTO = new QueryDTO();

            queryDTO.setItemCode(rst.getInt("item_code"));
            queryDTO.setDescription(rst.getString("description"));
            queryDTO.setCurrentQty(rst.getInt("qtyOnHand"));
            queryDTO.setRetailPrice(rst.getDouble("retail_price"));
            queryDTO.setMidPrice(rst.getDouble("mid_price"));
            queryDTO.setWholesalePrice(rst.getDouble("wholesale_price"));
            queryDTO.setBillDescription(rst.getString("bill_description"));

            queryDTOList.add(queryDTO);
        }

        return queryDTOList;
    }

    @Override
    public List<QueryDTO> getAvailableStockByDescription(String description) throws Exception {
        String sql = "SELECT *,SUM(BD.current_stock) AS qtyOnHand FROM item I, batch_details BD WHERE I.item_code=BD.item_code && i.description like ?  GROUP BY I.item_code";

        ResultSet rst = CrudUtil.executeQuery(sql,"%"+description+"%");

        List<QueryDTO> queryDTOList = new ArrayList<>();

        while (rst.next()){

            QueryDTO queryDTO = new QueryDTO();

            queryDTO.setItemCode(rst.getInt("item_code"));
            queryDTO.setDescription(rst.getString("description"));
            queryDTO.setCurrentQty(rst.getInt("qtyOnHand"));
            queryDTO.setRetailPrice(rst.getDouble("retail_price"));
            queryDTO.setMidPrice(rst.getDouble("mid_price"));
            queryDTO.setWholesalePrice(rst.getDouble("wholesale_price"));
            queryDTO.setBillDescription(rst.getString("bill_description"));

            queryDTOList.add(queryDTO);
        }

        return queryDTOList;
    }

    @Override
    public List<QueryDTO> getBatchDetailsByItemCode(int itemCode,boolean onlyAvailable) throws Exception {
        String sql;
        if(onlyAvailable){
            sql = "select * from item i, batch_details bd, batch b where i.item_code = ? && bd.item_code = i.item_code && b.batch_id = bd.batch_id && bd.current_stock>0 order by b.date asc;";
        }else {
            sql = "select * from item i, batch_details bd, batch b where i.item_code=? && bd.item_code=i.item_code && b.batch_id=bd.batch_id order by b.date asc";
        }

        ResultSet rst = CrudUtil.executeQuery(sql,itemCode);

        List<QueryDTO> queryDTOList = new ArrayList<>();

        while (rst.next()){

            QueryDTO queryDTO = new QueryDTO();

            queryDTO.setBatchId(rst.getInt("batch_id"));
            queryDTO.setBatchDetailId(rst.getInt("bid_id"));
            queryDTO.setDate(rst.getDate("date"));
            queryDTO.setItemCode(rst.getInt("item_code"));
            queryDTO.setDescription(rst.getString("description"));
            queryDTO.setCurrentQty(rst.getInt("current_stock"));
            queryDTO.setRetailPrice(rst.getDouble("retail_price"));
            queryDTO.setMidPrice(rst.getDouble("mid_price"));
            queryDTO.setWholesalePrice(rst.getDouble("wholesale_price"));
            queryDTO.setBillDescription(rst.getString("bill_description"));

            queryDTOList.add(queryDTO);
        }

        return queryDTOList;
    }

    @Override
    public List<QueryDTO> getLatestBatchDetailsByItemCode(int itemCode) throws Exception {
        String sql = "select * from item i, batch_details bd, batch b where i.item_code=? && bd.item_code=i.item_code && b.batch_id=bd.batch_id order by b.date desc";

        ResultSet rst = CrudUtil.executeQuery(sql,itemCode);

        List<QueryDTO> queryDTOList = new ArrayList<>();

        while (rst.next()){

            QueryDTO queryDTO = new QueryDTO();

            queryDTO.setBatchId(rst.getInt("batch_id"));
            queryDTO.setBatchDetailId(rst.getInt("bid_id"));
            queryDTO.setDate(rst.getDate("date"));
            queryDTO.setItemCode(rst.getInt("item_code"));
            queryDTO.setDescription(rst.getString("description"));
            queryDTO.setCurrentQty(rst.getInt("current_stock"));
            queryDTO.setRetailPrice(rst.getDouble("retail_price"));
            queryDTO.setMidPrice(rst.getDouble("mid_price"));
            queryDTO.setBuyingPrice(rst.getDouble("buying_price"));
            queryDTO.setWholesalePrice(rst.getDouble("wholesale_price"));
            queryDTO.setBillDescription(rst.getString("bill_description"));

            queryDTOList.add(queryDTO);
        }
        return queryDTOList;
    }
}
