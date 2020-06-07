package lk.janasetha.thogakade.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TabViewController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbForm1;

    @FXML
    private Tab tbForm2;

    public void initialize(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/" + "OrderForm" + ".fxml"));
            Parent root2 = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/" + "OrderForm" + ".fxml"));
            tbForm1.setContent(root);
            tbForm2.setContent(root2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {

        });
    }
}
