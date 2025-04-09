package service;

import model.Customer;
import repository.CustomerRepo;
import repository.Imp.SqlCustomerRep;

import java.sql.SQLException;

public class CustomerService {
    private SqlCustomerRep customerRepo;

    public CustomerService(SqlCustomerRep customerRepo) {
        this.customerRepo = customerRepo;
    }

    public boolean registerCustomer(Customer customer) throws SQLException {
        try {
            return customerRepo.addCustomer(customer);
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
            return false;
        }
    }

    public void showCustomers() throws SQLException {
        customerRepo.showCustomers();
    }
}