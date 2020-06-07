package lk.janasetha.thogakade.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.janasetha.thogakade.dto.BatchDTO;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.BatchService;

import java.util.List;

public class ModifyBatchController {

    @FXML
    private TableView<BatchDTO> tblBatch;

    @FXML
    private TableView<?> tblBatchDetails;

    @FXML
    private DatePicker dtpSearch;

    private BatchService batchService = (BatchService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.BATCH);

    public void initialize() {
        tblBatch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("batchId"));
        tblBatch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplier"));
        tblBatch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblBatch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("time"));
        tblBatch.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        tblBatch.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("billTotal"));

        try {
            List<BatchDTO> allBatch = batchService.getAllBatch();
            tblBatch.setItems(FXCollections.observableList(allBatch));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
