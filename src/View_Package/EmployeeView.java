package View_Package;


import Controller_Package.UsersController;
import Model_Package.Lines;
import Model_Package.LinesMethod;
import Model_Package.Users;
import Model_Package.UserMethod;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class EmployeeView extends javax.swing.JFrame implements java.util.Observer {
    public UserMethod userMethod;
    public Users u;

    public LinesMethod linesMethod;
    public Lines l;

    public ArrayList<Lines> linesDatabase;

    public DefaultTableModel linesTable;

    public Lines getLineObjectForDelete(){
        Lines l=new Lines();
        l.setId(Integer.parseInt(lineIdTextField.getText()));
        return l;
    }
    public Lines getLineObject(){
        Lines l=new Lines();
        generateLineFromFields(l);
        return l;
    }

    private void generateLineFromFields(Lines l) {
        l.setId(Integer.parseInt(lineIdTextField.getText()));
        l.setNumber(lineNumberTextField.getText());
        l.setFinalStation(lineFinalStationTextFiled.getText());
        l.setStartStation(lineStartStationTextField.getText());
        l.setStations(lineStationsTextFiled.getText());

    }

    public void addLineDeleteListener(ActionListener a){
        deleteLineButton.addActionListener(a);
    }

    public void addLineUpdateListener(ActionListener a){
        updateLineButton.addActionListener(a);
    }

    public void addLineCreateListener(ActionListener a){
        createLineButton.addActionListener(a);
    }

    public EmployeeView(UserMethod userService, LinesMethod lineService){
        this.linesMethod=lineService;
        this.userMethod=userService;
        linesDatabase = this.linesMethod.getLinesDatabase();
        for (Lines lines : linesDatabase) {
            lines.addObserver(this);
        }
        initComponents();
        refreshInfo();
    }

    public void refreshInfo(){
        linesTable.setRowCount(0);
        this.addEntriesInTable(linesTable);
    }

    public JTextField lineIdTextField;
    public JTextField lineNumberTextField;
    public JTextField lineFinalStationTextFiled;
    public JTextField lineStartStationTextField;
    public JTextField lineStationsTextFiled;

    public JButton deleteLineButton = new JButton("Delete");
    public JButton updateLineButton = new JButton("Update");
    public JButton createLineButton = new JButton("Insert");
    public JButton userViewButton;
    public JButton roLanguage;
    public JButton enLanguage;
    public JButton krButton;

    private void createLineFields(){
        JLabel lineIdLabel = new JLabel("Line Id: ");
        lineIdLabel.setBounds(630, 20, 130, 25);
        JLabel lineNumberLabel = new JLabel("Number: ");
        lineNumberLabel.setBounds(630, 50, 130, 25);
        JLabel lineFinalStationLabel = new JLabel("Final Station: ");
        lineFinalStationLabel.setBounds(630, 80, 130, 25);
        JLabel lineStartStationLabel = new JLabel("Start Station: ");
        lineStartStationLabel.setBounds(630, 110, 130, 25);
        JLabel lineStationsLabel = new JLabel("Stations:");
        lineStationsLabel.setBounds(630, 140, 130, 25);

        lineIdTextField = new JTextField();
        lineIdTextField.setBounds(760, 20, 100, 25);
        lineNumberTextField = new JTextField();
        lineNumberTextField.setBounds(760, 50, 100, 25);
        lineFinalStationTextFiled = new JTextField();
        lineFinalStationTextFiled.setBounds(760, 80, 100, 25);
        lineStartStationTextField = new JTextField();
        lineStartStationTextField.setBounds(760, 110, 100, 25);
        lineStationsTextFiled = new JTextField();
        lineStationsTextFiled.setBounds(760, 140, 100, 25);

        deleteLineButton.setBounds(630, 200,200, 50);
        updateLineButton.setBounds(630, 250,200, 50);
        createLineButton.setBounds(630, 300,200, 50);

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
                deleteLineButton.setText("Sterge Linie");
                updateLineButton.setText("Actualizeaza Linie");
                createLineButton.setText("Adauga Linie");
                lineIdLabel.setText("ID Linie: ");
                lineNumberLabel.setText("Numar Linie: ");
                lineFinalStationLabel.setText("Statie Finala: ");
                lineStartStationLabel.setText("Statie Pornire: ");
                lineStationsLabel.setText("Statii: ");

            }
        });

        enLanguage = new JButton("EN");
        enLanguage.setBounds(730,450,100,25);
        enLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userViewButton.setText("Users View");
                deleteLineButton.setText("Delete");
                updateLineButton.setText("Update");
                createLineButton.setText("Insert");
                lineIdLabel.setText("Line Id: ");
                lineNumberLabel.setText("Number: ");
                lineFinalStationLabel.setText("Final Station: ");
                lineStartStationLabel.setText("Start Station: ");
                lineStationsLabel.setText("Stations: ");

            }
        });

        krButton = new JButton("KR");
        krButton.setBounds(530, 450, 100, 25);
        krButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineIdLabel.setText("라인 ID:");
                lineNumberLabel.setText("라인 번호:");
                lineStartStationLabel.setText("첫 번째 역:");
                lineFinalStationLabel.setText("최종 역:");
                lineStationsLabel.setText("역:");
                createLineButton.setText("더하다");
                deleteLineButton.setText("지우다");
                updateLineButton.setText("최신 정보");
                userViewButton.setText("사용자 페이지");
            }
        });
        add(krButton);


        add(lineIdLabel);
        add(lineNumberLabel);
        add(lineFinalStationLabel);
        add(lineStartStationLabel);
        add(lineStationsLabel);
        add(lineIdTextField);
        add(lineNumberTextField);
        add(lineFinalStationTextFiled);
        add(lineStartStationTextField);
        add(lineStationsTextFiled);
        add(deleteLineButton);
        add(updateLineButton);
        add(createLineButton);
        add(userViewButton);
        add(roLanguage);
        add(enLanguage);
    }

    public void initComponents(){
        setResizable(false);
        setTitle("Employee View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        tableBuild();
        this.setSize(900, 550);
        createLineFields();
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Lines) {
            refreshTable();
        }
    }

    public void tableBuild(){
        String[] column ={"Line ID","Number","Final Station", "Start Station", "Stations"};
        linesTable =new DefaultTableModel(column,0);
        JTable jt=new JTable(linesTable);
        addEntriesInTable(linesTable);
        JScrollPane sp=new JScrollPane(jt);
        sp.setBounds(0,0,600,300);
        add(sp);
    }
    public void refreshTable(){
        linesTable.setRowCount(0);
        addEntriesInTable(linesTable);
    }
    private void addEntriesInTable(DefaultTableModel linesTable) {
        for (Lines l : linesDatabase) {
            int id = ((Lines) l).getId();
            String number = ((Lines) l).getNumber();
            String finalStation = ((Lines) l).getFinalStation();
            String startStation = ((Lines) l).getStartStation();
            String stations = ((Lines) l).getStations();

            Object[] data = {id, number, finalStation, startStation, stations};
            linesTable.addRow(data);
        }
    }


}