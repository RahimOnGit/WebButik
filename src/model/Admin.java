package model;

import repository.Imp.SqlAdmin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User
{
private String username;

public Admin()
{
    super();
}


    public Admin(int id,String username, String password)
    {
        super(id,password);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean Authenticate(String password) {
    if(getPassword()==null)
    {
        System.out.println("Password is empty");
        return false;
    }
        return getPassword().equals(password);
    }


    @Override
    public String toString() {
    return "Admin {" +getId()+" , "+username+" }";
    }

    public boolean validate(int adminId, String password) throws SQLException
    {
        List<Admin> admins;
        admins = new SqlAdmin().getAdmins();

        for(Admin admin : admins)
        {
            if(admin.getId()==adminId)
            {
                return admin.Authenticate(password);
            }

        }
        return false;
    }


}
