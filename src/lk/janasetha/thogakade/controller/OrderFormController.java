package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.*;
import lk.janasetha.thogakade.reports.Report;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.OrderService;
import lk.janasetha.thogakade.service.custom.StockService;
import lk.janasetha.thogakade.tm.ButtonTM;
import lk.janasetha.thogakade.utill.SysConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderFormController {
    /**
     * =============================== View Component Variable ===============================
     */

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
    private TableView<QueryDTO> tblItems;
    @FXML
    private TableView<QueryDTO> tblList;
    @FXML
    private TextField txtPayedAmount;
    @FXML
    private TextField lblBalance;
    @FXML
    private JFXButton btnCancelOrder;

    private StockService stockService = (StockService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STOCK);
    private OrderService orderService = (OrderService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ORDER);

    /**
     * =============================== View Selections ===============================
     */

    private OrderDTO orderDTO = new OrderDTO();
    private QueryDTO selectedItem;
    private List<QueryDTO> orderItemList = new ArrayList<>();


    /**
     * =============================== FXML Initialize ===============================
     */
    public void initialize() {
        initializeComponents();
        loadAvailableItems();
    }

    private void txtBarcodeChangeEvent(String changedValue) {
        try {
            List<QueryDTO> availableStockByBarcode;
            if (!changedValue.isEmpty()) {
                availableStockByBarcode = stockService.getAvailableStockByBarcode(changedValue);
            } else {
                availableStockByBarcode = stockService.getAvailableStock();
            }
            tblItems.setItems(FXCollections.observableList(availableStockByBarcode));
            processAfterItemLoaded(availableStockByBarcode);
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
            tblItems.setItems(FXCollections.observableList(availableStockByBarcode));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void processAfterItemLoaded(List<QueryDTO> availableStockByBarcode) {
        boolean isPreEmpty = tblItems.getItems().isEmpty();

        if (isPreEmpty || availableStockByBarcode.size() == 1) {
            tblItems.getSelectionModel().selectFirst();
            itemSelection();
        }
    }

    private void initializeComponents() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("currentQty"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("retailPrice"));


        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCount"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("sellingQty"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("sellingTotal"));
        tblList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("removeButton"));


        txtBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
            txtBarcodeChangeEvent(newValue.trim());
        });

        txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            txtSearchChangeEvent(newValue.trim());
        });


        cmbSalesType.setItems(FXCollections.observableArrayList(SysConfig.SALES_TYPE_RETAIL, SysConfig.SALES_TYPE_MID, SysConfig.SALES_TYPE_WHOLESALE));
        cmbSalesType.getSelectionModel().selectFirst();

        tblList.setItems(FXCollections.observableList(orderItemList));
    }

    public void loadAvailableItems() {
        try {
            List<QueryDTO> availableStock = stockService.getAvailableStock();
            tblItems.setItems(FXCollections.observableArrayList(availableStock));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void itemSelection() {
        QueryDTO tblItem = tblItems.getSelectionModel().getSelectedItem();
        if (tblItem != null) {
            if (tblItem.getCurrentQty() > 0) {

                this.selectedItem = tblItem.clone();
                setItemDetails();
                txtQty.requestFocus();
            } else {
                new Alert(Alert.AlertType.WARNING, "No Items in Stock for this Item").show();
                txtBarcode.requestFocus();
            }
        }
    }

    private void setItemDetails() {
        if (selectedItem != null) {
            lblDescription.setText(selectedItem.getBillDescription().replace(" ", "\n"));
            lblQtyOnHand.setText(String.valueOf(selectedItem.getCurrentQty()));

            double sellingPrice = extractSellingPriceBySalesType(selectedItem);
            selectedItem.setSellingPrice(sellingPrice);
            lblUnitPrice.setText(String.valueOf(sellingPrice));

            processSellingQty();
            double sellingTotal = sellingPrice * selectedItem.getSellingQty();
            selectedItem.setSellingTotal(sellingTotal);
            lblItemTotal.setText(String.valueOf(sellingTotal));
        }
    }

    private double extractSellingPriceBySalesType(QueryDTO queryDTO) {
        String salesType = cmbSalesType.getSelectionModel().getSelectedItem();
        if (salesType.equalsIgnoreCase(SysConfig.SALES_TYPE_MID)) {
            return queryDTO.getMidPrice();
        } else if (salesType.equalsIgnoreCase(SysConfig.SALES_TYPE_WHOLESALE)) {
            return queryDTO.getWholesalePrice();
        } else {
            return queryDTO.getRetailPrice();
        }
    }

    private void processSellingQty() {
        if (selectedItem != null) {
            String text = txtQty.getText();
            if (!text.matches("[0-9]{1,5}")) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Qty Entered");
                alert.show();
                txtQty.setText("");
                txtQty.requestFocus();
                return;
            }
            selectedItem.setSellingQty(Integer.parseInt(text));
        }
    }

    private void addToListProcess() {
        if (selectedItem != null) {
            QueryDTO already = getSelectedItemIfAlreadyOnList(selectedItem);
            if (already != null) {
                orderItemList.remove(already);
            }

            int itemCount = orderItemList.size() + 1;
            selectedItem.setItemCount(itemCount);


            ButtonTM btnRemove = new ButtonTM("Remove");
            btnRemove.setVisible(true);
            btnRemove.setOnAction(event1 -> {
                tblList.getItems().remove(btnRemove.getItem());
                loopOrderItemList();
            });
            selectedItem.setRemoveButton(btnRemove);


            orderItemList.add(selectedItem);

            loopOrderItemList();
            paidAmountEvent();
            tblList.refresh();
            txtBarcode.requestFocus();


        }
    }

    private void loopOrderItemList() {
        int itemCount = 1;
        double orderTotal = 0.0;
        QueryDTO temp = selectedItem;
        for (QueryDTO queryDTO : orderItemList) {
            selectedItem = queryDTO;
            queryDTO.setItemCount(itemCount++);

            double sellingPrice = extractSellingPriceBySalesType(selectedItem);
            double sellingTotal = sellingPrice * selectedItem.getSellingQty();
            selectedItem.setSellingPrice(sellingPrice);
            selectedItem.setSellingTotal(sellingTotal);

            orderTotal += queryDTO.getSellingTotal();
        }

        lblNoOfItems.setText(String.valueOf(orderItemList.size()));
        orderDTO.setTotal(orderTotal);
        lblOrderTotal.setText(String.valueOf(orderTotal));

        selectedItem = temp;
        tblList.refresh();
    }

    private QueryDTO getSelectedItemIfAlreadyOnList(QueryDTO itemTM) {
        for (QueryDTO item : tblList.getItems()) {
            if (item.getItemCode() == itemTM.getItemCode()) {
                return item;
            }
        }
        return null;
    }

    private void paidAmountEvent() {
        if (orderDTO != null) {
            String text = txtPayedAmount.getText();
            if (text.isEmpty()) {
                txtPayedAmount.requestFocus();
                orderDTO.setPaidAmount(0);
                return;
            }

            if (text.matches("[0-9]*.[0-9]{0,2}")) {

                double paidAmount = Double.parseDouble(text);
                orderDTO.setPaidAmount(paidAmount);
                double balance = orderDTO.getPaidAmount() - orderDTO.getTotal();
                orderDTO.setBalance(balance);
                if (orderDTO.getPaidAmount() != 0)
                    lblBalance.setText(String.valueOf(balance));
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Qty Entered");
                alert.show();
                txtPayedAmount.setText("00");
                txtPayedAmount.requestFocus();
            }
        }
    }

    private void processPlaceOrder() {

        orderDTO.setSalesType(cmbSalesType.getValue());
        List<OrderDetailDTO> items = new ArrayList<>();

        for (int i = 0; i < tblList.getItems().size(); i++) {
            QueryDTO itemTM = tblList.getItems().get(i);
            OrderDetailDTO odDTO = new OrderDetailDTO(new BatchDetailDTO(0, new ItemDTO(itemTM.getItemCode())), itemTM.getSellingQty(), itemTM.getSellingPrice(), itemTM.getSellingTotal());
            items.add(odDTO);
        }

        CompleteOrderDTO completeOrderDTO = new CompleteOrderDTO();
        completeOrderDTO.setOrder(orderDTO);
        completeOrderDTO.setItems(items);

        try {

            System.out.println("000000000000000000000000  000000000000000000000000");
            int orderId = orderService.addOrder(completeOrderDTO);
            if (orderId > 0) {
                prepareForNewOrder(true);
                Report.getInstance().generateOrderBillReport(completeOrderDTO, orderItemList);

                System.out.println("000000000000000000000000  000000000000000000000000");

                System.out.println("orderbo-item_list : " + orderService.getItem_List());

                new Alert(Alert.AlertType.INFORMATION, "Order " + orderId + " Placed Successfully").show();
            } else {
                System.out.println("Order Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepareForNewOrder(boolean isForceClear /** true - doesn't show alert */) {
        boolean isButtonClick = false;

        if (!isForceClear) {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.WARNING, "Are You Sure to Cancel Order", ButtonType.OK).showAndWait();
            isButtonClick = buttonType.isPresent() && buttonType.get().getButtonData().isDefaultButton();
        }

        if (!isButtonClick) {
            orderDTO = new OrderDTO();

            orderItemList.clear();
            tblList.refresh();
            orderItemList = new ArrayList<>();

            loadAvailableItems();
            tblItems.getSelectionModel().selectFirst();
            itemSelection();

            txtBarcode.setText("");
            txtDescription.setText("");
            txtQty.setText("1");
            lblNoOfItems.setText("0");
            lblOrderTotal.setText("0.0");
            txtPayedAmount.setText("0.0");
            lblBalance.setText("0.0");

            txtBarcode.requestFocus();
        }
    }


    /**
     * =============================== FXML Events ===============================
     */

    @FXML
    void tblItemClickEvent(MouseEvent event) {
        itemSelection();
    }

    @FXML
    void tblItemKeyEvent(KeyEvent event) {
        itemSelection();
    }

    @FXML
    void btnAddToListAction(ActionEvent event) {
        addToListProcess();
    }

    @FXML
    void btnCancelOrderAction(ActionEvent event) {
        prepareForNewOrder(false);
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        processPlaceOrder();
    }

    @FXML
    void cmbSalesTypeAction(ActionEvent event) {
        setItemDetails();
        loopOrderItemList();
        paidAmountEvent();
    }

    @FXML
    void lblRuMouseClick(MouseEvent event) {
        txtPayedAmount.requestFocus();
    }

    @FXML
    void txtPayedAmountAction(ActionEvent event) {
        paidAmountEvent();
    }

    @FXML
    void txtPayedAmountKeyPress(KeyEvent event) {
        paidAmountEvent();
    }

    @FXML
    void txtQtyAction(ActionEvent event) {
        processSellingQty();
        setItemDetails();
        addToListProcess();
    }

    @FXML
    void txtQtyKeyPress(KeyEvent event) {
        String text = txtQty.getText();
        if (text.equalsIgnoreCase("")) return;

        if (!text.matches("[0-9]{1,5}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Qty Entered");
            alert.show();
            txtQty.setText("1");
            txtQty.requestFocus();
            return;
        }

        processSellingQty();
        setItemDetails();
    }

    @FXML
    void btnTabAddAction(ActionEvent event) throws IOException {
        Parent parent = btnTabAdd.getParent().getParent().getParent();
        TabPane pane = (TabPane) parent;

        ObservableList<Tab> tabs = pane.getTabs();
        if (tabs.size() < SysConfig.ORDER_FORM_TAB_MAX_LIMIT) {
            Tab tab = new Tab("Form " + (tabs.size() + 1));
            tab.setId("tbForm" + (tabs.size() + 1));
            tab.setContent(parent);

            Parent root = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/" + "OrderForm" + ".fxml"));
            tab.setContent(root);

            tabs.add(tab);
        }
    }

    @FXML
    void btnTabRemoveAction(ActionEvent event) {
        Parent parent = btnTabAdd.getParent().getParent().getParent();
        TabPane pane = (TabPane) parent;

        Tab selectedTab = pane.getSelectionModel().getSelectedItem();
        if (pane.getTabs().size() != 1) {
            pane.getTabs().remove(selectedTab);
        }
    }
}
