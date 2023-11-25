package main;

import dao.AuthorInterface;
import daoImpl.AuthorDaoImpl;

public class Context {
    public static AuthorInterface instanceAuthorDao() {
        return new AuthorDaoImpl();
    }
}
