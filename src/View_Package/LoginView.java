package View_Package;

import Controller_Package.UsersController;
import Model_Package.LinesMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class LoginView extends JFrame implements Observer {
    public  JLabel userlabel;
    public  JTextField userText;
    public  JLabel passwordLabel;
    public  JPasswordField passwordText;
    public  JButton loginButton;
    public  JLabel success;
    public JButton userViewButton;
    public  JRadioButton user_checkbox;
    public  JRadioButton admin_checkbox;
    public JButton roLanguage;
    public JButton enLanguage;
    public  JFrame frame;
    public JButton krButton;

    private JPanel panel;
    public void disappear(){
        this.dispose();
    }
    public LoginView(){
        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setBackground(Color.pink);
        frame.setTitle("Login Window");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        userlabel = new JLabel("User");
        userlabel.setBounds(10, 20, 80, 25);
        panel.add(userlabel);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        admin_checkbox = new JRadioButton("admin");
        admin_checkbox.setBounds(150, 80, 80, 25);
        panel.add(admin_checkbox);
        admin_checkbox.setVisible(true);

        user_checkbox = new JRadioButton("employee");
        user_checkbox.setBounds(250, 80, 80, 25);
        panel.add(user_checkbox);
        user_checkbox.setVisible(true);

        ButtonGroup buttons=new ButtonGroup();
        buttons.add(admin_checkbox);
        buttons.add(user_checkbox);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        roLanguage = new JButton("RO");
        roLanguage.setBounds(100,130,80,25);
        roLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userlabel.setText("Utilizator");
                passwordLabel.setText("Parola");
                loginButton.setText("Logare");
                admin_checkbox.setText("Admin");
                user_checkbox.setText("Angajat");
                userViewButton.setText("Utilizatori");



            }
        });

        enLanguage = new JButton("EN");
        enLanguage.setBounds(200,130,80,25);
        enLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userlabel.setText("User");
                passwordLabel.setText("Password");
                admin_checkbox.setText("admin");
                loginButton.setText("Login");
                user_checkbox.setText("employee");
                userViewButton.setText("Users View");


            }
        });

        krButton = new JButton("KR");
        krButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userlabel.setText("사용자 이름:");
                passwordLabel.setText("암호:");
                admin_checkbox.setText("관리자");
                user_checkbox.setText("종업원");
                loginButton.setText("로그인");
                userViewButton.setText("사용자보기");
            }
        });
        krButton.setBounds(10,130,80,25);
        panel.add(krButton);
        panel.add(roLanguage);
        panel.add(enLanguage);

        userViewButton = new JButton("Users View");
        userViewButton.setBounds(120,170,100,25);
        userViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersView usersView = new UsersView(new LinesMethod());
                UsersController uc = new UsersController(usersView, new LinesMethod());
                usersView.setVisible(true);
            }
        });
        panel.add(userViewButton);


        success = new JLabel("");
        success.setBounds(150, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }
    public void loginAttemptListener(ActionListener a){
        loginButton.addActionListener(a);
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
