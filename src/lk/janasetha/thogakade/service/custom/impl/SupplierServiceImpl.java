package lk.janasetha.thogakade.service.custom.impl;

import lk.janasetha.thogakade.dto.SupplierDTO;
import lk.janasetha.thogakade.model.Supplier;
import lk.janasetha.thogakade.repository.DAOFactory;
import lk.janasetha.thogakade.repository.custom.SupplierDAO;
import lk.janasetha.thogakade.service.custom.SupplierService;

import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public List<SupplierDTO> getAllSuppliers() throws Exception {
        List<Supplier> supplierList = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();

        supplierList.forEach(supplier -> {
            SupplierDTO supplierDTO = new SupplierDTO();

            supplierDTO.setSupplierId(supplier.getSupplierId());
            supplierDTO.setSupplier(supplier.getSupplier());
            supplierDTO.setDescription(supplier.getDescription());
            supplierDTO.setBuyingPricePercentage(supplier.getBuyingPricePercentage());

            supplierDTOS.add(supplierDTO);
        });

        return supplierDTOS;
    }
}
