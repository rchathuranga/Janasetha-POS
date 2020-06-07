package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class OrderFormController {
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnRemove;
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
    private ComboBox<String> cmbPriceTag;
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

    /**
     * -------------------------------------------------------------------------------------------------------------
     */


    public void initialize() {

        initializeComponents();
        txtBarcode.textProperty().addListener((observable, oldValue, newValue) -> {
            txtBarcodeChangeEvent(newValue.trim());
        });
        txtDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            txtSearchChangeEvent(newValue.trim());
        });

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
    void btnAddAction(ActionEvent event) throws IOException {

        Parent parent = btnAdd.getParent().getParent().getParent();
        TabPane pane = (TabPane) parent;

        ObservableList<Tab> tabs = pane.getTabs();

        Tab tab = new Tab("Form " + (tabs.size() + 1));
        tab.setId("tbForm" + (tabs.size() + 1));
        tab.setContent(parent);

        Parent root = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/" + "OrderForm" + ".fxml"));
        tab.setContent(root);


        tabs.add(tab);


    }

    @FXML
    void btnRemoveAction(ActionEvent event) {

        Parent parent = btnAdd.getParent().getParent().getParent();
        TabPane pane = (TabPane) parent;

        Tab selectedItem = pane.getSelectionModel().getSelectedItem();
        if (pane.getTabs().size() != 1) {
            pane.getTabs().remove(selectedItem);
        }
    }

    @FXML
    void tblListClickEvent(MouseEvent event) {
        selectedItem = tblItems.getSelectionModel().getSelectedItem();

        String description = selectedItem.getBillDescription().replace(" ", "\n");
        lblDescription.setText(description);
        lblQtyOnHand.setText(String.valueOf(selectedItem.getQty()));
        lblUnitPrice.setText(String.valueOf(extractSellingPriceByPriceTag(selectedItem)));
        setItemUnitPrice(calculateItemTotal(selectedItem));
        txtQty.requestFocus();
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
    void cmbPriceTagAction(ActionEvent event) {
        setPriceTag(cmbPriceTag.getSelectionModel().getSelectedIndex());
        calculateOrderTotal();
    }




    @FXML
    void txtQtyKeyPress(KeyEvent event) {
        String text = txtQty.getText();
        if (text.equalsIgnoreCase("")) text = "1";
        itemQty = Integer.parseInt(text);

        setItemTotal(calculateItemTotal(selectedItem));
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
    void txtPayedAmountAction(ActionEvent event) {
        String text = txtPayedAmount.getText();
        setPaidAmount(Double.valueOf(text));
    }

    @FXML
    void txtPayedAmountKeyPress(KeyEvent event) {
        String text = txtPayedAmount.getText();
        setPaidAmount(Double.valueOf(text));
    }

    @FXML
    void btnPlaceOrderAction(ActionEvent event) {
        processPlaceOrder();
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

        cmbPriceTag.setItems(FXCollections.observableArrayList(SysConfig.SALES_TYPE_RETAIL,SysConfig.SALES_TYPE_MID,SysConfig.SALES_TYPE_WHOLESALE));
        cmbPriceTag.getSelectionModel().selectFirst();
    }

    private void loadTableFromQueryDTO(List<QueryDTO> list) {
        List<ItemTM> tableModel = new ArrayList<>();
        list.forEach(q -> {
            ItemTM itemTM = new ItemTM(q.getItemCode(), q.getDescription(), q.getCurrentQty(), q.getRetailPrice(), q.getMidPrice(), q.getWholesalePrice());
            System.out.println(q.getBilDescription());
            itemTM.setBillDescription(q.getBilDescription());
            tableModel.add(itemTM);
        });
        tblItems.setItems(FXCollections.observableList(tableModel));

        if (tableModel.size() == 1) {
            tblItems.getSelectionModel().selectFirst();
            tblListClickEvent(null);
        }
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

    private void processPlaceOrder() {

        OrderDTO orderDTO = new OrderDTO("B6NEW", cmbPriceTag.getValue(), paidAmount, balance, orderTotal);
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


            System.out.println();
            System.out.println(orderBO.getItem_List());
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
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
        System.out.println("orderTotal : "+ orderTotal);
        setPaidAmount(orderTotal);
    }

    private void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
        txtPayedAmount.setText(String.valueOf(paidAmount));
        setBalance();
    }

    private void setBalance() {
        this.balance = paidAmount-orderTotal;
        lblBalance.setText(String.valueOf(balance));
    }
}