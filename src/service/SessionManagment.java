package service;

import controller.CustomerController;
import model.Customer;
import repository.Imp.SqlCustomerRep;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//VG
public class SessionManagment {
    CustomerService customerService = new CustomerService(new SqlCustomerRep());
    CustomerController customerController = new CustomerController();

    public Customer login(String email, String password) throws SQLException {
        List<Customer> customer = new CustomerService(new SqlCustomerRep()).showCustomers();


           Customer loggedCustomer = customerService.authenticate(email, password);

            if (loggedCustomer != null) {
                System.out.println("Logged in successfully");
                System.out.println("Hi , " + loggedCustomer.getName());

                createSession(loggedCustomer);


           }
           else  {
                System.out.println("Can't log in ..check the email and the password...\n");

                }
           return loggedCustomer;

    }


    public void createSession(Customer loggedCustomer) throws SQLException {

        System.out.println("create session for user");
        UserSession session = new UserSession(loggedCustomer);
        System.out.println("session created successfully "+ session.getSessionId());
        checkSession(session);



    }
   public void checkSession(UserSession session) throws SQLException {
       if(session.isValid())
       {
           System.out.println("session valid");
           System.out.println("logged in user : "+session.getCustomer().getName());
       }
       else
       {
           System.out.println("session invalid");
       }
   }

//just need to implement this
   public void stimulateSessionTimeout(UserSession session) throws SQLException {
       System.out.println("session timeout");
       session.invalidate();
       if(!session.isValid())
       {
           System.out.println("session successfully invalidated");
       }
       else {
           System.out.println("Failed to invalidate session");
       }
   }




    private static class UserSession {
        private String sessionId;
        private Customer customer;
        private boolean valid;

        public UserSession(Customer customer) {
            this.sessionId = "SESSION_" + System.currentTimeMillis();
            this.customer = customer;
            this.valid = true;
        }

        public String getSessionId() {
            return sessionId;
        }

        public Customer getCustomer() {
            return customer;
        }

        public boolean isValid() {
            return valid;
        }

        public void invalidate() {
            this.valid = false;
        }
    }


}

