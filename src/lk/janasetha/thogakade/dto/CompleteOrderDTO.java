package lk.janasetha.thogakade.dto;

import java.util.List;

public class CompleteOrderDTO {

    private OrderDTO order;
    private List<OrderDetailDTO> items;


    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public List<OrderDetailDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailDTO> items) {
        this.items = items;
    }
}
