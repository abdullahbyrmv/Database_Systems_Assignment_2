package dao;

import entity.OrderDetail;

import java.util.List;

public interface OrderDetailInterface {
    public boolean addOrderDetail(OrderDetail orderDetail);

    public List<OrderDetail> getAllOrderDetails();

    public boolean updateOrderDetail(OrderDetail orderDetail);

    public boolean deleteOrderDetail(int order_id, int book_id);

    public List<OrderDetail> getOrderDetailByOrderId(int order_id);

    public OrderDetail getOrderDetailByOrderIdAndBookId(int order_id, int book_id);
}
