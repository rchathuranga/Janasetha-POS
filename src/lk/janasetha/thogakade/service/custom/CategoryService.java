package lk.janasetha.thogakade.service.custom;

import lk.janasetha.thogakade.dto.CategoryDTO;
import lk.janasetha.thogakade.service.SuperService;

import java.util.List;

public interface CategoryService extends SuperService {
    public List<CategoryDTO> getAllActiveCategories() throws Exception;

    public List<CategoryDTO> getAllCategories() throws Exception;

    public int addCategory(CategoryDTO categoryDTO) throws Exception;

    public boolean updateCategory(CategoryDTO categoryDTO) throws Exception;

    public CategoryDTO searchCategoryById(int id) throws Exception;
}
