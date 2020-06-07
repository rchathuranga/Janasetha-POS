package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.HashMap;

public class ViewController {

    @FXML
    private BorderPane border;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnManageItem;

    @FXML
    private JFXButton btnSettings;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private AnchorPane window;

    private HashMap<String, Parent> viewMap = new HashMap<>();
    private JFXButton clickButton;
    private static final String path = "/lk/janasetha/thogakade/view/";
    private static final String extension = ".fxml";

    public void initialize() {
        try {
            Parent tabView = FXMLLoader.load(getClass().getResource(path + "tabView" + extension));
            Parent itemForm = FXMLLoader.load(getClass().getResource(path + "ItemForm" + extension));
            Parent home = FXMLLoader.load(getClass().getResource(path + "Dashboard" + extension));
            Parent modifyBatch = FXMLLoader.load(getClass().getResource(path + "ModifyBatch" + extension));
            Parent viewOrders = FXMLLoader.load(getClass().getResource(path + "ViewOrders" + extension));
            Parent settings = FXMLLoader.load(getClass().getResource(path + "Settings" + extension));
            viewMap.put("btnPlaceOrder", tabView);
            viewMap.put("btnManageItem", itemForm);
            viewMap.put("btnSettings", settings);
            viewMap.put("btnHome", home);
            viewMap.put("btnModifyBatch", modifyBatch);
            viewMap.put("btnViewOrders", viewOrders);

        } catch (IOException e) {
            e.printStackTrace();
        }

        btnHome.fire();
    }


    @FXML
    void MenuBtnAction(ActionEvent event) {
        try {
            JFXButton clickedBtn = (JFXButton) event.getSource();
            clearAndLoadRoot(clickedBtn, clickedBtn.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void clearAndLoadRoot(JFXButton clickedBtn, String fxmlFileName) {
        if (clickButton != null) clickButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #2c2c2c ");

        clickedBtn.setStyle("-fx-background-color: #39393A; -fx-text-fill: #fff ");
        this.clickButton = clickedBtn;

        Parent root = viewMap.get(fxmlFileName);
        window.getChildren().clear();
        window.getChildren().add(root);
    }

}
