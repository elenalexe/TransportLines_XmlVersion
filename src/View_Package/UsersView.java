package View_Package;

import Model_Package.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;

public class UsersView extends javax.swing.JFrame implements java.util.Observer {
    LinesMethod lineService;
    public UserMethod userMethod;
    public Users u;

    public ArrayList<Lines> listaLinii;
    public DefaultTableModel linii;
    JTextField searchString;
    JRadioButton numberRadio;
    JRadioButton finishStationRadio;
    JRadioButton startStationRadio;
    public JPanel panel;
    public JButton jbSearch;
    public JButton roLanguage;
    public JButton enLanguage;
    public JButton krButton;


    public String getSearchTerm(){
        if(numberRadio.isSelected()){
            return "number";
        }
        if(finishStationRadio.isSelected()){
            return "finalStation";
        }
        if(startStationRadio.isSelected()){
            return "startStation";
        }
        return "";
    }

    public String getSearchString(){
        return searchString.getText();
    }

    public void addLineSearchListener(ActionListener a) {
        jbSearch.addActionListener(a);
    }

    public UsersView(LinesMethod lineService) {
        this.lineService=lineService;
        listaLinii=lineService.getLinesDatabase();
        initComponents();
        setTitle("Users View");
        for (Lines lines : listaLinii) {
            lines.addObserver(this);
        }
        refreshInfo();
    }

    public void refreshInfo() {
        linii.setRowCount(0);
        this.dateLinii(linii);
    }

    private void initComponents() {
        panel = new JPanel();

        String[] column ={"Line ID","Number","Final Station", "Start Station", "Stations"};
        linii = new DefaultTableModel(column, 0);
        dateLinii(linii);
        JTable tabelLinii = new JTable(linii);
        tabelLinii.setBackground(Color.white);
        JScrollPane scrollTabel = new JScrollPane(tabelLinii);
        scrollTabel.setBounds(0,0,600,300);
        panel.add(scrollTabel);
        this.setVisible(true);

        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setBounds(630, 30, 100, 25);
        panel.add(searchLabel);

        searchString = new JTextField();
        searchString.setBounds(730, 30, 100, 25);
        panel.add(searchString);

        numberRadio =new JRadioButton("Line Number");
        numberRadio.setBounds(630, 55, 150, 25);
        finishStationRadio =new JRadioButton("Final Station");
        finishStationRadio.setBounds(630, 100, 150, 25);
        startStationRadio =new JRadioButton("Start Station");
        startStationRadio.setBounds(630, 155, 150, 25);

        jbSearch=new JButton("Search");
        jbSearch.setBounds(630, 200, 225, 25);

        roLanguage = new JButton("RO");
        roLanguage.setBounds(700,250,80,25);
        roLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchLabel.setText("Cauta");
                numberRadio.setText("Numar Linie");
                finishStationRadio.setText("Statie Finala");
                startStationRadio.setText("Statie Pornire");
                jbSearch.setText("Cauta");



            }
        });

        enLanguage = new JButton("EN");
        enLanguage.setBounds(800,250,80,25);
        enLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchLabel.setText("Search:");
                numberRadio.setText("Line Number");
                finishStationRadio.setText("Final Station");
                startStationRadio.setText("Start Station");
                jbSearch.setText("Search");


            }
        });

        krButton = new JButton("KR");
        krButton.setBounds(600, 250, 80, 25);
        krButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchLabel.setText("검색:");
                numberRadio.setText("라인 번호:");
                startStationRadio.setText("첫 번째 역:");
                finishStationRadio.setText("최종 역:");
                jbSearch.setText("검색");

            }
        });
        panel.add(krButton);

        ButtonGroup buttons=new ButtonGroup();
        buttons.add(startStationRadio);
        buttons.add(finishStationRadio);
        buttons.add(numberRadio);

        add(jbSearch);

        add(finishStationRadio);
        add(startStationRadio);
        add(numberRadio);
        add(roLanguage);
        add(enLanguage);


        this.setSize(900, 400);
        this.add(panel);
        panel.setLayout(null);
    }

    private void dateLinii(DefaultTableModel liniiTable) {
        for (Lines l : listaLinii) {
            int id = l.getId();
            String number = l.getNumber();
            String finalStation = l.getFinalStation();
            String startStation = l.getStartStation();
            String stations = l.getStations();

            Object[] data = {id, number, finalStation, startStation, stations};
            liniiTable.addRow(data);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("IN UPDATE");
        refreshInfo();
    }
}
