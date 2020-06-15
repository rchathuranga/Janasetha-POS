package lk.janasetha.thogakade.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.janasetha.thogakade.dto.BatchDTO;
import lk.janasetha.thogakade.dto.BatchDetailDTO;
import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.BatchService;
import lk.janasetha.thogakade.tm.BatchDetailTM;

import java.util.ArrayList;
import java.util.List;

public class ModifyBatchController {

    @FXML
    private TableView<BatchDTO> tblBatch;

    @FXML
    private TableView<BatchDetailTM> tblBatchDetails;

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


        try {
            List<BatchDTO> allBatch = batchService.getAllBatch();
            tblBatch.setItems(FXCollections.observableList(allBatch));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void tblBatchClickEvent(MouseEvent event) {
        List<BatchDetailDTO> batchDetails = tblBatch.getSelectionModel().getSelectedItem().getBatchDetails();
        tblBatchDetails.setItems(FXCollections.observableList(convertBatchDetailDTOtoTM(batchDetails)));
    }


    private List<BatchDetailTM> convertBatchDetailDTOtoTM(List<BatchDetailDTO> dtos) {
        List<BatchDetailTM> tms = new ArrayList<>();
        for (BatchDetailDTO bd : dtos) {
            tms.add(new BatchDetailTM(tms.size() + 1, bd));
        }
        return tms;
    }

}
