package entity;

public class Book {
    private int book_id;
    private String title;
    private String genre;
    private double price;
    private int stock;

    public Book() {
    }

    public Book(int book_id, String title, String genre, double price, int stock) {
        this.book_id = book_id;
        this.title = title;
        this.genre = genre;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
