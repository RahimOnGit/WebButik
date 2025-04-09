package repository;

import model.Customer;

import java.sql.SQLException;

public interface CustomerRepo {
    boolean addCustomer(Customer customer) throws SQLException;
    void UpdateCustomer(int customerId ,Customer customer) throws SQLException;

}
