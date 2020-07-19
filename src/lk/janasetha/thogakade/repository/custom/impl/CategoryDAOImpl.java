package lk.janasetha.thogakade.repository.custom.impl;

import lk.janasetha.thogakade.model.Category;
import lk.janasetha.thogakade.repository.CrudUtil;
import lk.janasetha.thogakade.repository.custom.CategoryDAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public int addCategory(Category category) throws Exception {
        String sql = "INSERT into category(description, status) value(?,?)";
        boolean b = CrudUtil.executeUpdate(sql, category.getDescription(), category.getStatus());
        if (b) {
            sql = "SELECT LAST_INSERT_ID()";
            ResultSet rst = CrudUtil.executeQuery(sql);
            if (rst.next()) {
                return rst.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public boolean updateCategory(Category category) throws Exception {
        System.out.println(category);
        String sql = "Update category set description=?,status=? where cate_id=?";
        return CrudUtil.executeUpdate(sql, category.getDescription(), category.getStatus(), category.getCateId());
    }

    @Override
    public List<Category> getAll() throws Exception {
        String sql = "SELECT * FROM category";
        ResultSet rst = CrudUtil.executeQuery(sql);

        List<Category> categoryList = new ArrayList<>();

        while (rst.next()) {
            Category category = new Category();
            category.setCateId(rst.getInt("cate_id"));
            category.setDescription(rst.getString("description"));
            category.setStatus(rst.getString("Status"));
            categoryList.add(category);
        }

        return categoryList;
    }

    @Override
    public List<Category> getAllByStatus(String status) throws Exception {
        String sql = "SELECT * FROM category WHERE status=?";
        ResultSet rst = CrudUtil.executeQuery(sql, status);

        List<Category> categoryList = new ArrayList<>();

        while (rst.next()) {
            Category category = new Category();
            category.setCateId(rst.getInt("cate_id"));
            category.setDescription(rst.getString("description"));
            category.setStatus(rst.getString("Status"));
            categoryList.add(category);
        }

        return categoryList;
    }

    @Override
    public Category search(int cateId) throws Exception {
        String sql = "SELECT * FROM category WHERE cate_id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, cateId);


        Category category = null;
        while (rst.next()) {
            category = new Category();
            category.setCateId(rst.getInt("cate_id"));
            category.setDescription(rst.getString("description"));
            category.setStatus(rst.getString("Status"));
        }

        return category;
    }

    @Override
    public Category searchByCateIdAndStatus(int cateId, String status) throws Exception {
        String sql = "SELECT * FROM category WHERE cate_id=? AND status=?";
        ResultSet rst = CrudUtil.executeQuery(sql, cateId, status);


        Category category = null;
        while (rst.next()) {
            category = new Category();
            category.setCateId(rst.getInt("cate_id"));
            category.setDescription(rst.getString("description"));
            category.setStatus(rst.getString("Status"));
        }

        return category;
    }
}
