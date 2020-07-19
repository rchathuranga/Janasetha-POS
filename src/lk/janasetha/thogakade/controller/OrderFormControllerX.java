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
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.OrderService;
import lk.janasetha.thogakade.service.custom.StockService;
import lk.janasetha.thogakade.tm.ButtonTM;
import lk.janasetha.thogakade.tm.ItemTM;
import lk.janasetha.thogakade.utill.SysConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderFormControllerX {
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


    /**
     * -------------------------------------------------------------------------------------------------------------
     */

    private StockService stockBO = (StockService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.STOCK);
    private OrderService orderBO = (OrderService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ORDER);
    /**
     * -------------------------------------------------------------------------------------------------------------
     */

    private int noOfItems = 0;
    private int priceTag = 0;                                               // 0 - retail | 1 - mid | 2 - wholesale
    private double orderTotal = 0.0;
    private OrderDTO order = new OrderDTO();
    private List<ItemTM> itemTMTableList = new ArrayList();
    private double paidAmount = 0.0;
    private double balance = 0.0;

    /**
     * -------------------------------------------------------------------------------------------------------------
     */

    private double itemUnitPrice = 0.0;
    private int itemQty = 1;
    private double itemTotal = 0.0;
    private ItemTM selectedItem = null;
    private ArrayList<Integer> tabNumbered = new ArrayList<>();

    /**
     * -------------------------------------------------------------------------------------------------------------
     */


    public void initialize() {
        new Thread(() -> {
            initializeComponents();
            txtBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
                txtBarcodeChangeEvent(newValue.trim());
            });

            txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
                txtSearchChangeEvent(newValue.trim());
            });
        }).start();

        try {
            loadTableFromQueryDTO(stockBO.getAvailableStock());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * -------------------------------------------------------------------------------------------------------------
     */

    @FXML
    void lblRuMouseClick(MouseEvent event) {
        txtPayedAmount.requestFocus();
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
    void tblListClickEvent(MouseEvent event) {
        selectedItem = tblItems.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String description = selectedItem.getBillDescription().replace(" ", "\n");
            lblDescription.setText(description);
            lblQtyOnHand.setText(String.valueOf(selectedItem.getQty()));
            lblUnitPrice.setText(String.valueOf(extractSellingPriceByPriceTag(selectedItem)));
            setItemUnitPrice(calculateItemTotal(selectedItem));
            txtQty.requestFocus();
        }
    }

    @FXML
    void tblListKeyEvent(KeyEvent event) {
        selectedItem = tblItems.getSelectionModel().getSelectedItem();

        String description = selectedItem.getBillDescription().replace(" ", "\n");
        lblDescription.setText(description);
        lblQtyOnHand.setText(String.valueOf(selectedItem.getQty()));
        lblUnitPrice.setText(String.valueOf(extractSellingPriceByPriceTag(selectedItem)));
        setItemUnitPrice(calculateItemTotal(selectedItem));
    }

    @FXML
    void cmbSalesTypeAction(ActionEvent event) {
        setPriceTag(cmbSalesType.getSelectionModel().getSelectedIndex());
        calculateOrderTotal();
    }

    @FXML
    void btnTabRemoveAction(ActionEvent event) {

        Parent parent = btnTabAdd.getParent().getParent().getParent();
        TabPane pane = (TabPane) parent;

        Tab selectedItem = pane.getSelectionModel().getSelectedItem();
        if (pane.getTabs().size() != 1) {
            pane.getTabs().remove(selectedItem);
        }
    }

    @FXML
    void txtQtyAction(ActionEvent event) {
        btnAddToList.fire();
    }

    @FXML
    void btnAddToListAction(ActionEvent event) {
        ItemTM onList = getSelectedItemIfAlreadyOnList(selectedItem);

        if (onList == null) {
            noOfItems++;
            setNoOfItems(noOfItems);
            selectedItem.setListNumber(noOfItems);
            selectedItem.setSellingPrice(extractSellingPriceByPriceTag(selectedItem));
            selectedItem.setSellingQty(itemQty);
            selectedItem.setSellingTotal(calculateItemTotal(selectedItem));

            ButtonTM btnRemove = new ButtonTM("Remove");
            btnRemove.setVisible(true);
            btnRemove.setOnAction(event1 -> {
                tblList.getItems().remove(btnRemove.getItem());

                setNoOfItems(tblList.getItems().size());

                for (int i = 0; i < tblList.getItems().size(); i++) {
                    tblList.getItems().get(i).setListNumber(i + 1);
                }
                tblList.refresh();

                calculateOrderTotal();
                setBalance();
            });

            selectedItem.setRemoveButton(btnRemove);
            itemTMTableList.add(selectedItem);


        } else {
            if (onList.getSellingQty() != itemQty) {
                onList.setSellingQty(itemQty);
            } else {
                new Alert(Alert.AlertType.WARNING, "Already Set the Value").show();
            }
            onList.setSellingTotal(calculateItemTotal(onList));
            tblList.refresh();

        }
        tblList.setItems(FXCollections.observableList(itemTMTableList));
        calculateOrderTotal();

        txtQty.setText("1");
        txtBarcode.requestFocus();
    }

    @FXML
    void txtQtyKeyPress(KeyEvent event) {
        String text = txtQty.getText();
        if (text.equalsIgnoreCase("")) text = "1";

        if (text.matches("[0-9]{1,5}")) {
            itemQty = Integer.parseInt(text);
            setItemTotal(calculateItemTotal(selectedItem));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Qty Entered");
            alert.show();
            txtQty.setText("");
            txtQty.requestFocus();
        }
    }

    @FXML
    void txtPayedAmountAction(ActionEvent event) {
        String text = txtPayedAmount.getText();
        this.paidAmount = Double.parseDouble(text);
        setBalance();
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        processPlaceOrder();
    }

    @FXML
    void txtPayedAmountKeyPress(KeyEvent event) {
        String text = txtPayedAmount.getText();
        this.paidAmount = Double.parseDouble(text);
        setBalance();
    }


    @FXML
    void btnCancelOrderAction(ActionEvent event) {

    }

    /**
     * -------------------------------------------------------------------------------------------------------------
     */

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
                availableStockByBarcode = stockBO.getAvailableStockByBarcode(changedValue);
            } else {
                availableStockByBarcode = stockBO.getAvailableStock();
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
                availableStockByBarcode = stockBO.getAvailableStockByDescription(changedValue);
            } else {
                availableStockByBarcode = stockBO.getAvailableStock();
            }
            loadTableFromQueryDTO(availableStockByBarcode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double calculateItemTotal(ItemTM selectedItem) {
        return extractSellingPriceByPriceTag(selectedItem) * itemQty;
    }

    private double extractSellingPriceByPriceTag(ItemTM itemTM) {
        double price;
        if (priceTag == 1) {
            price = itemTM.getMidPrice();
        } else if (priceTag == 2) {
            price = itemTM.getWholesalePrice();
        } else {
            price = itemTM.getRetailPrice();
        }
        return price;
    }

    private void refreshSellingPriceInTable2List() {
        tblList.getItems().forEach(itemTM -> {
            itemTM.setSellingPrice(extractSellingPriceByPriceTag(itemTM));
            itemTM.setSellingTotal(calculateItemTotal(itemTM));
        });
        tblList.refresh();
    }

    private ItemTM getSelectedItemIfAlreadyOnList(ItemTM itemTM) {
        for (ItemTM item : tblList.getItems()) {
            if (item.getItemCode() == itemTM.getItemCode()) {
                return item;
            }
        }
        return null;
    }

    private void calculateOrderTotal() {
        orderTotal = 0;
        for (ItemTM itemTM : tblList.getItems()) {
            orderTotal += itemTM.getSellingTotal();
        }
        setOrderTotal(orderTotal);
    }

    private void loadTableFromQueryDTO(List<QueryDTO> list) {
        List<ItemTM> tableModel = new ArrayList<>();
        list.forEach(q -> {
            ItemTM itemTM = new ItemTM(q.getItemCode(), q.getDescription(), q.getCurrentQty(), q.getRetailPrice(), q.getMidPrice(), q.getWholesalePrice());
//            System.out.println(q.getBilDescription());
            itemTM.setBillDescription(q.getBilDescription());
            tableModel.add(itemTM);
        });

        boolean isPreEmpty = tblItems.getItems().isEmpty();

        tblItems.setItems(FXCollections.observableList(tableModel));

        if (isPreEmpty || tableModel.size() == 1) {
            tblItems.getSelectionModel().selectFirst();
            tblListClickEvent(null);
        }
    }

    /**
     * -------------------------------------------------------------------------------------------------------------
     */


    private void setPriceTag(int priceTag) {
        this.priceTag = priceTag;
        lblUnitPrice.setText(String.valueOf(extractSellingPriceByPriceTag(selectedItem)));
        refreshSellingPriceInTable2List();
        setItemUnitPrice(calculateItemTotal(selectedItem));
    }

    private void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
        setItemTotal(calculateItemTotal(selectedItem));
    }

    private void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
        lblItemTotal.setText(String.valueOf(itemTotal));
    }

    private void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
        lblNoOfItems.setText(String.valueOf(noOfItems));
    }

    private void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
        lblOrderTotal.setText(String.valueOf(orderTotal));
        setPaidAmount(orderTotal);
    }

    private void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
        txtPayedAmount.setText(String.valueOf(paidAmount));
        setBalance();
    }

    private void processPlaceOrder() {
        new Thread(() -> {
            OrderDTO orderDTO = new OrderDTO("B6NEW", cmbSalesType.getValue(), paidAmount, balance, orderTotal);
            List<OrderDetailDTO> items = new ArrayList<>();

            for (int i = 0; i < tblList.getItems().size(); i++) {
                ItemTM itemTM = tblList.getItems().get(i);
                OrderDetailDTO rawRice = new OrderDetailDTO(new BatchDetailDTO(0, new ItemDTO(itemTM.getItemCode())), itemTM.getSellingQty(), itemTM.getSellingPrice(), itemTM.getSellingTotal());
                items.add(rawRice);
            }

            CompleteOrderDTO completeOrderDTO = new CompleteOrderDTO();
            completeOrderDTO.setOrder(orderDTO);
            completeOrderDTO.setItems(items);

            try {

                System.out.println("000000000000000000000000  000000000000000000000000");
                orderBO.addOrder(completeOrderDTO);
                System.out.println("000000000000000000000000  000000000000000000000000");

                System.out.println("orderbo-item_list : " + orderBO.getItem_List());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void setBalance() {
        this.balance = paidAmount - orderTotal;
        lblBalance.setText(String.valueOf(balance));
    }

    private int getNextNumber() {
        Parent parent = btnTabAdd.getParent().getParent().getParent();
        TabPane pane = (TabPane) parent;
        ObservableList<Tab> tabs = pane.getTabs();

        for (int i = 0; i < 8; i++) {
            int tabId = Integer.parseInt(tabs.get(i).getId().split("tbForm")[1]);
            if (tabId != i) {
                return i;
            }
        }

        return 0;
    }

    @FXML
    void btnClearFields(ActionEvent event) {
        selectedItem = null;
    }
}

