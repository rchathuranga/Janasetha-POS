package lk.janasetha.thogakade.service;

import lk.janasetha.thogakade.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getBO(BOTypes type) {
        switch (type) {
            case CATEGORY:
                return new CategoryServiceImpl();
            case ORDER:
                return new OrderServiceImpl();
            case STOCK:
                return new StockServiceImpl();
            case BATCH:
                return new BatchServiceImpl();
            case SUPPLIER:
                return new SupplierServiceImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, ACCOUNT, ORDER, STOCK, PHONE, BRAND, CATEGORY, CUSTOM, SUPPLIER,
        SDISCOUNT, RETURNS, REPAIRSERVICE, BATCH, REPAIRDETAIL, ORDERDETAIL
    }
}
