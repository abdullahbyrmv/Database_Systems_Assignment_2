package main;

import dao.AuthorInterface;
import dao.BookDetailInterface;
import dao.BookInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BookInterface bookInterface = Context.instanceBookDao();
        AuthorInterface authorInterface = Context.instanceAuthorDao();
        BookDetailInterface bookDetailInterface = Context.instanceBookDetailDao();

    }
}
