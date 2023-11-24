package dao;

import entity.Author;

import java.util.List;

public interface AuthorInterface {

    public boolean addAuthor(Author author);

    public List<Author> getAllAuthors();

    public boolean updateAuthor(Author author);

    public boolean deleteAuthor(int author_id);

    public Author getAuthorById(int author_id);
}
