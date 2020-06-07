package lk.janasetha.thogakade.service.custom;


import lk.janasetha.thogakade.service.SuperService;
import lk.janasetha.thogakade.dto.BatchDTO;

import java.util.List;

public interface BatchService extends SuperService {
    public List<BatchDTO> getAllBatch() throws Exception;
}
