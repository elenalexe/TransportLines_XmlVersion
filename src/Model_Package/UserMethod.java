package Model_Package;

import Default_Package.XmlDatabase;

import java.util.ArrayList;

public class UserMethod {
    private static ArrayList<Users> userDatabase=new ArrayList<>();

    public UserMethod(){
        if(userDatabase.size() == 0) {
            userDatabase = XmlDatabase.getUsersFromXml();
        }
    }

    private void updateUser(Users u, Users user) {
        user.setUsername(u.getUsername());
        user.setRole(u.getRole());
        user.setPassword(u.getPassword());
    }

    public Users matchUserByCombination(String username, String password, String role) {
        for (Users u : userDatabase) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password) && u.getRole().equals(role)){
                return u;
            }
        }
        return new Users();
    }

    public ArrayList<Users> getUserDatabase() {
        return userDatabase;
    }
}
