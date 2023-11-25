package entity;

public class OrderDetail {
    private Order order_id;
    private Book book_id;

    public OrderDetail() {
    }

    public OrderDetail(Order order_id, Book book_id) {
        this.order_id = order_id;
        this.book_id = book_id;
    }

    public Order getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Order order_id) {
        this.order_id = order_id;
    }

    public Book getBook_id() {
        return book_id;
    }

    public void setBook_id(Book book_id) {
        this.book_id = book_id;
    }


    @Override
    public String toString() {
        return "OrderDetails{" +
                "order_id=" + order_id +
                ", book_id=" + book_id +
                '}';
    }
}
