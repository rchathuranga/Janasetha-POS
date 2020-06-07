package lk.janasetha.thogakade.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class TestController {


    @FXML
    private TableView<?> tblItems;

    @FXML
    private Button btnSave;

    @FXML
    void btnSaveAction(ActionEvent event) {
        System.out.println("clicked");


        Parent parent = btnSave.getParent();
        System.out.println(parent.getId());

        Parent parent1 = parent.getParent();
        System.out.println(parent1.getId());

        Parent parent2 = parent1.getParent();
        System.out.println(parent2.getId());

        Parent parent3 = parent2.getParent();
        System.out.println(parent3.getId());

        Parent parent4 = parent3.getParent();
        System.out.println(parent4.getId());

        Parent parent5 = parent4.getParent();
        System.out.println(parent5.getId());

        System.out.println();
    }

}

