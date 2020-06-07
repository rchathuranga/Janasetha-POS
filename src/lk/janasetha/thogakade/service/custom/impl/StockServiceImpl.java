package lk.janasetha.thogakade.service.custom.impl;

import lk.janasetha.thogakade.db.DBConnection;
import lk.janasetha.thogakade.dto.*;
import lk.janasetha.thogakade.model.Batch;
import lk.janasetha.thogakade.model.BatchDetail;
import lk.janasetha.thogakade.model.Item;
import lk.janasetha.thogakade.repository.DAOFactory;
import lk.janasetha.thogakade.repository.custom.BatchDAO;
import lk.janasetha.thogakade.repository.custom.BatchDetailDAO;
import lk.janasetha.thogakade.repository.custom.ItemDAO;
import lk.janasetha.thogakade.repository.custom.QueryDAO;
import lk.janasetha.thogakade.service.custom.StockService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StockServiceImpl implements StockService {

    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);
    private final BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BATCH);
    private final BatchDetailDAO batchDetailDAO = (BatchDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BATCHDETAIL);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.QUERY);

    @Override
    public int addNewStock(CompleteStockDTO stockDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean processSuccess = false;

        BatchDTO batchDTO = stockDTO.getBatch();

        Batch batch = new Batch(batchDTO.getSupplier(), batchDTO.getDate(), batchDTO.getTime(), batchDTO.getStatus(), batchDTO.getInvoiceNo(), batchDTO.getBillTotal());
        int batchId = batchDAO.add(batch);

        if (batchId != 0) {

            for (BatchDetailDTO batchDetailDTO : stockDTO.getBatchDetail()) {

                ItemDTO itemDTO = batchDetailDTO.getItem();
                int itemId = itemDTO.getItemCode();


                if (itemId == 0) {
                    Item item = new Item(itemDTO.getDescription(),itemDTO.getBillDescription(), itemDTO.getStatus(), itemDTO.getCategoryId(), itemDTO.getBarcode());
                    itemId = itemDAO.add(item);
                }


                if (itemId > 0) {

                    BatchDetail batchDetail = new BatchDetail(
                            batchId,
                            itemId,
                            batchDetailDTO.getQty(),
                            batchDetailDTO.getQty(),
                            batchDetailDTO.getRetailPrice(),
                            batchDetailDTO.getMidPrice(),
                            batchDetailDTO.getWholesalePrice(),
                            batchDetailDTO.getBuyingPrice(),
                            batchDetailDTO.getExpireDate(),
                            batchDetailDTO.getManufactureDate());


                    processSuccess = batchDetailDAO.add(batchDetail) > 0;
                    if (!processSuccess) {
                        break;
                    }
                }
            }

        }

        if (processSuccess) {
            connection.commit();
        }
        connection.rollback();

        connection.setAutoCommit(true);
        return 0;
    }

    @Override
    public List<CompleteStockDTO> getAllStockDetails() throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();

        List<CompleteStockDTO> returnValues = new ArrayList<>();


        List<Batch> allBatch = batchDAO.getAll();

        for (Batch batch : allBatch) {
            BatchDTO batchDTO = new BatchDTO(batch.getBatchId(), batch.getSupplier(), batch.getDate(), batch.getTime(), batch.getStatus(), batch.getInvoiceNo(), batch.getBillTotal());
            List<BatchDetailDTO> batchDetailDTOList = new ArrayList<>();

            List<BatchDetail> allBatchDetails = batchDetailDAO.getAllByBatchId(batch.getBatchId());

            for (BatchDetail batchDetail : allBatchDetails) {

                Item search = itemDAO.search(batchDetail.getItemCode());
                ItemDTO itemDTO = new ItemDTO(search.getItemCode(),
                        search.getDescription(), search.getStatus(),
                        search.getCategoryId(),
                        search.getBarcode());

                BatchDetailDTO batchDetailDTO = new BatchDetailDTO(batchDetail.getBidId(), batchDetail.getBatchId(),itemDTO, batchDetail.getQty(), batchDetail.getCurrentStock(), batchDetail.getRetailPrice(),
                        batchDetail.getMidPrice(), batchDetail.getWholesalePrice(),batchDetail.getBuyingPrice(), batchDetail.getManufactureDate(), batchDetail.getExpireDate());

                batchDetailDTOList.add(batchDetailDTO);
            }

            returnValues.add(new CompleteStockDTO(batchDTO, batchDetailDTOList));
        }


        return returnValues;
    }


    @Override
    public List<QueryDTO> getAvailableStock() throws Exception {
        return queryDAO.getAvailableStock();
    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();


        List<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> returnList = new ArrayList<>();

        if (all!=null){
            for (Item item : all) {
                ItemDTO itemDTO = new ItemDTO();

                itemDTO.setItemCode(item.getItemCode());
                itemDTO.setDescription(item.getDescription());
                itemDTO.setBillDescription(item.getBillDescription());
                itemDTO.setRegularPrice(item.getRegularPrice());
                itemDTO.setCategoryId(item.getCategoryId());
                itemDTO.setStatus(item.getStatus());
                itemDTO.setQtyOnHand(batchDetailDAO.getQtyOnHand(item.getItemCode()));

                returnList.add(itemDTO);
            }
        }

        return returnList;
    }

    @Override
    public List<ItemDTO> getAllItemsByBarcode(String barcode) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        List<Item> all = itemDAO.getAllItemsByBarcode(barcode);
        ArrayList<ItemDTO> returnList = new ArrayList<>();

        if (all!=null){
            for (Item item : all) {
                ItemDTO itemDTO = new ItemDTO();

                itemDTO.setItemCode(item.getItemCode());
                itemDTO.setDescription(item.getDescription());
                itemDTO.setBillDescription(item.getBillDescription());
                itemDTO.setRegularPrice(item.getRegularPrice());
                itemDTO.setCategoryId(item.getCategoryId());
                itemDTO.setStatus(item.getStatus());
                itemDTO.setQtyOnHand(batchDetailDAO.getQtyOnHand(item.getItemCode()));

                returnList.add(itemDTO);
            }
        }

        return returnList;
    }

    @Override
    public List<ItemDTO> getAllItemsByDescription(String description) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        List<Item> all = itemDAO.getAllItemsByDescription(description);
        ArrayList<ItemDTO> returnList = new ArrayList<>();

        if (all!=null){
            for (Item item : all) {
                ItemDTO itemDTO = new ItemDTO();

                itemDTO.setItemCode(item.getItemCode());
                itemDTO.setDescription(item.getDescription());
                itemDTO.setBillDescription(item.getBillDescription());
                itemDTO.setRegularPrice(item.getRegularPrice());
                itemDTO.setCategoryId(item.getCategoryId());
                itemDTO.setStatus(item.getStatus());
                itemDTO.setQtyOnHand(batchDetailDAO.getQtyOnHand(item.getItemCode()));

                returnList.add(itemDTO);
            }
        }

        return returnList;
    }

    @Override
    public List<QueryDTO> getLatestBatchDetailsByItemCode(int itemCode) throws Exception {
        return queryDAO.getLatestBatchDetailsByItemCode(itemCode);
    }

    @Override
    public List<QueryDTO> getAvailableStockByBarcode(String barcode) throws Exception {
        return queryDAO.getAvailableStockByBarcode(barcode);
    }

    @Override
    public List<QueryDTO> getAvailableStockByDescription(String description) throws Exception {
        return queryDAO.getAvailableStockByDescription(description);
    }

    @Override
    public List<QueryDTO> getBatchDetailsByItemCode(int itemCode) throws Exception {
        return queryDAO.getBatchDetailsByItemCode(itemCode,false);
    }
}

