package lk.janasetha.thogakade.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import lk.janasetha.thogakade.main.StartUp;

import javax.swing.text.html.StyleSheet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TabViewController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tbForm1;

    @FXML
    private Tab tbForm2;

    private StyleSheet styleSheet;

    private ArrayList<String> colorMap = new ArrayList();

    public TabViewController() {
        colorMap.add("-fx-background-color: #F4D35E");
        colorMap.add("-fx-background-color: #54F2F2");
        colorMap.add("-fx-background-color: #F95738");
        colorMap.add("-fx-background-color: #FF70A6");
        colorMap.add("-fx-background-color: #1098F7 ");
        colorMap.add("-fx-background-color: #EFC7E5");
        colorMap.add("-fx-background-color: #42CAFD");
        colorMap.add("-fx-background-color: #D34F73");
    }

    public void initialize() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/" + "OrderForm" + ".fxml"));
            Parent root2 = FXMLLoader.load(getClass().getResource("/lk/janasetha/thogakade/view/" + "OrderForm" + ".fxml"));
            tbForm1.setContent(root);
            tbForm2.setContent(root2);

        } catch (IOException e) {
            e.printStackTrace();
        }


        ObservableList<Tab> tabs = tabPane.getTabs();
        tabs.addListener((InvalidationListener) observable -> {
            setTabColors(tabs);
        });

        setTabColors(tabs);
        initiateKeyBindTabSwitch();


        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab oldTab, Tab newTab) {


                        System.out.println("CHANGED");

                    }
                }
        );


    }

    private void setTabColors(List<Tab> tabs) {
        for (int i = 0; i < tabs.size(); i++) {
            Tab tab = tabs.get(i);
            tab.setStyle(colorMap.get(i));
        }
    }

    private void initiateKeyBindTabSwitch() {
        final KeyCombination kb = new KeyCodeCombination(KeyCode.TAB, KeyCombination.CONTROL_DOWN);
        StartUp.scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (kb.match(event)) {
                int selectedIndex = tabPane.getSelectionModel().getSelectedIndex();
                if (selectedIndex < tabPane.getTabs().size() - 1) {
                    tabPane.getSelectionModel().select(selectedIndex + 1);
                } else {
                    tabPane.getSelectionModel().select(0);
                }
            }
        });
    }

}
