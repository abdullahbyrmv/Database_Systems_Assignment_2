package entity;


import java.sql.Date;

public class Order {
    private int order_id;
    private int customer_id;
    private Date order_date;
    private Customer customer;

    public Order() {
    }

    public Order(int order_id, int customer_id, Date order_date, Customer customer) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
        this.customer = customer;
    }

    public Order(int order_id, int customer_id, Date order_date) {
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.order_date = order_date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", order_date=" + order_date +
                ", customer=" + customer +
                '}';
    }
}
