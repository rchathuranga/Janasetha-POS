package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.Supplier;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.SupplierDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public Integer add(Supplier supplier) throws Exception {
        String sql = "INSERT INTO supplier(supplier,description,buying_price_percentage) VALUES(?,?,?)";

        boolean isSupplierAdded = CrudUtil.executeUpdate(sql, supplier.getSupplier(), supplier.getDescription(), supplier.getBuyingPricePercentage());

        if (isSupplierAdded) {
            sql = "SELECT LAST_INSERT_ID()";
            ResultSet rst = CrudUtil.executeQuery(sql);
            if (rst.next()) {
                return rst.getInt(1);
            }
        }

        return -1;
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {
        String sql = "UPDATE supplier SET supplier=?, description=?, buying_price_percentage=? WHERE supplier_id=?";
        return CrudUtil.executeUpdate(sql, supplier.getSupplier(), supplier.getDescription(), supplier.getBuyingPricePercentage(), supplier.getSupplierId());
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }

    @Override
    public Supplier search(Integer integer) throws Exception {
        String sql = "SELECT * FROM supplier WHERE supplier_id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, integer);

        if (rst.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupplierId(rst.getInt("supplier_id"));
            supplier.setSupplier(rst.getString("supplier"));
            supplier.setDescription(rst.getString("description"));
            supplier.setBuyingPricePercentage(rst.getDouble("buying_price_percentage"));

            return supplier;
        }

        return null;
    }

    @Override
    public List<Supplier> getAll() throws Exception {
        List<Supplier> suppliers = new ArrayList<>();

        String sql = "SELECT * FROM supplier";
        ResultSet rst = CrudUtil.executeQuery(sql);

        while (rst.next()) {
            Supplier supplier = new Supplier();
            supplier.setSupplierId(rst.getInt("supplier_id"));
            supplier.setSupplier(rst.getString("supplier"));
            supplier.setDescription(rst.getString("description"));
            supplier.setBuyingPricePercentage(rst.getDouble("buying_price_percentage"));

            suppliers.add(supplier);
        }

        return suppliers;
    }
}
