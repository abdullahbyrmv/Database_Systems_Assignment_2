package daoImpl;

import abstractDao.AbstractDao;
import dao.CustomerInterface;
import entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl extends AbstractDao implements CustomerInterface {

    @Override
    public boolean addCustomer(Customer customer) {
        try (Connection connection = connect();) {
            PreparedStatement st = connection.prepareStatement("INSERT INTO customer (customer_id,first_name,last_Name,address,email) VALUES (?,?,?,?,?)");
            st.setInt(1, customer.getCustomer_id());
            st.setString(2, customer.getFirst_name());
            st.setString(3, customer.getLast_name());
            st.setString(4, customer.getAddress());
            st.setString(5, customer.getEmail());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM customer");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int customer_id = res.getInt("customer_id");
                String first_name = res.getString("first_name");
                String last_name = res.getString("last_name");
                String address = res.getString("address");
                String email = res.getString("email");
                customers.add(new Customer(customer_id, first_name, last_name, address, email));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE customer SET first_name=?, last_name=?, address=?,email=? WHERE customer_id=?");
            st.setString(1, customer.getFirst_name());
            st.setString(2, customer.getLast_name());
            st.setString(3, customer.getAddress());
            st.setString(4, customer.getEmail());
            st.setInt(5, customer.getCustomer_id());
            return st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(int customer_id) {
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("DELETE FROM customer WHERE customer_id = " + customer_id);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Customer getCustomerById(int customer_id) {
        Customer customer = null;
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM customer WHERE customer_id = " + customer_id);
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int id = res.getInt("customer_id");
                String first_name = res.getString("first_name");
                String last_name = res.getString("last_name");
                String address = res.getString("address");
                String email = res.getString("email");
                customer = new Customer(id, first_name, last_name, address, email);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return customer;
    }
}
