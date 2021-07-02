package Controller_Package;


import Model_Package.LinesMethod;
import View_Package.EmployeeView;
import View_Package.UsersView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeController {
    private EmployeeView view;
    private UsersView usersView;
    private LinesMethod linesMethod;


    public EmployeeController(EmployeeView view, LinesMethod linesMethod) {
        this.view = view;
        this.linesMethod=linesMethod;

        view.addLineDeleteListener(new deleteLineListener());
        view.addLineCreateListener(new createLineListener());
        view.addLineUpdateListener(new updateLineListener());
    }

    private void showUserMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }

    class deleteLineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deleteLineAction();
            view.refreshInfo();
        }
    }

    private void deleteLineAction() {
        try {
            boolean status = view.linesMethod.deleteInDatabase(view.getLineObjectForDelete());
            if(status){
                showUserMessage("Deleted Successfully");
            }else{
                showUserMessage("Deleting Failed");
            }
            view.linesDatabase =view.linesMethod.getLinesDatabase();
            view.setBackground(Color.pink);
            view.refreshTable();
        }catch(Exception ex){
            showUserMessage("Deleting Failed: Wrong input");
        }
        view.refreshInfo();
    }

    class createLineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createLineAction();
            view.refreshInfo();
        }
    }

    private void createLineAction() {
        try {
            boolean status=view.linesMethod.insertInDatabase(view.getLineObject());
            if(status){
                showUserMessage("Creation Successfully");
            }else{
                showUserMessage("Creating Failed");
            }
            view.linesDatabase =view.linesMethod.getLinesDatabase();
            view.refreshTable();
        }catch(Exception ex){
            showUserMessage("Creating Failed: Wrong input");
        }
        view.refreshInfo();
    }

    class updateLineListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                boolean status=view.linesMethod.updateInDatabase(view.getLineObject());
                if(status){
                    showUserMessage("Updated Successfully");
                }else{
                    showUserMessage("Updating Failed");
                }
            }catch(Exception ex){
                showUserMessage("Updating Failed: Wrong input");
            }
        }
    }
}
