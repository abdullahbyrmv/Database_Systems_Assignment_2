package dao;

import entity.Order;

import java.util.List;

public interface OrderInterface {
    public boolean addOrder(Order order);

    public List<Order> getAllOrders();

    public boolean updateOrder(Order order);

    public boolean deleteOrder(int order_id);

    public Order getOrderById(int order_id);
}
