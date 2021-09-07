package com.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.config.ConnectionDb;
import com.utility.Game;

public class Menu extends JFrame implements ActionListener {
    
    private Vector<Game> gamesData;
    private ConnectionDb connectionDb;

    private JPanel headerPanel, midPanel, contentPanel, viewPanel, transactionPanel;
    private JLabel headerLbl;
    private JButton transactionBtn, viewBtn;

    public Menu(Vector<Game> gamesData, ConnectionDb connectionDb) {
        this.gamesData = gamesData;
        this.connectionDb = connectionDb;
        
        //fill data to vector
        fillDataVector();

        //header
        headerPanel = new JPanel();
        headerLbl = new JLabel("Game Shop");
        headerLbl.setFont(new Font("", Font.BOLD, 16));
        headerLbl.setBorder(new EmptyBorder(30, 0, 20, 0));
        headerPanel.add(headerLbl);

        //content
        midPanel = new JPanel();
        contentPanel = new JPanel(new GridLayout(2,1));
       
        viewBtn = new JButton("View");
        viewBtn.setPreferredSize(new Dimension(130, 30));
        viewPanel = new JPanel();
        viewPanel.add(viewBtn);
        viewPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        transactionBtn = new JButton("Transaction");
        transactionBtn.setPreferredSize(new Dimension(130, 30));
        transactionPanel = new JPanel();
        transactionPanel.add(transactionBtn);

        contentPanel.add(viewPanel);
        contentPanel.add(transactionPanel);
        midPanel.add(contentPanel);        
        
        
        //action listener button
        viewBtn.addActionListener(this);
        transactionBtn.addActionListener(this);


        //add all
        add(headerPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);

        //coloring
        headerPanel.setBackground(Color.decode("#79B4B7"));
        midPanel.setBackground(Color.decode("#79B4B7"));
        viewPanel.setBackground(Color.decode("#79B4B7"));
        transactionPanel.setBackground(Color.decode("#79B4B7"));

        settings();
    }

    private void settings() {
        setTitle("Main Menu");
		// setLocationRelativeTo(null);
        setLocation(450, 100);
		setSize(300, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
    }

    private void fillDataVector() {
        try {
            connectionDb.resultSet = connectionDb.getGamesData();
            while(connectionDb.resultSet.next()) {
                int id = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(1)));
                String name = String.valueOf(connectionDb.resultSet.getObject(2)); 
                String developerName = String.valueOf(connectionDb.resultSet.getObject(3)); 
                String genre = String.valueOf(connectionDb.resultSet.getObject(4)); 
                int price = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(5)));

                gamesData.add(new Game(id, name, developerName, genre, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewBtn) {
            //go to view.java
            setVisible(false);
            new View(gamesData, connectionDb);

        }
        else if(e.getSource() == transactionBtn) {
            //go to transaction.java
            setVisible(false);
            new Transaction(gamesData, connectionDb);
        }
    }

}