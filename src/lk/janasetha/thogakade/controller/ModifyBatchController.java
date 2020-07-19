package lk.janasetha.thogakade.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.BatchDTO;
import lk.janasetha.thogakade.dto.BatchDetailDTO;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.BatchService;
import lk.janasetha.thogakade.tm.BatchDetailTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModifyBatchController {
    @FXML
    private TableView<BatchDTO> tblBatch;
    @FXML
    private TableView<BatchDetailTM> tblBatchDetails;
    @FXML
    private DatePicker dtpDate;
    @FXML
    private TextField txtQty;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnClear;

    private BatchService batchService = (BatchService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.BATCH);

    public void initialize() {
        tblBatch.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("batchId"));
        tblBatch.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("supplier"));
        tblBatch.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblBatch.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("time"));
        tblBatch.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        tblBatch.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("billTotal"));

        tblBatchDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblBatchDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        tblBatchDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBatchDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("currentStock"));
        tblBatchDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        tblBatchDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("midPrice"));
        tblBatchDetails.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("wholesalePrice"));
        tblBatchDetails.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        tblBatchDetails.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("manufactureDate"));
        tblBatchDetails.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("expireDate"));

        dtpDate.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (dtpDate.focusedProperty().get()) dtpDate.show();
        });

        try {
            List<BatchDTO> allBatch = batchService.getAllBatch();
            tblBatch.setItems(FXCollections.observableList(allBatch));
            tblBatch.getSelectionModel().selectFirst();
            tblBatchClickEvent(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tblBatchClickEvent(MouseEvent event) {
        List<BatchDetailDTO> batchDetails = tblBatch.getSelectionModel().getSelectedItem().getBatchDetails();
        tblBatchDetails.setItems(FXCollections.observableList(convertBatchDetailDTOtoTM(batchDetails)));
    }

    @FXML
    void btnSearchAction(ActionEvent event) {
        searchBatchesByDate();
    }

    private void searchBatchesByDate() {
        LocalDate value = dtpDate.getValue();
        if (value != null) {
            Date date = Date.valueOf(value);
            try {
                List<BatchDTO> allBatchByDate = batchService.getAllBatchByDate(date);
                tblBatch.setItems(FXCollections.observableList(allBatchByDate));
                tblBatch.getSelectionModel().selectFirst();
                if (!allBatchByDate.isEmpty()) tblBatchClickEvent(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
    }

    @FXML
    void dtpDateAction(ActionEvent event) {
        searchBatchesByDate();
    }

    @FXML
    void tblBatchDetailsClickEvent(MouseEvent event) {
    }

    @FXML
    void txtQtyAction(ActionEvent event) {
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        try {
            List<BatchDTO> allBatch = batchService.getAllBatch();
            tblBatch.setItems(FXCollections.observableList(allBatch));
            tblBatch.getSelectionModel().selectFirst();
            tblBatchClickEvent(null);
            dtpDate.setValue(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<BatchDetailTM> convertBatchDetailDTOtoTM(List<BatchDetailDTO> dtos) {
        List<BatchDetailTM> tms = new ArrayList<>();
        for (BatchDetailDTO bd : dtos) {
            tms.add(new BatchDetailTM(tms.size() + 1, bd));
        }
        return tms;
    }

}
