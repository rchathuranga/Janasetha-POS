package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.OrderDTO;
import lk.janasetha.thogakade.dto.OrderDetailDTO;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.OrderService;
import lk.janasetha.thogakade.tm.OrderDetailTM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewOrdersController {
    @FXML
    private TableView<OrderDTO> tblOrder;
    @FXML
    private TableView<OrderDetailTM> tblOrderDetails;
    @FXML
    private TextField txtBillNo;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private DatePicker dtpDate;
    @FXML
    private JFXButton btnClear;

    private OrderService orderService = (OrderService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ORDER);


    public void initialize() {
        initializeComponents();
        new Thread(this::loadAllOrdersToTable).start();
    }

    private void loadAllOrdersToTable() {
        try {
            List<OrderDTO> orders = orderService.getOrders();
            tblOrder.setItems(FXCollections.observableList(orders));
            tblOrder.getSelectionModel().selectFirst();
            loadOrderDetailToSelectedOrder();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeComponents() {
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("billNo"));
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("time"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salesType"));
        tblOrder.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("amount"));

        dtpDate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (dtpDate.focusedProperty().get()) dtpDate.show();
        });
    }

    @FXML
    void btnSearchAction(ActionEvent event) {
        System.out.println(dtpDate.getValue());
        boolean byOrderId = !txtBillNo.getText().equalsIgnoreCase("");
        boolean byDate = dtpDate.getValue() != null;

        if (byOrderId) {
            searchOrderByOrderIdOrDate(true);
        } else if (byDate) {
            searchOrderByOrderIdOrDate(false);
        } else {
            // TODO: 7/21/2020 set Alert to enter values
            System.out.println("Empty");
        }

    }

    @FXML
    void tblOrderClickEvent(MouseEvent event) {
        loadOrderDetailToSelectedOrder();
    }

    @FXML
    void tblOrderKeyPressed(KeyEvent event) {
        loadOrderDetailToSelectedOrder();
    }

    @FXML
    void txtBillNoAction(ActionEvent event) {
        searchOrderByOrderIdOrDate(true);
    }

    @FXML
    void dtpDateAction(ActionEvent event) {
        searchOrderByOrderIdOrDate(false);
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        loadAllOrdersToTable();
        txtBillNo.setText("");
        dtpDate.setValue(null);
    }

    private void loadOrderDetailToSelectedOrder() {
        try {
            OrderDTO selectedItem = tblOrder.getSelectionModel().getSelectedItem();
            List<OrderDetailDTO> orderDetailByOrderId = orderService.getOrderDetailByOrderId(selectedItem.getOrderId());

            List<OrderDetailTM> tm = new ArrayList<>();
            for (OrderDetailDTO detailDTO : orderDetailByOrderId) {
                OrderDetailTM orderDetailTM = new OrderDetailTM();

                orderDetailTM.setId(detailDTO.getId());
                orderDetailTM.setItemId(detailDTO.getBatchDetailDTO().getItem().getItemCode());
                orderDetailTM.setItemDescription(detailDTO.getBatchDetailDTO().getItem().getDescription());
                orderDetailTM.setQty(detailDTO.getQty());
                orderDetailTM.setUnitPrice(detailDTO.getUnitPrice());
                orderDetailTM.setAmount(detailDTO.getTotal());

                tm.add(orderDetailTM);
            }

            tblOrderDetails.setItems(FXCollections.observableList(tm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchOrderByOrderIdOrDate(boolean isByOrderId) {
        try {
            if (isByOrderId) {
                if (txtBillNo.getText().isEmpty()) {
                    loadAllOrdersToTable();
                } else {
                    int billNo = Integer.parseInt(txtBillNo.getText());
                    OrderDTO order = orderService.searchOrder(billNo);
                    tblOrder.setItems(FXCollections.observableList(Collections.singletonList(order)));
                    tblOrder.getSelectionModel().selectFirst();
                    if (order != null) loadOrderDetailToSelectedOrder();
                }
            } else {
                Date date = Date.valueOf(dtpDate.getValue());
                List<OrderDTO> orderList = orderService.getOrdersByDate(date);
                tblOrder.setItems(FXCollections.observableList(orderList));
                tblOrder.getSelectionModel().selectFirst();
                if (!orderList.isEmpty()) loadOrderDetailToSelectedOrder();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
