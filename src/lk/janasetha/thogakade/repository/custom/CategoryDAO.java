package lk.janasetha.thogakade.repository.custom;

import lk.janasetha.thogakade.model.Category;
import lk.janasetha.thogakade.repository.SuperDAO;

import java.util.List;

public interface CategoryDAO extends SuperDAO {
    public int addCategory(Category category) throws Exception;

    public boolean updateCategory(Category category) throws Exception;

    public List<Category> getAll() throws Exception;

    public List<Category> getAllByStatus(String status) throws Exception;

    public Category search(int cateId) throws Exception;

    public Category searchByCateIdAndStatus(int cateId, String status) throws Exception;
}
