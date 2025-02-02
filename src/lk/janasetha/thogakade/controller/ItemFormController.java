package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.*;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.CategoryService;
import lk.janasetha.thogakade.service.custom.StockService;
import lk.janasetha.thogakade.service.custom.SupplierService;
import lk.janasetha.thogakade.tm.BatchDetailTM;
import lk.janasetha.thogakade.utill.SysConfig;
import org.controlsfx.control.textfield.TextFields;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemFormController {
    @FXML
    private TextField txtBarcode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TableView<ItemDTO> tblItems;
    @FXML
    private TextField txtItemDescription;
    @FXML
    private TextField txtBillDescription;
    @FXML
    private TextField txtRetailPrice;
    @FXML
    private TextField txtMidPrice;
    @FXML
    private TextField txtWholesalePrice;
    @FXML
    private TextField txtBuyingPrice;
    @FXML
    private DatePicker dtpManuDate;
    @FXML
    private DatePicker dtpExDate;
    @FXML
    private TextField txtQty;
    @FXML
    private JFXButton btnAddToList;
    @FXML
    private TextField txtItemBarcode;
    @FXML
    private ComboBox<CategoryDTO> cmbCategory;
    @FXML
    private TableView<BatchDetailTM> tblBatchDetail;
    @FXML
    private TextField txtSupplier;
    @FXML
    private TextField txtInvoiceNo;
    @FXML
    private JFXButton btnAddStock;
    @FXML
    private TextField lblBillTotal;
    @FXML
    private JFXButton btnClearAll;
    @FXML
    private JFXButton btnClearFields;
    @FXML
    private ComboBox<String> cmbMeasureUnit;

    private StockService stockService = (StockService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STOCK);
    private CategoryService categoryService = (CategoryService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.CATEGORY);
    private SupplierService supplierService = (SupplierService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.SUPPLIER);


    private ItemDTO selectedItem = null;
    private QueryDTO selectedQueryItem = null;
    private List<BatchDetailDTO> batchDetailDTOList = new ArrayList<>();
    private int listNumber = 0;


    private void createdEvent() {
        dtpManuDate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (dtpManuDate.focusedProperty().get()) dtpManuDate.show();
        });
        dtpExDate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (dtpExDate.focusedProperty().get()) dtpExDate.show();
        });

        txtBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
            txtBarcodeChangeEvent(newValue.trim());
        });
        txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            txtSearchChangeEvent(newValue.trim());
        });
    }

    public void initialize() {
        createdEvent();
        initializedComponent();


//        new Thread(() -> {
            try {
                List<SupplierDTO> allSuppliers = supplierService.getAllSuppliers();
                TextFields.bindAutoCompletion(txtSupplier, allSuppliers);

                List<ItemDTO> allItems = stockService.getAllItems();
                tblItems.setItems(FXCollections.observableArrayList(allItems));

                List<CategoryDTO> categories = categoryService.getAllActiveCategories();
                cmbCategory.setItems(FXCollections.observableList(categories));
                cmbCategory.getSelectionModel().selectFirst();

            } catch (Exception e) {
                e.printStackTrace();
            }
//        }).start();


    }

    private void initializedComponent() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        tblBatchDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("listNo"));
        tblBatchDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tblBatchDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBatchDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        tblBatchDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("midPrice"));
        tblBatchDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("wholesalePrice"));
        tblBatchDetail.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblBatchDetail.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("totalCost"));

        cmbMeasureUnit.setItems(FXCollections.observableArrayList(SysConfig.MEASURE_UNIT_PIECES, SysConfig.MEASURE_UNIT_WEIGHT));
        cmbMeasureUnit.getSelectionModel().selectFirst();
    }

    @FXML
    void btnClearAllAction(ActionEvent event) {
        selectedItem=null;
        selectedQueryItem=null;

        tblBatchDetail.setItems(FXCollections.observableList(new ArrayList<>()));
        batchDetailDTOList = new ArrayList<>();
        tblBatchDetail.refresh();

        lblBillTotal.setText("0.0");
        txtSupplier.setText("");
        txtInvoiceNo.setText("");

        clearTextItemArea();
    }


    @FXML
    void btnAddStockAction(ActionEvent event) {
        listNumber = 0;

        String supplier = txtSupplier.getText();
        String invoiceNo = txtInvoiceNo.getText();

        if (supplier.equals("") & invoiceNo.equals("")) {
            new Alert(Alert.AlertType.WARNING, "Enter Supplier and Invoice No").show();
        } else {

            BatchDTO batch = new BatchDTO(supplier, null, null, "ACT", invoiceNo, Double.parseDouble(lblBillTotal.getText()));

            CompleteStockDTO stockDTO = new CompleteStockDTO();
            stockDTO.setBatch(batch);
            stockDTO.setBatchDetail(batchDetailDTOList);

            try {
                boolean isStockAdded = stockService.addNewStock(stockDTO);
                if (isStockAdded) {
                    new Alert(Alert.AlertType.INFORMATION, "Stocks Added Successfully").show();
                    btnClearAllAction(null);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Stocks Added Failed").show();
                }

                List<ItemDTO> allItems = stockService.getAllItems();
                tblItems.setItems(FXCollections.observableArrayList(allItems));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private boolean validate() {
        boolean r = txtRetailPrice.getText().equals("");
        boolean w = txtWholesalePrice.getText().equals("");
        boolean m = txtMidPrice.getText().equals("");
        boolean b = txtBuyingPrice.getText().equals("");

        return (r & w & m & b);
    }

    @FXML
    void btnAddToListAction(ActionEvent event) {
        LocalDate manuDateValue = dtpManuDate.getValue();
        Date manu = (manuDateValue != null) ? Date.valueOf(manuDateValue) : null;
        LocalDate exDateValue = dtpExDate.getValue();
        Date ex = (exDateValue != null) ? Date.valueOf(exDateValue) : null;
        int qty = Integer.parseInt(txtQty.getText());

        double buying = 0, retail = 0, wholesale = 0, mid = 0;

        if (!validate()) {
            buying = Double.parseDouble(txtBuyingPrice.getText());
            retail = Double.parseDouble(txtRetailPrice.getText());
            wholesale = Double.parseDouble(txtWholesalePrice.getText());
            mid = Double.parseDouble(txtMidPrice.getText());
        } else {
            retail = selectedQueryItem.getRetailPrice();
            mid = selectedQueryItem.getMidPrice();
            wholesale = selectedQueryItem.getWholesalePrice();
            buying = selectedQueryItem.getBuyingPrice();
        }

        if (selectedItem == null || !selectedItem.getDescription().equals(txtDescription.getText().trim())) {
            if (!txtItemDescription.getText().equals("")) {

                String description = txtItemDescription.getText();
                String billDescription = txtBillDescription.getText();

//                Alert barcodeAlert = new Alert(Alert.AlertType.INFORMATION, "Enter Product Barcode");
//                barcodeAlert.  todo set text field to alert

                selectedItem = new ItemDTO(description, billDescription, "ACT", cmbCategory.getValue(), retail, cmbMeasureUnit.getValue(), txtItemBarcode.getText());
            }
        }

        BatchDetailDTO batchDetailDTO = new BatchDetailDTO(0, selectedItem, qty, qty, retail, mid, wholesale, buying, manu, ex);
        batchDetailDTO.setTotalCost(qty * buying);


        long count = tblBatchDetail.getItems().stream().filter(bd -> bd.getItemCode() == selectedItem.getItemCode()).count();
        if (count == 0) {

            System.out.println("batchDetailDTO : " + batchDetailDTO);
            batchDetailDTOList.add(batchDetailDTO);
            tblBatchDetail.getItems().add(new BatchDetailTM(++listNumber, batchDetailDTO));
            tblBatchDetail.refresh();

        } else {
            new Alert(Alert.AlertType.WARNING, "Item Already Added").show();
        }

        calculateBillTotal();
        selectedItem = null;
        selectedQueryItem = null;
        clearTextItemArea();
    }

    @FXML
    void tblListClickEvent(MouseEvent event) {
        tableActionEvent();
    }

    @FXML
    void tblListKeyEvent(KeyEvent event) {
        tableActionEvent();
    }

    @FXML
    void txtQtyActionEvent(ActionEvent event) {
        btnAddToList.fire();
    }

    private void txtBarcodeChangeEvent(String text) {
        try {
            List<ItemDTO> availableStockByBarcode;
            if (!text.isEmpty()) {
                availableStockByBarcode = stockService.getAllItemsByBarcode(text);
            } else {
                availableStockByBarcode = stockService.getAllItems();
            }
            tblItems.setItems(FXCollections.observableList(availableStockByBarcode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void txtSearchChangeEvent(String changedValue) {
        try {
            List<ItemDTO> availableStockByBarcode;
            if (!changedValue.isEmpty()) {
                availableStockByBarcode = stockService.getAllItemsByDescription(changedValue);
            } else {
                availableStockByBarcode = stockService.getAllItems();
            }
            tblItems.setItems(FXCollections.observableList(availableStockByBarcode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void tableActionEvent() {
        clearTextItemArea();

        selectedItem = tblItems.getSelectionModel().getSelectedItem();
        txtItemDescription.setPromptText(selectedItem.getDescription());
        txtBillDescription.setPromptText(selectedItem.getBillDescription());
        txtItemBarcode.setText(selectedItem.getBarcode());
        cmbCategory.getSelectionModel().select(selectedItem.getCategory());
        cmbMeasureUnit.getSelectionModel().select(selectedItem.getMeasureUnit());

        try {
            List<QueryDTO> batchDetailsByItemCode = stockService.getLatestBatchDetailsByItemCode(selectedItem.getItemCode());


            if (batchDetailsByItemCode != null & !batchDetailsByItemCode.isEmpty()) {
                selectedQueryItem = batchDetailsByItemCode.get(0);

                txtRetailPrice.setPromptText(String.valueOf(selectedQueryItem.getRetailPrice()));
                txtWholesalePrice.setPromptText(String.valueOf(selectedQueryItem.getWholesalePrice()));
                txtMidPrice.setPromptText(String.valueOf(selectedQueryItem.getMidPrice()));
                txtBuyingPrice.setPromptText(String.valueOf(selectedQueryItem.getBuyingPrice()));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void calculateBillTotal() {
        double billTotal = tblBatchDetail.getItems().stream().mapToDouble(BatchDetailTM::getTotalCost).sum();
        lblBillTotal.setText(String.valueOf(billTotal));
    }

    private void clearTextItemArea() {
        txtItemDescription.setText("");
        txtItemDescription.setPromptText("");

        txtBillDescription.setText("");
        txtBillDescription.setPromptText("");

        txtRetailPrice.setText("");
        txtRetailPrice.setPromptText("");

        txtMidPrice.setText("");
        txtMidPrice.setPromptText("");

        txtWholesalePrice.setText("");
        txtWholesalePrice.setPromptText("");

        txtBuyingPrice.setText("");
        txtBuyingPrice.setPromptText("");

        dtpManuDate.setValue(null);
        dtpManuDate.setPromptText("");

        dtpExDate.setValue(null);
        dtpExDate.setPromptText("");

        txtQty.setText("1");
        txtQty.setPromptText("");

        txtItemBarcode.setText("");

        cmbCategory.getSelectionModel().selectFirst();
        cmbMeasureUnit.getSelectionModel().selectFirst();
    }

    @FXML
    void btnClearFields(ActionEvent event) {
        selectedItem = null;
        clearTextItemArea();
    }
}
