package lk.janasetha.thogakade.service.custom;


import lk.janasetha.thogakade.dto.BatchDTO;
import lk.janasetha.thogakade.service.SuperService;

import java.sql.Date;
import java.util.List;

public interface BatchService extends SuperService {
    public List<BatchDTO> getAllBatch() throws Exception;

    public List<BatchDTO> getAllBatchByDate(Date date) throws Exception;
}
