package lk.janasetha.thogakade.service.custom.impl;

import lk.janasetha.thogakade.service.custom.BatchService;
import lk.janasetha.thogakade.repository.DAOFactory;
import lk.janasetha.thogakade.repository.custom.BatchDAO;
import lk.janasetha.thogakade.repository.custom.BatchDetailDAO;
import lk.janasetha.thogakade.repository.custom.ItemDAO;
import lk.janasetha.thogakade.db.DBConnection;
import lk.janasetha.thogakade.dto.BatchDTO;
import lk.janasetha.thogakade.dto.BatchDetailDTO;
import lk.janasetha.thogakade.dto.ItemDTO;
import lk.janasetha.thogakade.model.Batch;
import lk.janasetha.thogakade.model.BatchDetail;
import lk.janasetha.thogakade.model.Item;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BatchServiceImpl implements BatchService {

    private BatchDAO batchDAO = (BatchDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BATCH);
    private BatchDetailDAO batchDetailDAO = (BatchDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BATCHDETAIL);
    private ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<BatchDTO> getAllBatch() throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();

        List<Batch> all = batchDAO.getAll();
        List<BatchDTO> ret = new ArrayList<>();

        for (Batch batch : all) {
            BatchDTO batchDTO = new BatchDTO(batch.getBatchId(), batch.getSupplier(), batch.getDate(), batch.getTime(), batch.getStatus());
            List<BatchDetail> batchDetails = batchDetailDAO.getAllByBatchId(batch.getBatchId());
            List<BatchDetailDTO> batchDetailDTOS = new ArrayList<>();

            for (BatchDetail bd : batchDetails) {
                Item search = itemDAO.search(bd.getItemCode());

                ItemDTO itemDTO = null;
                if(search!=null){
                    itemDTO=new ItemDTO(search.getItemCode(), search.getDescription(), search.getStatus(), search.getCategoryId(), search.getBarcode());
                }

                BatchDetailDTO batchDetailDTO = new BatchDetailDTO(bd.getBidId(), bd.getBatchId(), itemDTO, bd.getQty(), bd.getCurrentStock(), bd.getRetailPrice(), bd.getMidPrice(), bd.getWholesalePrice(), bd.getBuyingPrice(), bd.getManufactureDate(), bd.getExpireDate());
                batchDetailDTOS.add(batchDetailDTO);
            }
            batchDTO.setBatchDetails(batchDetailDTOS);

            ret.add(batchDTO);
        }

        return ret;
    }
}
