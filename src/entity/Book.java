package entity;

public class Book {
    private int book_id;
    private String title;
    private String genre;
    private int stock;
    private Author author;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book() {
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book(int book_id, String title, String genre, int stock, Author author) {
        this.book_id = book_id;
        this.title = title;
        this.genre = genre;
        this.stock = stock;
        this.author = author;
    }

    public Book(int book_id, String title, String genre, int stock, Order order) {
        this.book_id = book_id;
        this.title = title;
        this.genre = genre;
        this.stock = stock;
        this.order = order;
    }

    public Book(int book_id, String title, String genre, int stock, Author author, Order order) {
        this.book_id = book_id;
        this.title = title;
        this.genre = genre;
        this.stock = stock;
        this.author = author;
        this.order = order;
    }

    public Book(int book_id, String title, String genre, int stock) {
        this.book_id = book_id;
        this.title = title;
        this.genre = genre;
        this.stock = stock;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", stock=" + stock +
                ", author=" + author +
                ", order=" + order +
                '}';
    }
}
