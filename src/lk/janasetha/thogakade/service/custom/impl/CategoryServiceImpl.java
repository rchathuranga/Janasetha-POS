package lk.janasetha.thogakade.service.custom.impl;

import lk.janasetha.thogakade.dto.CategoryDTO;
import lk.janasetha.thogakade.model.Category;
import lk.janasetha.thogakade.repository.DAOFactory;
import lk.janasetha.thogakade.repository.custom.CategoryDAO;
import lk.janasetha.thogakade.service.custom.CategoryService;
import lk.janasetha.thogakade.utill.SysConfig;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public List<CategoryDTO> getAllActiveCategories() throws Exception {
        List<CategoryDTO> list = new ArrayList<>();
        List<Category> all = categoryDAO.getAllByStatus(SysConfig.STATUS_ACTIVE);
        for (Category category : all) {
            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setCateId(category.getCateId());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setStatus(category.getStatus());

            list.add(categoryDTO);
        }
        return list;
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws Exception {
        List<CategoryDTO> list = new ArrayList<>();
        List<Category> all = categoryDAO.getAll();
        for (Category category : all) {
            CategoryDTO categoryDTO = new CategoryDTO();

            categoryDTO.setCateId(category.getCateId());
            categoryDTO.setDescription(category.getDescription());
            categoryDTO.setStatus(category.getStatus());

            list.add(categoryDTO);
        }
        return list;
    }

    @Override
    public int addCategory(CategoryDTO categoryDTO) throws Exception {
        Category category = new Category();
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.getStatus());
        return categoryDAO.addCategory(category);
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) throws Exception {
        Category category = new Category();
        category.setCateId(categoryDTO.getCateId());
        category.setDescription(categoryDTO.getDescription());
        category.setStatus(categoryDTO.getStatus());
        return categoryDAO.updateCategory(category);
    }

    @Override
    public CategoryDTO searchCategoryById(int id) throws Exception {
        Category search = categoryDAO.search(id);
        System.out.println("search : " + search);
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCateId(search.getCateId());
        categoryDTO.setDescription(search.getDescription());
        categoryDTO.setStatus(search.getStatus());

        return categoryDTO;
    }
}
