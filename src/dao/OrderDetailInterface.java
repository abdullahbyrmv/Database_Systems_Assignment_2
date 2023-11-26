package dao;

import entity.OrderDetail;

import java.util.List;

public interface OrderDetailInterface {
    public boolean addOrderDetail(OrderDetail orderDetail);

    public List<OrderDetail> getAllOrderDetails();

    public boolean updateOrderDetail(OrderDetail orderDetail);

    public boolean deleteOrderDetail(int order_id, int book_id);

    public OrderDetail getOrderDetailById(int order_id);
}
