package Controller_Package;

import Model_Package.EmployeesMethod;
import View_Package.AdminView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController {

        private AdminView view;
        private EmployeesMethod employeesMethod;


        public AdminController(AdminView view, EmployeesMethod employeesMethod) {
            this.view = view;
            this.employeesMethod = employeesMethod;

            view.addEmployeeDeleteListener(new deleteEmployeeListener());
            view.addEmployeeCreateListener(new createEmployeeListener());
            view.addEmployeeUpdateListener(new updateEmployeeListener());
        }

        private void showUserMessage(String message) {
            JOptionPane.showMessageDialog(view, message);
        }

        class deleteEmployeeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                deleteEmployeeAction();
            }
        }

        private void deleteEmployeeAction() {
            try {
                boolean status = view.employeesMethod.deleteInDatabase(view.getEmployeeObjectForDelete());
                if(status){
                    showUserMessage("Deleted Successfully");
                }else{
                    showUserMessage("Deleting Failed");
                }
                view.employeesDatabase =view.employeesMethod.getEmployeesDatabase();
                view.setBackground(Color.blue);
                view.refreshTable();
            }catch(Exception ex){
                showUserMessage("Deleting Failed: Wrong input");
            }
        }

        class createEmployeeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                createEmployeeAction();
            }
        }

        private void createEmployeeAction() {
            try {
                boolean status=view.employeesMethod.insertInDatabase(view.getEmployeeObject());
                if(status){
                    showUserMessage("Creation Successfully");
                }else{
                    showUserMessage("Creating Failed");
                }
                view.employeesDatabase =view.employeesMethod.getEmployeesDatabase();
                view.refreshTable();
            }catch(Exception ex){
                showUserMessage("Creating Failed: Wrong input");
            }
        }

        class updateEmployeeListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean status=view.employeesMethod.updateInDatabase(view.getEmployeeObject());
                    if(status){
                        showUserMessage("Updated Successfully");
                    }else{
                        showUserMessage("Updating Failed");
                    }
                }catch(Exception ex){
                    showUserMessage("Update Failed: Wrong input");
                }
            }
        }
    }

