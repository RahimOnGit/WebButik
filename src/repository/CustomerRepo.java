package repository;

import model.Customer;

public interface CustomerRepo {
    void addCustomer(Customer customer);
    void UpdateCustomer(int customerId ,Customer customer);

}
