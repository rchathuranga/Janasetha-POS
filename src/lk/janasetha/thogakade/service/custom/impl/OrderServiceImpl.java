package lk.janasetha.thogakade.service.custom.impl;

import lk.janasetha.thogakade.dto.QueryDTO;
import lk.janasetha.thogakade.model.Payment;
import lk.janasetha.thogakade.repository.custom.*;
import lk.janasetha.thogakade.service.custom.OrderService;
import lk.janasetha.thogakade.repository.DAOFactory;
import lk.janasetha.thogakade.db.DBConnection;
import lk.janasetha.thogakade.dto.CompleteOrderDTO;
import lk.janasetha.thogakade.dto.OrderDTO;
import lk.janasetha.thogakade.dto.OrderDetailDTO;
import lk.janasetha.thogakade.exception.OutOfStockException;
import lk.janasetha.thogakade.model.BatchDetail;
import lk.janasetha.thogakade.model.Order;
import lk.janasetha.thogakade.model.OrderDetails;
import lk.janasetha.thogakade.utill.SysConfig;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ORDERS);
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ORDERDETAIL);
    private final BatchDetailDAO batchDetailDAO = (BatchDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BATCHDETAIL);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.QUERY);
    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PAYMENT);

    private List<QueryDTO> item_List = new ArrayList<>();

    @Override
    public int addOrder(CompleteOrderDTO completeOrder) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        OrderDTO order = completeOrder.getOrder();
        List<OrderDetailDTO> orderItems = completeOrder.getItems();

        validatePaymentCompleteness(completeOrder);

        Integer orderId = orderDAO.add(
                new Order(order.getBillNo(),
                        order.getDate(),
                        order.getTime(),
                        order.isPaymentCompleted(),
                        order.getSalesType(),
                        order.getTotal()));

        System.out.println("orderId : " + orderId);

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(order.getTotal());
        payment.setBalance(order.getBalance());
        payment.setPaidAmount(order.getPaidAmount());
        Integer paymentId = paymentDAO.add(payment);

        System.out.println("paymentId : " + paymentId);

        boolean orderDetailAdded = false;

        for (OrderDetailDTO orderDetail: orderItems) {
            List<QueryDTO> batchDetails = queryDAO.getBatchDetailsByItemCode(orderDetail.getBatchDetailDTO().getItem().getItemCode(),true);
            int _qty = orderDetail.getQty();
            int i=0;

            do {
                QueryDTO queryDTO = batchDetails.get(i++);
                System.out.println(queryDTO.getBatchDetailId()+ " - " +queryDTO.getDescription()+ " - " +queryDTO.getCurrentQty());


                double unitPrice = 0.0;
                double total = 0.0;
                int deductQty = 0;

                if(_qty <= queryDTO.getCurrentQty()){
                    batchDetailDAO.deductCurrentStock(queryDTO.getBatchDetailId(), _qty);
                    deductQty=_qty;
                    _qty = 0;

                }else {
                    deductQty=queryDTO.getCurrentQty();
                    _qty-=queryDTO.getCurrentQty();
                    batchDetailDAO.deductCurrentStock(queryDTO.getBatchDetailId(), deductQty);
                }

                if(order.getSalesType().equals(SysConfig.SALES_TYPE_WHOLESALE)){
                    unitPrice=queryDTO.getWholesalePrice();
                }else if(order.getSalesType().equals(SysConfig.SALES_TYPE_MID)){
                    unitPrice=queryDTO.getMidPrice();
                }else {
                    unitPrice=queryDTO.getRetailPrice();
                }


                orderDetailAdded = orderDetailDAO.add(new OrderDetails(orderId, queryDTO.getBatchDetailId(),
                    deductQty, unitPrice, deductQty * unitPrice)) > 0;

                queryDTO.setCurrentQty(deductQty);
                item_List.add(queryDTO);
            }while (_qty>0);
        }

        if (orderDetailAdded) {
            connection.commit();
        }

        connection.rollback();
        connection.setAutoCommit(true);
        return -1;
    }

    private void validatePaymentCompleteness(CompleteOrderDTO completeOrder) {
        System.out.println(":::::::::validatePaymentCompleteness::::::::::::");
        System.out.print(completeOrder.getOrder().getPaidAmount() + "\t>=\t" + completeOrder.getOrder().getTotal());
        completeOrder.getOrder().setPaymentCompleted((completeOrder.getOrder().getPaidAmount() >= completeOrder.getOrder().getTotal()));
    }


    private boolean validatePayment(CompleteOrderDTO order) throws Exception {
        boolean completePayment = false;

        Payment payment = new Payment();
        payment.setOrderId(order.getOrder().getOrderId());
        payment.setAmount(order.getOrder().getTotal());
        payment.setBalance(order.getOrder().getBalance());
        payment.setPaidAmount(order.getOrder().getPaidAmount());

        Integer add = paymentDAO.add(payment);
        if (add != 0) {
            completePayment = (order.getOrder().getPaidAmount() >= order.getOrder().getTotal());
        }

        return completePayment;
    }


    @Override
    public void cancelOrder(int OrderId) throws Exception {
        throw new OutOfStockException("Item Over");
    }

    @Override
    public List<QueryDTO> getItem_List() {
        return item_List;
    }
}
