package service;

import controller.Colors;
import controller.CustomerController;
import model.Customer;
import repository.Imp.SqlCustomerRep;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//VG
public class SessionManagment {
    CustomerService customerService = new CustomerService(new SqlCustomerRep());

    public Customer login(String email, String password) throws SQLException {
        Customer loggedCustomer = customerService.authenticate(email, password);

            if (loggedCustomer != null) {
                System.out.println(Colors.GREEN+"Logged in successfully"+Colors.RESET);
                System.out.println("Hi , " + loggedCustomer.getName());
                createSession(loggedCustomer);
            }
           else  {
                System.out.println(Colors.RED+"Can't log in ..check the email and the password...\n"+Colors.RESET);
                }
           return loggedCustomer;

    }


    public void createSession(Customer loggedCustomer){

        System.out.println("create session for user");
        UserSession session = new UserSession(loggedCustomer);
        checkSession(session);
        System.out.println("session created successfully "+ session.getSessionId());



    }
   public void checkSession(UserSession session) {
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






    private static class UserSession {
        private String sessionId;
        private Customer customer;
        private boolean valid;

        public UserSession(Customer customer) {
            this.sessionId = "SESSION:" + System.currentTimeMillis();
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

