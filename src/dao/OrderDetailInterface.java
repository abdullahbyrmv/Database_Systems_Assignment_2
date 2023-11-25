package dao;

import entity.*;

import java.util.List;

public interface OrderDetailInterface {
    public boolean addOrderDetail(OrderDetail orderDetail);

    public List<OrderDetail> getAllOrderDetails();

    public boolean updateOrderDetail(OrderDetail orderDetail);

    public boolean deleteOrderDetail(Book book_id, Order order_id);

    public OrderDetail getOrderDetailById(int order_id);
}
