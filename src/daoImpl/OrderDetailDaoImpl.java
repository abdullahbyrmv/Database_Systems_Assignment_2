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

    private void deleteEmptyOrder() {
        try (Connection connection = connect()) {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM orders WHERE order_id NOT IN (SELECT DISTINCT order_id FROM order_detail)";
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("An error occurred while deleting empty orders: " + e.getMessage());
        }
    }

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {

        BookInterface bookInterface = Context.instanceBookDao();

        try (Connection connection = connect();
             PreparedStatement insertOrderStatement = connection.prepareStatement("INSERT INTO order_detail (order_id, book_id,number_of_ordered_books) VALUES (?, ?, ?)");
             PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE book SET stock = ? WHERE book_id = ?")) {

            connection.setAutoCommit(false);

            int requestedBookId = orderDetail.getBook_id();
            int requestedQuantity = orderDetail.getNumber_of_ordered_books();
            Book book = bookInterface.getBookById(requestedBookId);

            if (book != null && book.getStock() >= requestedQuantity) {
                insertOrderStatement.setInt(1, orderDetail.getOrder_id());
                insertOrderStatement.setInt(2, orderDetail.getBook_id());
                insertOrderStatement.setInt(3, orderDetail.getNumber_of_ordered_books());
                insertOrderStatement.executeUpdate();

                int updatedStock = book.getStock() - requestedQuantity;
                updateBookStatement.setInt(1, updatedStock);
                updateBookStatement.setInt(2, requestedBookId);
                updateBookStatement.executeUpdate();

                connection.commit();
                System.out.println("Query executing: INSERT INTO order_detail (order_id,book_id,number_of_ordered_books)" +
                        " VALUES (" + orderDetail.getOrder_id() + "," + orderDetail.getBook_id() +
                        "," + orderDetail.getNumber_of_ordered_books() + ")");
                System.out.println("Order detail inserted successfully");
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
            st.execute("SELECT * FROM order_detail ORDER BY order_id");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int order_id = res.getInt("order_id");
                int book_id = res.getInt("book_id");
                int number_of_ordered_books = res.getInt("number_of_ordered_books");
                System.out.println("order_id = " + order_id + ", book_id = " + book_id + ", number_of_ordered_books = "
                        + number_of_ordered_books);
                orderDetailList.add(new OrderDetail(order_id, book_id, number_of_ordered_books));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (orderDetailList.size() == 0) {
            System.out.println("No records found");
        }
        return orderDetailList;
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        BookInterface bookTable = Context.instanceBookDao();
        OrderDetailInterface orderDetailTable = Context.instanceOrderDetailDao();
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE order_detail SET book_id=?,number_of_ordered_books=? WHERE order_id=?");
            st.setInt(1, orderDetail.getBook_id());
            st.setInt(2, orderDetail.getNumber_of_ordered_books());
            st.setInt(3, orderDetail.getOrder_id());
            System.out.println("Updated order with order_id = " + orderDetail.getOrder_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteOrderDetail(int order_id, int book_id) {
        try (Connection connection = connect()) {
            BookInterface bookTable = Context.instanceBookDao();
            OrderDetailInterface orderDetailTable = Context.instanceOrderDetailDao();
            Statement st = connection.createStatement();
            PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE book SET stock = ? WHERE book_id = ?");
            connection.setAutoCommit(false);
            Book book = bookTable.getBookById(book_id);
            OrderDetail orderDetail = orderDetailTable.getOrderDetailByOrderIdAndBookId(order_id, book_id);
            updateBookStatement.setInt(1, book.getStock() + orderDetail.getNumber_of_ordered_books());
            updateBookStatement.setInt(2, book_id);
            int number_of_affected_rows = st.executeUpdate("DELETE FROM order_detail WHERE order_id = " + order_id +
                    " AND book_id = " + book_id);
            if (number_of_affected_rows == 0) {
                System.out.println("No record with order_id = " + order_id + " AND book_id = " + book_id + " exists");
                return false;
            }

            updateBookStatement.executeUpdate();

            connection.commit();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Deleted record with order_id = " + order_id + " AND book_id = " + book_id);
        return true;
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int order_id) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM order_detail WHERE order_id = " + order_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id_order = res.getInt("order_id");
                int id_book = res.getInt("book_id");
                int number_of_ordered_books = res.getInt("number_of_ordered_books");
                System.out.println("order_id = " + id_order + ", book_id = " + id_book + ", number_of_ordered_books = "
                        + number_of_ordered_books);
                orderDetailList.add(new OrderDetail(id_order, id_book, number_of_ordered_books));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (orderDetailList.size() == 0) {
            System.out.println("No such order detail found");
        }
        return orderDetailList;
    }

    @Override
    public OrderDetail getOrderDetailByOrderIdAndBookId(int order_id, int book_id) {
        OrderDetail orderDetail = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM order_detail WHERE order_id = " + order_id + " AND book_id = " + book_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id_order = res.getInt("order_id");
                int id_book = res.getInt("book_id");
                int number_of_ordered_books = res.getInt("number_of_ordered_books");
                System.out.println("order_id = " + id_order + ", book_id = " + id_book + ", number_of_ordered_books = "
                        + number_of_ordered_books);
                orderDetail = new OrderDetail(id_order, id_book, number_of_ordered_books);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (orderDetail == null) {
            System.out.println("No such order detail found");
        }
        return orderDetail;
    }
}
