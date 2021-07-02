package Default_Package;

import Controller_Package.LoginController;


import Model_Package.EmployeesMethod;
import Model_Package.LinesMethod;
import Model_Package.UserMethod;
import View_Package.LoginView;

public class Main{
    public static void main(String[] args)
    {
        UserMethod userMethod = new UserMethod();
        LinesMethod linesMethod = new LinesMethod();
        EmployeesMethod employeesMethod = new EmployeesMethod();

        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginView, userMethod, linesMethod, employeesMethod);
    }

}
