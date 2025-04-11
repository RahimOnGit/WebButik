package service;

import model.Customer;
import repository.CustomerRepo;
import repository.Imp.SqlCustomerRep;

import java.sql.SQLException;

public class CustomerService extends SqlCustomerRep {
    private final SqlCustomerRep customerRepo;

    public CustomerService(SqlCustomerRep customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void registerCustomer(Customer customer) throws SQLException {

             boolean res = customerRepo.addCustomer(customer);
            System.out.println(res?"Customer added successfully":" failed to add a customer");

    }

    public void updateCustomer(int customerId,Customer customer) throws SQLException {

            boolean res =  customerRepo.UpdateCustomer(customerId,customer);
        System.out.println(res?"Customer updated successfully":" failed to update a customer");

        }





    public void showCustomers() throws SQLException {
        customerRepo.showCustomers();
    }
}