package entity;

public class BookDetails {
    private Book book_id;
    private Author author_id;

    public BookDetails() {
    }

    public BookDetails(Book book_id, Author author_id) {
        this.book_id = book_id;
        this.author_id = author_id;
    }

    public Book getBook_id() {
        return book_id;
    }

    public void setBook_id(Book book_id) {
        this.book_id = book_id;
    }

    public Author getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Author author_id) {
        this.author_id = author_id;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "book_id=" + book_id +
                ", author_id=" + author_id +
                '}';
    }
}
