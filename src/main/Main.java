package main;

import dao.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        LocalDate myDate = LocalDate.of(2023, 11, 26);
        Date date = Date.valueOf(myDate);

        BookInterface bookTable = Context.instanceBookDao();
        AuthorInterface authorTable = Context.instanceAuthorDao();
        BookDetailInterface bookDetailTable = Context.instanceBookDetailDao();
        OrderInterface orderTable = Context.instanceOrderDao();
        CustomerInterface customerTable = Context.instanceCustomerDao();
        OrderDetailInterface orderDetailTable = Context.instanceOrderDetailDao();

    }
}
