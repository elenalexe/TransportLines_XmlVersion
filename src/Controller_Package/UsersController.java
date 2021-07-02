package Controller_Package;

import Model_Package.Lines;
import Model_Package.LinesMethod;
import Model_Package.UserMethod;
import View_Package.UsersView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersController {
    private UsersView view;
    private Lines model;
    private LinesMethod linesMethod;
    private UserMethod userMethod;

    public UsersController(UsersView view,LinesMethod linesMethod) {
        this.view = view;
        this.linesMethod=linesMethod;

        this.view.addLineSearchListener(new SearchLineListener());
    }
    class SearchLineListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.out.println("search string: "+ view.getSearchString()+ " search term:  "+ view.getSearchTerm());
            view.listaLinii=linesMethod.searchLine(view.getSearchString(), view.getSearchTerm());
            view.refreshInfo();
        }
    }
    private void showUserMessage(String message) {
        JOptionPane.showMessageDialog(view, message);
    }


}
