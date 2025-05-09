package service;

import controller.Colors;
import model.Customer;
import repository.Imp.SqlCustomerRep;

import java.sql.SQLException;
import java.util.List;

public class CustomerService extends SqlCustomerRep {
    private final SqlCustomerRep customerRepo;



    public CustomerService(SqlCustomerRep customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void registerCustomer(Customer customer)  {

        boolean res = customerRepo.addCustomer(customer);
        System.out.println(res ? Colors.GREEN+"Customer added successfully"+Colors.RESET: " failed to add a customer"+Colors.RESET);

    }

    public void updateCustomer(int customerId, Customer customer)  {

        boolean res = customerRepo.UpdateCustomer(customerId, customer);
        System.out.println(res ? "Customer updated successfully" : " failed to update a customer");

    }


    public List<Customer> showCustomers() throws SQLException {
        return customerRepo.showCustomers();

    }


    public Customer authenticate(String email, String password) throws SQLException {
        Customer customer = customerRepo.findByEmail(email);
        if (customer != null && customer.authenticate(password)) {

            return customer;
        }
        return null;
    }
}