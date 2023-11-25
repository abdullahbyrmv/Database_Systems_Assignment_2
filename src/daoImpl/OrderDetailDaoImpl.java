package daoImpl;

import abstractDao.AbstractDao;
import dao.OrderDetailInterface;
import entity.Book;
import entity.Order;
import entity.OrderDetail;

import java.util.List;

public class OrderDetailDaoImpl extends AbstractDao implements OrderDetailInterface {

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {
        return false;
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return null;
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        return false;
    }

    @Override
    public boolean deleteOrderDetail(Book book_id, Order order_id) {
        return false;
    }

    @Override
    public OrderDetail getOrderDetailById(int order_id) {
        return null;
    }
}
