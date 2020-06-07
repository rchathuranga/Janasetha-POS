package lk.janasetha.thogakade.main;

import lk.janasetha.thogakade.service.ServiceFactory;
import lk.janasetha.thogakade.service.custom.OrderService;
import lk.janasetha.thogakade.dto.*;
import lk.janasetha.thogakade.exception.OutOfStockException;
import lk.janasetha.thogakade.utill.MyLogger;
import lk.janasetha.thogakade.utill.SysConfig;

import java.util.ArrayList;
import java.util.List;

public class OrderUp {

    public static void main(String[] args) {
//        MyLogger.debug();
        OrderService orderBO = (OrderService) ServiceFactory.getInstance().getBO(ServiceFactory.BOTypes.ORDER);

        OrderDTO orderDTO = new OrderDTO("B6NEW",false, SysConfig.SALES_TYPE_RETAIL,12100);
        List<OrderDetailDTO> items = new ArrayList<>();

        OrderDetailDTO rawRice = new OrderDetailDTO(new BatchDetailDTO(8,new ItemDTO(4)), 1000, 100, 10000);
        OrderDetailDTO sunLight = new OrderDetailDTO(new BatchDetailDTO(14,new ItemDTO(2)), 900, 9.00, 8100);
        items.add(rawRice);
        items.add(sunLight);


        CompleteOrderDTO completeOrderDTO = new CompleteOrderDTO();
        completeOrderDTO.setOrder(orderDTO);
        completeOrderDTO.setItems(items);


        try {

            System.out.println("000000000000000000000000  000000000000000000000000");
            orderBO.addOrder(completeOrderDTO);
            System.out.println("000000000000000000000000  000000000000000000000000");


            orderBO.getItem_List().forEach(System.out::println);


        }catch (OutOfStockException e){
            System.out.println("OutOfStockException");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
