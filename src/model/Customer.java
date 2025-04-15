package model;

public class Customer extends User {
    @Override
    public boolean Authenticate(String password) {
        return getPassword().equals(password);
    }

    int  id;
    String name;
    String address;
    String phone;
    String email;
    String password;

    public Customer(String name,String email, String phone, String address, String password) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Customer(int id, String name, String email, String phone, String address, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String password) {
        return this.getPassword()!= null && this.getPassword().equals(password);

    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
