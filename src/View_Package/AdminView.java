package View_Package;

import Controller_Package.UsersController;
import Model_Package.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class AdminView extends javax.swing.JFrame implements java.util.Observer {

        public UserMethod userMethod;
        public Users u;

        public EmployeesMethod employeesMethod;
        public Employees e;

        public ArrayList<Employees> employeesDatabase;

        public DefaultTableModel employeesTable;

        public Employees getEmployeeObjectForDelete(){
            Employees e=new Employees();
            e.setId(Integer.parseInt(employeeIdTextField.getText()));
            return e;
        }
        public Employees getEmployeeObject(){
            Employees e = new Employees();
            generateEmployeeFromFields(e);
            return e;
        }

        private void generateEmployeeFromFields(Employees e) {
            e.setId(Integer.parseInt(employeeIdTextField.getText()));
            e.setName(nameTextField.getText());
            e.setEmail(emailTextField.getText());
            e.setBirthDate(birthDateTextFiled.getText());
        }

        public void addEmployeeDeleteListener(ActionListener a){
            deleteEmployeeButton.addActionListener(a);
        }

        public void addEmployeeUpdateListener(ActionListener a){
            updateEmployeeButton.addActionListener(a);
        }

        public void addEmployeeCreateListener(ActionListener a){
            createEmployeeButton.addActionListener(a);
        }

        public AdminView(UserMethod userService, EmployeesMethod employeeService){
            this.employeesMethod=employeeService;
            this.userMethod=userService;
            employeesDatabase = this.employeesMethod.getEmployeesDatabase();
            for (Employees employees : employeesDatabase) {
                employees.addObserver(this);
            }
            initComponents();
            refreshTable();
        }

        public JTextField employeeIdTextField;
        public JTextField emailTextField;
        public JTextField nameTextField;
        public JTextField birthDateTextFiled;

        public JButton deleteEmployeeButton = new JButton("Delete");
        public JButton updateEmployeeButton = new JButton("Update");
        public JButton createEmployeeButton = new JButton("Insert");
        public JButton userViewButton;
        public JButton roLanguage;
        public JButton enLanguage;
        public JButton krLanguage;

        private void createEmployeeFields(){
            JLabel employeeIdLabel = new JLabel("Employee Id: ");
            employeeIdLabel.setBounds(630, 20, 130, 25);
            JLabel employeeNameLabel = new JLabel("Name: ");
            employeeNameLabel.setBounds(630, 50, 130, 25);
            JLabel emailLabel = new JLabel("Email: ");
            emailLabel.setBounds(630, 80, 130, 25);
            JLabel birthdateLabel = new JLabel("Birthday: ");
            birthdateLabel.setBounds(630, 110, 130, 25);

            employeeIdTextField = new JTextField();
            employeeIdTextField.setBounds(760, 20, 100, 25);
            nameTextField = new JTextField();
            nameTextField.setBounds(760, 50, 100, 25);
            emailTextField = new JTextField();
            emailTextField.setBounds(760, 80, 100, 25);
            birthDateTextFiled = new JTextField();
            birthDateTextFiled.setBounds(760, 110, 100, 25);

            deleteEmployeeButton.setBounds(630, 200,200, 50);
            updateEmployeeButton.setBounds(630, 250,200, 50);
            createEmployeeButton.setBounds(630, 300,200, 50);

            userViewButton = new JButton("Users View");
            userViewButton.setBounds(630,400,200,25);
            userViewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UsersView usersView = new UsersView(new LinesMethod());
                    UsersController uc = new UsersController(usersView, new LinesMethod());
                    usersView.setVisible(true);
                }
            });

            roLanguage = new JButton("RO");
            roLanguage.setBounds(630,450,100,25);
            roLanguage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userViewButton.setText("Utilizatori");
                    deleteEmployeeButton.setText("Sterge Angajat");
                    updateEmployeeButton.setText("Actualizeaza Angajat");
                    createEmployeeButton.setText("Adauga Angajat");
                    employeeIdLabel.setText("ID Angajat: ");
                    employeeNameLabel.setText("Nume: ");
                    emailLabel.setText("Email: ");
                    birthdateLabel.setText("Data nasterii: ");


                }
            });

            enLanguage = new JButton("EN");
            enLanguage.setBounds(730,450,100,25);
            enLanguage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userViewButton.setText("Users View");
                    deleteEmployeeButton.setText("Delete");
                    updateEmployeeButton.setText("Update");
                    createEmployeeButton.setText("Insert");
                    employeeIdLabel.setText("Employee Id: ");
                    employeeNameLabel.setText("Name: ");
                    emailLabel.setText("Email: ");
                    birthdateLabel.setText("Birthday: ");


                }
            });

            krLanguage = new JButton("KR");
            krLanguage.setBounds(530,450,100,25);
            krLanguage.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    employeeIdLabel.setText("직원 ID:");
                    employeeNameLabel.setText("이름:");
                    emailLabel.setText("이메일:");
                    birthdateLabel.setText("생신:");
                    createEmployeeButton.setText("더하다");
                    deleteEmployeeButton.setText("지우다");
                    updateEmployeeButton.setText("최신 정보");
                    userViewButton.setText("사용자 페이지");
                }
            });



            add(employeeIdLabel);
            add(employeeNameLabel);
            add(emailLabel);
            add(birthdateLabel);
            add(employeeIdTextField);
            add(nameTextField);
            add(emailTextField);
            add(birthDateTextFiled);
            add(deleteEmployeeButton);
            add(updateEmployeeButton);
            add(createEmployeeButton);
            add(userViewButton);
            add(roLanguage);
            add(enLanguage);
            add(krLanguage);
        }

        public void initComponents(){
            setResizable(false);
            setTitle("Admin View");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            tableBuild();
            this.setSize(900, 550);
            createEmployeeFields();
            setLayout(null);
            setVisible(true);
        }
        @Override
        public void update(Observable o, Object arg) {
            if (o instanceof Employees) {
                refreshTable();
            }
        }

        public void tableBuild(){
            String[] column ={"Employee ID","Name","Email", "Birthday"};
            employeesTable =new DefaultTableModel(column,0);
            JTable jt=new JTable(employeesTable);
            addEntriesInTable();
            JScrollPane sp=new JScrollPane(jt);
            sp.setBounds(0,0,600,300);
            add(sp);
        }
        public void refreshTable(){
            employeesTable.setRowCount(0);
            addEntriesInTable();
        }
        private void addEntriesInTable() {
            for (Employees e : employeesDatabase) {
                int id = ((Employees) e).getId();
                String name = ((Employees) e).getName();
                String email = ((Employees) e).getEmail();
                String birthday = ((Employees) e).getBirthDate();

                Object[] data = {id, name, email, birthday};
                employeesTable.addRow(data);
            }
        }


    }

