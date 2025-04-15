package repository.Imp;

import model.Admin;
import repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlAdmin {
    DatabaseConnection db = new DatabaseConnection();

    public List<Admin> getAdmins() throws SQLException
    {
        List<Admin> admins = new ArrayList<Admin>();
        Admin admin = new Admin();
        try(Connection conn = db.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement("select * from admins");
            try(ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    admin.setId(rs.getInt(1));
                    admin.setUsername(rs.getString(2));
                    admin.setPassword(rs.getString(3));
                admins.add(admin);
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("failed to open the database "+e.getMessage());
        }


    return admins;
    }


    public void insertAdmin(Admin admin) throws SQLException
    {
        try(Connection conn = db.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement("insert into admins(username,password) values(?,?)");
            ps.setString(1, admin.getUsername());
            ps.setString(2, admin.getPassword());
            ps.executeUpdate();
            System.out.println(admin.getUsername()+" inserted successfully ");
        } catch (Exception e) {
            System.out.println("error in insertAdmin()"+e.getMessage());
        }

    }

}
