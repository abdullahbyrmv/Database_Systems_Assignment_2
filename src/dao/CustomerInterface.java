package dao;

import entity.Customer;

import java.util.List;

public interface CustomerInterface {
    public boolean addCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public boolean updateCustomer(Customer customer);

    public boolean deleteCustomer(int customer_id);

    public Customer getCustomerById(int customer_id);
}
