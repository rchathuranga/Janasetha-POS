package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ViewOrdersController {

    @FXML
    private TableView<?> tblOrder;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    private TextField txtBillNo;

    @FXML
    private JFXButton btnSearch;

    @FXML
    void btnSearchAction(ActionEvent event) {

    }

    @FXML
    void txtBillNoAction(ActionEvent event) {

    }

}
