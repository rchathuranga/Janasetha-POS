package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.QueryDTO;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.OrderService;
import lk.janasetha.thogakade.service.custom.StockService;
import lk.janasetha.thogakade.tm.ItemTM;
import lk.janasetha.thogakade.utill.SysConfig;

import java.util.ArrayList;
import java.util.List;

public class OrderFormController {
    @FXML
    private Button btnTabAdd;
    @FXML
    private Button btnTabRemove;
    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private TextField lblQtyOnHand;
    @FXML
    private TextField lblUnitPrice;
    @FXML
    private TextArea lblDescription;
    @FXML
    private TextField lblItemTotal;
    @FXML
    private ComboBox<String> cmbSalesType;
    @FXML
    private TextField txtQty;
    @FXML
    private JFXButton btnAddToList;
    @FXML
    private TextField txtBarcode;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField lblNoOfItems;
    @FXML
    private TextField lblOrderTotal;
    @FXML
    private TableView<ItemTM> tblItems;
    @FXML
    private TableView<ItemTM> tblList;
    @FXML
    private TextField txtPayedAmount;
    @FXML
    private TextField lblBalance;
    @FXML
    private JFXButton btnCancelOrder;

    private StockService stockService = (StockService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STOCK);
    private OrderService orderService = (OrderService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ORDER);

    /**
     * ===================== =====================
     */

    private ItemTM selectedItem;

    //Order Common Properties
    private String orderSalesType;

    //Single Item Properties
    private double itemUnitPrice;
    private int itemQty;
    private double itemTotal;

    /** ===================== =====================  */


    /**
     * FXML initialize method
     */
    public void initialize() {
        try {
            new Thread(() -> {
                initializeComponents();
                txtBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
                    txtBarcodeChangeEvent(newValue.trim());
                });
                txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
                    txtSearchChangeEvent(newValue.trim());
                });
            }).start();
            loadTableFromQueryDTO(stockService.getAvailableStock());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnAddToListAction(ActionEvent event) {

    }

    @FXML
    void btnCancelOrderAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {

    }

    @FXML
    void cmbSalesTypeAction(ActionEvent event) {
        orderSalesType = cmbSalesType.getSelectionModel().getSelectedItem();

    }

    @FXML
    void lblRuMouseClick(MouseEvent event) {

    }

    @FXML
    void tblListClickEvent(MouseEvent event) {

    }

    @FXML
    void tblListKeyEvent(KeyEvent event) {

    }

    @FXML
    void txtPayedAmountAction(ActionEvent event) {

    }

    @FXML
    void txtPayedAmountKeyPress(KeyEvent event) {

    }

    @FXML
    void txtQtyAction(ActionEvent event) {

    }

    @FXML
    void txtQtyKeyPress(KeyEvent event) {

    }

    /** ===================== =====================  */

    private void initializeComponents() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("midPrice"));
        tblItems.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("wholesalePrice"));


        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("listNumber"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sellingQty"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("sellingTotal"));
        tblList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("removeButton"));

        cmbSalesType.setItems(FXCollections.observableArrayList(SysConfig.SALES_TYPE_RETAIL, SysConfig.SALES_TYPE_MID, SysConfig.SALES_TYPE_WHOLESALE));
        cmbSalesType.getSelectionModel().selectFirst();
    }

    private void txtBarcodeChangeEvent(String changedValue) {
        try {
            List<QueryDTO> availableStockByBarcode;
            if (!changedValue.isEmpty()) {
                availableStockByBarcode = stockService.getAvailableStockByBarcode(changedValue);
            } else {
                availableStockByBarcode = stockService.getAvailableStock();
            }
            loadTableFromQueryDTO(availableStockByBarcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void txtSearchChangeEvent(String changedValue) {
        try {
            List<QueryDTO> availableStockByBarcode;
            if (!changedValue.isEmpty()) {
                availableStockByBarcode = stockService.getAvailableStockByDescription(changedValue);
            } else {
                availableStockByBarcode = stockService.getAvailableStock();
            }
            loadTableFromQueryDTO(availableStockByBarcode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void tableClickEvent() {
        this.selectedItem = tblItems.getSelectionModel().getSelectedItem();
        if (this.selectedItem != null) {
            setLblItemDescription(this.selectedItem.getBillDescription());
            lblQtyOnHand.setText(String.valueOf(this.selectedItem.getQty()));
            lblUnitPrice.setText(String.valueOf(extractSellingPriceBySalesType(selectedItem)));
            setItemUnitPrice(calculateItemTotal(selectedItem));
            txtQty.requestFocus();
        }
    }

    private double extractSellingPriceBySalesType(ItemTM itemTM) {
        double price;
        if (orderSalesType.equalsIgnoreCase(SysConfig.SALES_TYPE_MID)) {
            price = itemTM.getMidPrice();
        } else if (orderSalesType.equalsIgnoreCase(SysConfig.SALES_TYPE_WHOLESALE)) {
            price = itemTM.getWholesalePrice();
        } else {
            price = itemTM.getRetailPrice();
        }
        return price;
    }

    private void loadTableFromQueryDTO(List<QueryDTO> list) {
        List<ItemTM> tableModel = new ArrayList<>();
        list.forEach(q -> {
            ItemTM itemTM = new ItemTM();

            itemTM.setItemCode(q.getItemCode());
            itemTM.setDescription(q.getDescription());
            itemTM.setBillDescription(q.getBilDescription());
            itemTM.setQty(q.getCurrentQty());
            itemTM.setRetailPrice(q.getRetailPrice());
            itemTM.setMidPrice(q.getMidPrice());
            itemTM.setWholesalePrice(q.getWholesalePrice());

            tableModel.add(itemTM);
        });

        boolean isPreEmpty = tblItems.getItems().isEmpty();
        tblItems.setItems(FXCollections.observableList(tableModel));

        //if there is minimum one object in item list
        if (isPreEmpty || tableModel.size() == 1) {
            tblItems.getSelectionModel().selectFirst();
            tableClickEvent();
        }
    }


    private void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
        setItemTotal(calculateItemTotal(selectedItem));
    }

    private double calculateItemTotal(ItemTM selectedItem) {
        return extractSellingPriceBySalesType(selectedItem) * itemQty;
    }

    private void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
        lblItemTotal.setText(String.valueOf(itemTotal));
    }

    /**
     * ==================================== Setter for View Management ====================================
     */

    private void setLblItemDescription(String value) {
        value = value.replace(" ", "\n");
        lblDescription.setText(value);
    }


    /**
     * ==================================== Tab Management ====================================
     */

    @FXML
    void btnTabAddAction(ActionEvent event) {

    }

    @FXML void btnTabRemoveAction(ActionEvent event) {

    }
}

