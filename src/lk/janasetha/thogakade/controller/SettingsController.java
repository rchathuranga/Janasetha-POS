package lk.janasetha.thogakade.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.CategoryDTO;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.CategoryService;
import lk.janasetha.thogakade.utill.SysConfig;

import java.util.List;

public class SettingsController {
    @FXML
    private TextField txtCategory;
    @FXML
    private ComboBox<String> cmbStatus;
    @FXML
    private TableView<CategoryDTO> tblCategory;
    @FXML
    private TextField txtSearchCategory;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnAdd;

    private CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.CATEGORY);

    public void initialize() {
        tblCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cateId"));
        tblCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblCategory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));

        cmbStatus.setItems(FXCollections.observableArrayList("Active", "Inactive"));
        cmbStatus.getSelectionModel().selectFirst();
        loadAllCategories();
    }

    private void loadAllCategories() {
        try {
            List<CategoryDTO> allCategories = categoryService.getAllCategories();
            System.out.println(allCategories);
            tblCategory.setItems(FXCollections.observableList(allCategories));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tblCategoryClickEvent(MouseEvent event) {
        CategoryDTO selectedItem = tblCategory.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            txtCategory.setText(selectedItem.getDescription());
            if (selectedItem.getStatus().equalsIgnoreCase(SysConfig.STATUS_ACTIVE)) {
                cmbStatus.getSelectionModel().select(SysConfig.STATUS_VIEW_ACTIVE);
            } else {
                cmbStatus.getSelectionModel().select(SysConfig.STATUS_VIEW_INACTIVE);
            }
        }
    }

    @FXML
    void cmbStatus(ActionEvent event) {

    }

    @FXML
    void txtCategoryAction(ActionEvent event) {

    }

    @FXML
    void btnAddAction(ActionEvent event) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setDescription(txtCategory.getText());
        categoryDTO.setStatus(cmbStatus.getSelectionModel().getSelectedItem().equalsIgnoreCase(SysConfig.STATUS_VIEW_ACTIVE) ? SysConfig.STATUS_ACTIVE : SysConfig.STATUS_INACTIVE);
        try {
            categoryService.addCategory(categoryDTO);
            loadAllCategories();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        try {
            CategoryDTO selectedItem = tblCategory.getSelectionModel().getSelectedItem();
            System.out.println("s : " + selectedItem);
            if (selectedItem != null) {
                selectedItem.setDescription(txtCategory.getText());
                selectedItem.setStatus(cmbStatus.getSelectionModel().getSelectedItem().equalsIgnoreCase("Active") ? "ACT" : "DEACT");

                categoryService.updateCategory(selectedItem);
                loadAllCategories();
            } else {
                new Alert(Alert.AlertType.WARNING, "Please select category from table to update").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtSearchCategory(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtSearchCategory.getText());
            CategoryDTO categoryDTO = categoryService.searchCategoryById(id);
            txtCategory.setText(categoryDTO.getDescription());
            cmbStatus.getSelectionModel().select(categoryDTO.getStatus().equalsIgnoreCase("ACT") ? "Active" : "Inactive");
            tblCategory.setItems(FXCollections.observableArrayList(categoryDTO));
            tblCategory.getSelectionModel().selectFirst();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

