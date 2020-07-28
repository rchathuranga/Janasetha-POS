package lk.janasetha.thogakade.service.custom;

import lk.janasetha.thogakade.dto.SupplierDTO;
import lk.janasetha.thogakade.service.SuperService;

import java.util.List;

public interface SupplierService extends SuperService {
    public List<SupplierDTO> getAllSuppliers() throws Exception;
}
