package Classes;

import java.sql.Connection;

import javafx.collections.ObservableList;

public abstract class Person {
    private String username;
    private String email;
    private String password;
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public ObservableList<String> getAllInstitute(Connection connection) {
        Institute institute = new Institute();
        return institute.getAllInstitute(connection);
    }

    public ObservableList<String> getAllBranch(Connection connection) {
        Institute institute = new Institute();
        return institute.getAllBranch(connection);
    }
    
    public abstract boolean Login(Connection connection);
}