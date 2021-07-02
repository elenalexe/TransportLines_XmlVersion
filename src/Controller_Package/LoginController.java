package Controller_Package;

import Model_Package.EmployeesMethod;
import Model_Package.LinesMethod;
import Model_Package.UserMethod;
import View_Package.AdminView;
import View_Package.EmployeeView;
import View_Package.LoginView;
import View_Package.UsersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private UserMethod userMethod;
    private LinesMethod linesMethod;
    private EmployeesMethod employeesMethod;

    public LoginController(LoginView view, UserMethod userMethod, LinesMethod linesMethod, EmployeesMethod employeesMethod) {
        this.loginView = view;
        this.userMethod = new UserMethod();
        this.linesMethod = new LinesMethod();
        this.employeesMethod = new EmployeesMethod();

        this.loginView.loginAttemptListener(new loginListener());
    }
    public void loginFlow(String role){
        loginView.disappear();

        if(role.equals("admin")){
            AdminView adminView = new AdminView(userMethod, employeesMethod);
            AdminController ac = new AdminController(adminView, employeesMethod);
        }
        if(role.equals("employee")) {
            EmployeeView ev = new EmployeeView(userMethod, linesMethod);
            EmployeeController ac = new EmployeeController(ev, linesMethod);
            EmployeeView employeeView = new EmployeeView(userMethod, linesMethod);
            EmployeeController ec = new EmployeeController(employeeView, linesMethod);
        }
    }

    class loginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String user = loginView.userText.getText();
            String password = loginView.passwordText.getText();
            System.out.println(user + "," + password);
            String role="";
            boolean rol= loginView.user_checkbox.isSelected();
            if(rol){
                role="employee";
            }else{
                role="admin";
            }
            if(  (!loginView.admin_checkbox.isSelected() && !loginView.user_checkbox.isSelected())
                    || user.equals(" ")
                    || user.equals("")
                    || password.equals(" ")
                    || password.equals("")
            ) {
                loginView.success.setText(("Information incomplete! "));
            } else {
                if (userMethod.matchUserByCombination(user,password,role).getId() != 0) {
                    loginView.success.setText("Login successful!");
                    if (role.equals("employee")) {
                        JOptionPane.showMessageDialog(loginView.frame, "Successfully logged in as employee.");
                    } else {
                        JOptionPane.showMessageDialog(loginView.frame, "Successfully logged in as admin.");
                    }
                    loginFlow(role);
                }
                else {
                    JOptionPane.showMessageDialog(loginView.frame, "Login fail!");
                }
            }
        }
    }
}
