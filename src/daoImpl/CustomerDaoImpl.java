package daoImpl;

import abstractDao.AbstractDao;
import dao.CustomerInterface;
import entity.Customer;

import java.util.List;

public class CustomerDaoImpl extends AbstractDao implements CustomerInterface {

    @Override
    public boolean addCustomer(Customer customer) {
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return false;
    }

    @Override
    public boolean deleteCustomer(int customer_id) {
        return false;
    }

    @Override
    public Customer getCustomerById(int customer_id) {
        return null;
    }
}
