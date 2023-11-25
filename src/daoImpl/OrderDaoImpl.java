package daoImpl;

import abstractDao.AbstractDao;
import dao.OrderInterface;
import entity.Order;

import java.util.List;

public class OrderDaoImpl extends AbstractDao implements OrderInterface {

    @Override
    public boolean addOrder(Order order) {
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public boolean deleteOrder(int order_id) {
        return false;
    }

    @Override
    public Order getOrderById(int order_id) {
        return null;
    }
}
