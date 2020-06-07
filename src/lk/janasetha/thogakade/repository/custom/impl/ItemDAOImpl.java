package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.ItemDAO;
import lk.janasetha.thogakade.model.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private Connection connection;
    private String sql;

    @Override
    public Integer add(Item item) throws Exception{
        sql = "INSERT INTO item(description,bill_description,status,cate_id,barcode) VALUES(?,?,?,?)";

        boolean itemAdded = CrudUtil.executeUpdate( sql, item.getDescription(), item.getBillDescription(),item.getStatus(), item.getCategoryId(), item.getBarcode());

        if(itemAdded){

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
    public boolean update(Item item) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Item search(Integer integer) throws Exception {
        sql = "SELECT * FROM item WHERE item_code = ?";
        ResultSet rst = CrudUtil.executeQuery( sql, integer);

        Item item = null;
        while (rst.next()){
            item = new Item();

            item.setItemCode(rst.getInt("item_code"));
            item.setDescription(rst.getString("description"));
            item.setBillDescription(rst.getString("bill_description"));
            item.setStatus(rst.getString("status"));
            item.setRegularPrice(rst.getDouble("regular_price"));
            item.setCategoryId(rst.getInt("cate_id"));
            item.setBarcode(rst.getString("barcode"));


        }

        return item;
    }

    @Override
    public List<Item> getAll() throws Exception {
        sql = "SELECT * FROM item";
        ResultSet rst = CrudUtil.executeQuery( sql);

        List<Item> itemList = new ArrayList<>();
        while (rst.next()){
            Item item = new Item();

            item.setItemCode(rst.getInt("item_code"));
            item.setDescription(rst.getString("description"));
            item.setBillDescription(rst.getString("bill_description"));
            item.setStatus(rst.getString("status"));
            item.setRegularPrice(rst.getDouble("regular_price"));
            item.setCategoryId(rst.getInt("cate_id"));
            item.setBarcode(rst.getString("barcode"));

            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public List<Item> getAllItemsByBarcode(String barcode) throws Exception {
        sql = "SELECT * FROM item where barcode=?";
        ResultSet rst = CrudUtil.executeQuery( sql, barcode);

        List<Item> itemList = new ArrayList<>();
        while (rst.next()){
            Item item = new Item();

            item.setItemCode(rst.getInt("item_code"));
            item.setDescription(rst.getString("description"));
            item.setBillDescription(rst.getString("bill_description"));
            item.setStatus(rst.getString("status"));
            item.setRegularPrice(rst.getDouble("regular_price"));
            item.setCategoryId(rst.getInt("cate_id"));
            item.setBarcode(rst.getString("barcode"));

            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public List<Item> getAllItemsByDescription(String description) throws Exception {
        sql = "SELECT * FROM item where description like ?";
        ResultSet rst = CrudUtil.executeQuery( sql,"%"+ description +"%" );

        List<Item> itemList = new ArrayList<>();
        while (rst.next()){
            Item item = new Item();

            item.setItemCode(rst.getInt("item_code"));
            item.setDescription(rst.getString("description"));
            item.setBillDescription(rst.getString("bill_description"));
            item.setStatus(rst.getString("status"));
            item.setRegularPrice(rst.getDouble("regular_price"));
            item.setCategoryId(rst.getInt("cate_id"));
            item.setBarcode(rst.getString("barcode"));

            itemList.add(item);
        }
        return itemList;
    }
}
