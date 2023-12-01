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
            PreparedStatement st = connection.prepareStatement("INSERT INTO customer (customer_id,customer_name,customer_surname,address,email) VALUES (?,?,?,?,?)");
            st.setInt(1, customer.getCustomer_id());
            st.setString(2, customer.getCustomer_name());
            st.setString(3, customer.getCustomer_surname());
            st.setString(4, customer.getAddress());
            st.setString(5, customer.getEmail());
            System.out.println("Query executing: INSERT INTO customer (customer_id,customer_name,customer_surname,address,email)" +
                    " " + "VALUES (" + customer.getCustomer_id() + "," + customer.getCustomer_name() +
                    "," + customer.getCustomer_surname() + "," + customer.getAddress() + "," + customer.getEmail() + ")");
            st.execute();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Customer inserted successfully!");
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connect()) {
            Statement st = connection.createStatement();
            st.execute("SELECT * FROM customer ORDERY BY customer_id");
            ResultSet res = st.getResultSet();
            while (res.next()) {
                int customer_id = res.getInt("customer_id");
                String customer_name = res.getString("customer_name");
                String customer_surname = res.getString("customer_surname");
                String address = res.getString("address");
                String email = res.getString("email");
                System.out.println("customer_id = " + customer_id + ", customer_name = " + customer_name +
                        ", customer_surname = " + customer_surname + ", address = " + address + ", email = " + email);
                customers.add(new Customer(customer_id, customer_name, customer_surname, address, email));
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return customers;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try (Connection connection = connect()) {
            PreparedStatement st = connection.prepareStatement("UPDATE customer SET customer_name=?, customer_surname=?, address=?,email=? WHERE customer_id=?");
            st.setString(1, customer.getCustomer_name());
            st.setString(2, customer.getCustomer_surname());
            st.setString(3, customer.getAddress());
            st.setString(4, customer.getEmail());
            st.setInt(5, customer.getCustomer_id());
            System.out.println("Updated customer with customer_id = " + customer.getCustomer_id());
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
            int number_of_affected_rows = st.executeUpdate("DELETE FROM customer WHERE customer_id = " + customer_id);

            if (number_of_affected_rows == 0) {
                System.out.println("No customer with customer_id = " + customer_id + " exists.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        System.out.println("Deleted customer with customer_id = " + customer_id);
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
                String customer_name = res.getString("customer_name");
                String customer_surname = res.getString("customer_surname");
                String address = res.getString("address");
                String email = res.getString("email");
                System.out.println("customer_id = " + id + ", customer_name = " + customer_name +
                        ", customer_surname = " + customer_surname + ", address = " + address + ", email = " + email);
                customer = new Customer(id, customer_name, customer_surname, address, email);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        if (customer == null) {
            System.out.println("No such customer found");
        }
        return customer;
    }
}
