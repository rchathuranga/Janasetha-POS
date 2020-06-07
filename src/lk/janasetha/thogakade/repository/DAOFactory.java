package lk.janasetha.thogakade.repository;

import lk.janasetha.thogakade.repository.custom.impl.*;
import lk.janasetha.thogakade.repository.custom.impl.ItemDAOImpl;
import lk.janasetha.thogakade.repository.custom.impl.OrderDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,ACCOUNT,ORDERS, CATEGORY,BRAND,CUSTOM,ORDERDETAIL,BATCHDETAIL,BATCH,QUERY,PAYMENT
    }

    public SuperDAO getDao(DAOTypes type){
        switch (type){
            case ORDERS:return new OrderDAOImpl();
            case ITEM:return new ItemDAOImpl();
            case ORDERDETAIL:return new OrderDetailDAOImpl();
            case BATCH:return new BatchDAOImpl();
            case BATCHDETAIL:return new BatchDetailDAOImpl();
            case QUERY:return new QueryDAOImpl();
            case PAYMENT: return new PaymentDAOImpl();
//            case CATAGORY:return new CatagoryDAOImpl();
//            case BRAND:return new BrandDAOImpl();
//            case CUSTOM:return new CustomDAOImpl();
            default:return null;
        }
    }
}
