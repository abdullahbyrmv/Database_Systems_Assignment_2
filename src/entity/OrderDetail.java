package entity;

public class OrderDetail {
    private int order_id;
    private int book_id;
    private int number_of_ordered_books;

    public OrderDetail(int order_id, int book_id, int number_of_ordered_books) {
        this.order_id = order_id;
        this.book_id = book_id;
        this.number_of_ordered_books = number_of_ordered_books;
    }

    public OrderDetail() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getNumber_of_ordered_books() {
        return number_of_ordered_books;
    }

    public void setNumber_of_ordered_books(int number_of_ordered_books) {
        this.number_of_ordered_books = number_of_ordered_books;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "order_id=" + order_id +
                ", book_id=" + book_id +
                ", number_of_ordered_books=" + number_of_ordered_books +
                '}';
    }
}
