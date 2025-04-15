package model;

public abstract class User {
    private int id;
    private String password;

    public User() {
    }

    public User(int id, String password) {
        this.id = id;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract boolean Authenticate(String password);

@Override
    public String toString() {
    return "User {"+id+", "+password+"}";
}
}
