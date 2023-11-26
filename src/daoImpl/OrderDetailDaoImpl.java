package daoImpl;

import abstractDao.AbstractDao;
import dao.OrderDetailInterface;
import entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl extends AbstractDao implements OrderDetailInterface {

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO order_detail (order_id,book_id) VALUES (?,?)");
            st.setInt(1, orderDetail.getOrder_id());
            st.setInt(2, orderDetail.getBook_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM order_detail");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                int book_id = res.getInt("book_id");
                orderDetailList.add(new OrderDetail(order_id, book_id));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orderDetailList;
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE order_detail SET book_id=? WHERE order_id=?");
            st.setInt(1, orderDetail.getBook_id());
            st.setInt(2, orderDetail.getOrder_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOrderDetail(int order_id, int book_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM order_detail WHERE order_id = " + order_id + " AND book_id" + book_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public OrderDetail getOrderDetailById(int order_id) {
        OrderDetail orderDetail = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM order_detail WHERE order_id = " + order_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id_order = res.getInt("order_id");
                int id_book = res.getInt("book_id");
                orderDetail = new OrderDetail(id_order, id_book);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orderDetail;
    }
}
