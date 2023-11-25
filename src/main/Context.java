package main;

import dao.*;
import daoImpl.*;

public class Context {
    public static AuthorInterface instanceAuthorDao() {
        return new AuthorDaoImpl();
    }

    public static BookInterface instanceBookDao() {
        return new BookDaoImpl();
    }

    public static CustomerInterface instanceCustomerDao() {
        return new CustomerDaoImpl();
    }

    public static OrderInterface instanceOrderDao() {
        return new OrderDaoImpl();
    }

    public static BookDetailInterface instanceBookDetailDao() {
        return new BookDetailDaoImpl();
    }

    public static OrderDetailInterface instanceOrderDetailDao() {
        return new OrderDetailDaoImpl();
    }
}
