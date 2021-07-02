package Model_Package;

import java.util.Observable;

public class   Users extends Observable {
    public int id;
    public String password;
    public String role;
    public String username;

    public Users(int id, String password, String role, String username) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.username = username;
    }
    public Users(){
        this.id=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setChanged();
        notifyObservers();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        setChanged();
        notifyObservers();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        setChanged();
        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        setChanged();
        notifyObservers();
    }
}
