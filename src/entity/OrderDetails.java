package entity;

public class OrderDetails {
    private Order order_id;
    private Book book_id;
    private int number_of_books;

    public OrderDetails() {
    }

    public OrderDetails(Order order_id, Book book_id, int number_of_books) {
        this.order_id = order_id;
        this.book_id = book_id;
        this.number_of_books = number_of_books;
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

    public int getNumber_of_books() {
        return number_of_books;
    }

    public void setNumber_of_books(int number_of_books) {
        this.number_of_books = number_of_books;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "order_id=" + order_id +
                ", book_id=" + book_id +
                ", number_of_books=" + number_of_books +
                '}';
    }
}
