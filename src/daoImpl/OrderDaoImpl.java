package daoImpl;

import abstractDao.AbstractDao;
import dao.OrderInterface;
import entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends AbstractDao implements OrderInterface {

    @Override
    public boolean addOrder(Order order) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO orders (order_id,customer_id,order_date) VALUES (?,?,?)");
            st.setInt(1, order.getOrder_id());
            st.setInt(2, order.getCustomer_id());
            st.setDate(3, order.getOrder_date());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM orders");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                int customer_id = res.getInt("customer_id");
                Date order_date = res.getDate("order_date");
                orders.add(new Order(order_id, customer_id, order_date));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orders;
    }

    @Override
    public boolean updateOrder(Order order) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE orders SET customer_id=?, order_date=? WHERE order_id=?");
            st.setInt(1, order.getCustomer_id());
            st.setDate(2, order.getOrder_date());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOrder(int order_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM orders WHERE order_id = " + order_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Order getOrderById(int order_id) {
        Order order = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM orders WHERE order_id = " + order_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id_order = res.getInt("order_id");
                int customer_id = res.getInt("customer_id");
                Date order_date = res.getDate("order_date");
                order = new Order(id_order, customer_id, order_date);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return order;
    }
}
