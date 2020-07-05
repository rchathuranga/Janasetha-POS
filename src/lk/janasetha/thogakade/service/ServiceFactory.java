package lk.janasetha.thogakade.service;

import lk.janasetha.thogakade.service.custom.impl.BatchServiceImpl;
import lk.janasetha.thogakade.service.custom.impl.CategoryServiceImpl;
import lk.janasetha.thogakade.service.custom.impl.OrderServiceImpl;
import lk.janasetha.thogakade.service.custom.impl.StockServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){
    }
    public static ServiceFactory getInstance(){
        if(serviceFactory ==null){
            serviceFactory =new ServiceFactory();
        }
        return serviceFactory;
    }

    public SuperService getBO(BOTypes type){
        switch (type){
//            case BRAND:return new BrandBOImpl();
            case CATEGORY:
                return new CategoryServiceImpl();
//            case CUSTOM:return new CustomBOImpl();
            case ORDER:return new OrderServiceImpl();
            case STOCK:return new StockServiceImpl();
            case BATCH:return new BatchServiceImpl();
//            case ORDERDETAIL:return new OrderDetailBOImpl();
            default:return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, ACCOUNT, ORDER, STOCK, PHONE, BRAND, CATEGORY, CUSTOM,
        SDISCOUNT, RETURNS, REPAIRSERVICE, BATCH, REPAIRDETAIL, ORDERDETAIL
    }
}
