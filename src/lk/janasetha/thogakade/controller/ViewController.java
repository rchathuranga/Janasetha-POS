package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.FileNotFoundException;
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

    private HashMap<String, String> viewMap = new HashMap<>();
    private JFXButton clickButton;
    private static final String path = "/lk/janasetha/thogakade/view/";
    private static final String extension = ".fxml";

    public void initialize() {
        String tabView = path + "tabView" + extension;
        String itemForm = path + "ItemForm" + extension;
        String dashboard = path + "Dashboard" + extension;
        String modifyBatch = path + "ModifyBatch" + extension;
        String viewOrders = path + "ViewOrders" + extension;
        String settings = path + "Settings" + extension;

            viewMap.put("btnPlaceOrder", tabView);
            viewMap.put("btnManageItem", itemForm);
            viewMap.put("btnSettings", settings);
        viewMap.put("btnHome", dashboard);
            viewMap.put("btnModifyBatch", modifyBatch);
            viewMap.put("btnViewOrders", viewOrders);


        btnHome.fire();

    }

    public void get() throws FileNotFoundException {
        HashMap<String, String> hashMap = new HashMap<>();

        viewMap.put("btnPlaceOrder", "tabView");
        viewMap.put("btnManageItem", "ItemForm");
        viewMap.put("btnSettings", "Settings");
        viewMap.put("btnHome", "Dashboard");
        viewMap.put("btnModifyBatch", "ModifyBatch");
        viewMap.put("btnViewOrders", "ViewOrders");


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


    private void clearAndLoadRoot(JFXButton clickedBtn, String fxmlFileName) throws IOException {
        if (clickButton != null) clickButton.setStyle("-fx-background-color: #fff; -fx-text-fill: #2c2c2c ");

        clickedBtn.setStyle("-fx-background-color: #39393A; -fx-text-fill: #fff ");
        this.clickButton = clickedBtn;

        Parent root = FXMLLoader.load(getClass().getResource(viewMap.get(clickedBtn.getId())));
        window.getChildren().clear();
        window.getChildren().add(root);
    }

}
