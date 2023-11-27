package daoImpl;

import abstractDao.AbstractDao;
import dao.BookInterface;
import dao.OrderDetailInterface;
import entity.Book;
import entity.OrderDetail;
import main.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl extends AbstractDao implements OrderDetailInterface {

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {

        BookInterface bookInterface = Context.instanceBookDao();

        try (Connection connection = connect();
             PreparedStatement insertOrderStatement = connection.prepareStatement("INSERT INTO order_detail (order_id, book_id,number_of_books) VALUES (?, ?, ?)");
             PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE book SET stock = ? WHERE book_id = ?")) {

            connection.setAutoCommit(false);

            int requestedBookId = orderDetail.getBook_id();
            int requestedQuantity = orderDetail.getNumber_of_books();
            Book book = bookInterface.getBookById(requestedBookId);

            if (book != null && book.getStock() >= requestedQuantity) {
                insertOrderStatement.setInt(1, orderDetail.getOrder_id());
                insertOrderStatement.setInt(2, orderDetail.getBook_id());
                insertOrderStatement.setInt(3, orderDetail.getNumber_of_books());
                insertOrderStatement.executeUpdate();

                int updatedStock = book.getStock() - requestedQuantity;
                updateBookStatement.setInt(1, updatedStock);
                updateBookStatement.setInt(2, requestedBookId);
                updateBookStatement.executeUpdate();

                connection.commit();
                return true;
            } else {
                connection.rollback();
                System.out.println("Not enough books in stock for this order.");
                return false;
            }
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
                int number_of_books = res.getInt("number_of_books");
                orderDetailList.add(new OrderDetail(order_id, book_id, number_of_books));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orderDetailList;
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE order_detail SET book_id=?,number_of_books=? WHERE order_id=?");
            st.setInt(1, orderDetail.getBook_id());
            st.setInt(2, orderDetail.getNumber_of_books());
            st.setInt(3, orderDetail.getOrder_id());
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
                int number_of_books = res.getInt("number_of_books");
                orderDetail = new OrderDetail(id_order, id_book, number_of_books);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return orderDetail;
    }
}
