package dao;

import entity.OrderDetail;

import java.util.List;

public interface OrderDetailInterface {
    public boolean addOrderDetail(OrderDetail orderDetail);

    public List<OrderDetail> getAllOrderDetails();

    public boolean updateOrderDetail(OrderDetail orderDetail);

    public boolean deleteOrderDetail(int book_id, int order_id);

    public OrderDetail getOrderDetailById(int order_id);
}
