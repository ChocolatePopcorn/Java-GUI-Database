package com.project;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;

import com.config.ConnectionDb;
import com.utility.Game;
import com.utility.Receipt;


public class Transaction extends JFrame implements ActionListener, MouseInputListener {

	private Vector<Game> gamesData;
	private Vector<Receipt> receiptsData;
	private ConnectionDb connectionDb;

	private JPanel headerPanel, midPanel, contentPanel, formPanel, tablePanel, botPanel, bot1Panel, bot2Panel, payBtnPanel, clearBtnPanel,
	nameLblPanel, developerLblPanel, genreLblPanel, priceLblPanel, qtyLblPanel, amountPriceLblPanel,
	nameTxtFieldPanel, developerTxtFieldPanel, genreTxtFieldPanel, priceTxtFieldPanel, qtyTxtFieldPanel, amountPriceTxtFieldPanel;
	private JLabel headerLbl, nameLbl, developerLbl, genreLbl, priceLbl, qtyLbl, amountPriceLbl;
	private JTextField nameTxtField, developerTxtField, genreTxtField, priceTxtField, qtyTxtField, amountPriceTxtField;

    private JTable dataTable;
	private JScrollPane scrollPane;
	private JButton payBtn, clearBtn, backBtn;

	private Vector<Object> column, row;
	private Vector<Vector<Object>> data;
	private Vector<String> receiptFormat;

	String qtyStr, amountPriceStr, tempQty, tempAmountPrice, totalPaymentStr;
	int calculatePayment;

    public Transaction(Vector<Game> gamesData, ConnectionDb connectionDb) {
        this.gamesData = gamesData;
		this.connectionDb = connectionDb;

        //Header
		headerPanel = new JPanel();
		headerPanel.setBorder(new EmptyBorder(20, 0, 30, 0));
		headerLbl = new JLabel("Transaction");
		headerLbl.setFont(new Font("", Font.BOLD, 16));
		headerPanel.add(headerLbl);

        midPanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(1,3));
        
		
		//left side
		formPanel = new JPanel(new GridLayout(6,2));
		
		nameLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		nameLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		nameTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		nameLbl = new JLabel("Game Name: ");
		nameLbl.setFont(new Font("", Font.PLAIN, 14));
		nameLblPanel.add(nameLbl);
		nameTxtField = new JTextField();
		nameTxtField.setEditable(false);
		nameTxtField.setPreferredSize(new Dimension(130, 25));
		nameTxtFieldPanel.add(nameTxtField);


		developerLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		developerLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		developerTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		developerLbl = new JLabel("Developer Name: ");
		developerLbl.setFont(new Font("", Font.PLAIN, 14));
		developerLblPanel.add(developerLbl);
		developerTxtField = new JTextField();
		developerTxtField.setEditable(false);
		developerTxtField.setPreferredSize(new Dimension(130, 25));
		developerTxtFieldPanel.add(developerTxtField);
		

		genreLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		genreLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		genreTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		genreLbl = new JLabel("Game Genre: ");
		genreLbl.setFont(new Font("", Font.PLAIN, 14));
		genreLblPanel.add(genreLbl);
		genreTxtField = new JTextField();
		genreTxtField.setEditable(false);
		genreTxtField.setPreferredSize(new Dimension(130, 25));
		genreTxtFieldPanel.add(genreTxtField);


		priceLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		priceLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		priceTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		priceLbl = new JLabel("Game Price: ");
		priceLbl.setFont(new Font("", Font.PLAIN, 14));
		priceLblPanel.add(priceLbl);
		priceTxtField = new JTextField();
		priceTxtField.setEditable(false);
		priceTxtField.setPreferredSize(new Dimension(130, 25));
		priceTxtFieldPanel.add(priceTxtField);


		qtyLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		qtyLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		qtyTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		qtyLbl = new JLabel("Quantity: ");
		qtyLbl.setFont(new Font("", Font.PLAIN, 14));
		qtyLblPanel.add(qtyLbl);
		qtyTxtField = new JTextField();
		qtyTxtField.setPreferredSize(new Dimension(130, 25));
		qtyTxtFieldPanel.add(qtyTxtField);

		amountPriceLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		amountPriceLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		amountPriceTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		amountPriceLbl = new JLabel("Amount Money: ");
		amountPriceLbl.setFont(new Font("", Font.PLAIN, 14));
		amountPriceLblPanel.add(amountPriceLbl);
		amountPriceTxtField = new JTextField();
		amountPriceTxtField.setPreferredSize(new Dimension(130, 25));
		amountPriceTxtFieldPanel.add(amountPriceTxtField);


		formPanel.add(nameLblPanel);
		formPanel.add(nameTxtFieldPanel);
		formPanel.add(developerLblPanel);
		formPanel.add(developerTxtFieldPanel);
		formPanel.add(genreLblPanel);
		formPanel.add(genreTxtFieldPanel);
		formPanel.add(priceLblPanel);
		formPanel.add(priceTxtFieldPanel);
		formPanel.add(qtyLblPanel);
		formPanel.add(qtyTxtFieldPanel);
		formPanel.add(amountPriceLblPanel);
		formPanel.add(amountPriceTxtFieldPanel);
			 

		//right side
		column = new Vector<Object>();
		column.add("Name");
		column.add("Developer");
		column.add("Genre");
		column.add("Price");

		data = new Vector<Vector<Object>>();
		for(var user: gamesData) {
			row = new Vector<Object>();
			row.add(user.getName());
			row.add(user.getDeveloperName());
			row.add(user.getGenre());
			row.add(user.getPrice());
			data.add(row);
		}

		dataTable = new JTable(data, column);
		dataTable.addMouseListener(this);
		scrollPane = new JScrollPane(dataTable);
		tablePanel = new JPanel();
		tablePanel.add(scrollPane);
		
        contentPanel.add(formPanel);
		contentPanel.add(tablePanel);

		midPanel.add(contentPanel);


		//bottom
		botPanel = new JPanel(new GridLayout(2, 1));
		botPanel.setBorder(new EmptyBorder(0, 0, 25, 0));
		
		//bottom first
		bot1Panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		bot1Panel.setBorder(new EmptyBorder(0, 40, 5, 0));

		payBtnPanel = new JPanel();
        payBtnPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		payBtn = new JButton("Pay");
		payBtn.setFont(new Font("", Font.PLAIN, 12));
		payBtn.addActionListener(this);
        payBtnPanel.add(payBtn);
	
		clearBtnPanel = new JPanel();
        clearBtnPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("", Font.PLAIN, 12));
		clearBtn.addActionListener(this);
        clearBtnPanel.add(clearBtn);

	
		bot1Panel.add(payBtnPanel);
		bot1Panel.add(clearBtnPanel);
		botPanel.add(bot1Panel);

		//bottom second
		bot2Panel = new JPanel();
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		backBtn.setPreferredSize(new Dimension(150, 30));
		bot2Panel.add(backBtn);
		botPanel.add(bot2Panel);


        //add
        add(headerPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);

		//coloring
		headerPanel.setBackground(Color.decode("#93B5C6"));
		midPanel.setBackground(Color.decode("#93B5C6"));
		nameLblPanel.setBackground(Color.decode("#93B5C6"));
		nameTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		developerLblPanel.setBackground(Color.decode("#93B5C6"));
		developerTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		genreLblPanel.setBackground(Color.decode("#93B5C6"));
		genreTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		priceLblPanel.setBackground(Color.decode("#93B5C6"));
		priceTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		formPanel.setBackground(Color.decode("#93B5C6"));
		contentPanel.setBackground(Color.decode("#93B5C6"));
		tablePanel.setBackground(Color.decode("#93B5C6"));
		qtyLblPanel.setBackground(Color.decode("#93B5C6"));
		qtyTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		amountPriceLblPanel.setBackground(Color.decode("#93B5C6"));
		amountPriceTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		botPanel.setBackground(Color.decode("#93B5C6"));
		bot1Panel.setBackground(Color.decode("#93B5C6"));
		bot2Panel.setBackground(Color.decode("#93B5C6"));
		clearBtnPanel.setBackground(Color.decode("#93B5C6"));
		payBtnPanel.setBackground(Color.decode("#93B5C6"));


        settings();
    }
	
    private void settings() {
		setTitle("Transaction Menu");
		// setLocationRelativeTo(null);
		setLocation(200, 0);
		setSize(1000, 680);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == payBtn) {
			if(checkInputData()) {
				//show label
				int qtyInt = Integer.valueOf(qtyStr);
				String priceNowStr = priceTxtField.getText();
				int priceNow = Integer.valueOf(priceNowStr);
				int totalPayment = qtyInt * priceNow;
				totalPaymentStr = String.valueOf(totalPayment);


				String[] confirmationLog = { "Okay", "Cancel" };
				int inpConfirmLog = JOptionPane.showOptionDialog(null, "Your total payment is Rp." + totalPaymentStr + ". Please click 'Okay' to continue!" , "Confirmation",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, confirmationLog, confirmationLog[0]);
				
				//check money
				if(inpConfirmLog == 0) {
					int amountPriceInt = Integer.valueOf(amountPriceStr);
					
					calculatePayment = amountPriceInt - totalPayment;

					//if change >= 0
					if(calculatePayment >= 0) {
						String changeAmount = String.valueOf(calculatePayment);
						String[] paymentLog = {"Print Receipt", "Close"};
						int inpPaymentLog =  JOptionPane.showOptionDialog(null, "Your purchased is successful! Your change is Rp." + changeAmount + ". Thank you for your purchase! You can print the receipt by click 'Print Receipt'.", "Thank You",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, paymentLog, paymentLog[0]);

						//if choose print receipt
						if(inpPaymentLog == 0) {

							JOptionPane.showMessageDialog(this, "Your receipts has been printed!", "Receipt Information", JOptionPane.INFORMATION_MESSAGE);

							//items are added to DB
							int gameId = 0;
							String name = nameTxtField.getText();
							String developer = developerTxtField.getText();
							String genre = genreTxtField.getText();
							int priceInt = Integer.valueOf(priceTxtField.getText());
							int qty = Integer.valueOf(qtyTxtField.getText());
							int amountPrice = Integer.valueOf(amountPriceTxtField.getText());

							for(var item : gamesData) {
								if(item.getName().equals(name)) {
									gameId = item.getId();
									break;
								}
							}

							//add to db
							connectionDb.insertReceiptData(gameId, name, developer, genre, priceInt, qty, amountPrice);

							//write a receipt to receipt.txt
							try(FileWriter fileWrite = new FileWriter("Receipt.txt");
								BufferedWriter buffWrite = new BufferedWriter(fileWrite)) {
								formatWrite();
								
								for(var writeData : receiptFormat) {
									buffWrite.write(writeData);
								}

								receiptFormat.clear(); 								
							} catch(Exception receipt) {
								receipt.printStackTrace();
							}
						}
					} else if(calculatePayment < 0) {
						JOptionPane.showMessageDialog(this, "The money inputted is not enough! Payment cancelled by system!", "Error" ,JOptionPane.ERROR_MESSAGE);
					}
				}

				//go back to menu and clear curr vector
				gamesData.clear();
				setVisible(false);
				new Menu(gamesData, connectionDb);
			}
		} else if(e.getSource() == clearBtn) {
			clearInputField();
		} else if(e.getSource() == backBtn) {
			//go back to menu and clear curr vector
			gamesData.clear();
			setVisible(false);
			new Menu(gamesData, connectionDb);
		}
	}

	private void formatWrite() {
		//get all receipts data to get receipt id
		fillDataReceipts();

		Date date = Calendar.getInstance().getTime();  
        DateFormat dateTimeFormat = new SimpleDateFormat("yyyy/mm/dd-hh:mm:ss");  
        String currDateTime = dateTimeFormat.format(date);  
		
		String[] splittedDateTime = currDateTime.split("-");
		String currDate = splittedDateTime[0];
		String currTime = splittedDateTime[1];

		String name = nameTxtField.getText();
		String priceStr = priceTxtField.getText();
		String qtyStr = qtyTxtField.getText();


		int receiptId = 0;
		for(var find: receiptsData) {
			if(find.getName().equals(name)) {
				receiptId = find.getId();
				break;
			}
		} 

		receiptFormat = new Vector<String>();
		receiptFormat.add("---------- Games Store ----------\n");
		receiptFormat.add("Date : " + currDate + "\n");
		receiptFormat.add("Time : " + currTime + "\n");
		receiptFormat.add("---------------------------------\n");
		receiptFormat.add("Receipt Number\t: " + receiptId + "\n");
		receiptFormat.add("Game Name\t\t: " + name + "\n");
		receiptFormat.add("Game Price\t\t: Rp." + priceStr + "\n");
		receiptFormat.add("Game Quantity\t: " + qtyStr + "\n");
		receiptFormat.add("Total Price\t\t: Rp." + totalPaymentStr + "\n");
		receiptFormat.add("Change\t\t\t: Rp." + calculatePayment + "\n");
		receiptFormat.add("---------------------------------\n");
		receiptFormat.add("Thank you for Purchasing!");
	}

	private void fillDataReceipts() {
		receiptsData = new Vector<Receipt>();
        try {
            connectionDb.resultSet = connectionDb.getReceiptData();
            while(connectionDb.resultSet.next()) {
                int receiptId = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(1)));
                int gameId = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(2)));
                String receiptName = String.valueOf(connectionDb.resultSet.getObject(3)); 
                String receiptDeveloperName = String.valueOf(connectionDb.resultSet.getObject(4)); 
                String receiptGenre = String.valueOf(connectionDb.resultSet.getObject(5)); 
                int receiptPrice = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(6)));
                int receiptQty = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(7)));
                int receiptAmoundPaid = Integer.valueOf(String.valueOf(connectionDb.resultSet.getObject(8)));

                receiptsData.add(new Receipt(receiptId, gameId, receiptName, receiptDeveloperName, receiptGenre,receiptPrice, receiptQty, receiptAmoundPaid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private boolean checkInputData() {
		//check enable button
		qtyStr = qtyTxtField.getText();
		amountPriceStr = amountPriceTxtField.getText();
		tempQty = checkNumberStr(qtyStr);
		tempAmountPrice = checkNumberStr(amountPriceStr);

		//check disable button
		String nameCheck = nameTxtField.getText();
		String devCheck = developerTxtField.getText();
		String genreCheck = genreTxtField.getText();
		String priceStrCheck = priceTxtField.getText();

		
		if(!tempQty.equals("true")) {
			JOptionPane.showMessageDialog(this, "The quantity purchased of game " + tempQty + " !", "Error" ,JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(!tempAmountPrice.equals("true")) {
			JOptionPane.showMessageDialog(this, "The money inputted " + tempAmountPrice + " !", "Error" ,JOptionPane.ERROR_MESSAGE);
			return false;
		} else if(nameCheck.isBlank() || devCheck.isBlank() || genreCheck.isBlank() || priceStrCheck.isBlank() || nameCheck == null || devCheck == null || genreCheck == null || priceStrCheck == null) {
			JOptionPane.showMessageDialog(this, "Please choose the game from the list!", "Error" ,JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private String checkNumberStr(String numberStr) {
		if(numberStr.isBlank()) return "must be filled";
		if(numberStr.equals("0")) return "can't be '0'";
		else {
			for(var input : numberStr.toCharArray()) {
				if(Character.isAlphabetic(input)) return "can't contain alphabet";
			}	
			return "true";
		}
	}

    @Override
    public void mouseClicked(MouseEvent e) {
        int row = dataTable.getSelectedRow();
		nameTxtField.setText(dataTable.getValueAt(row, 0).toString());
		developerTxtField.setText(dataTable.getValueAt(row, 1).toString());
		genreTxtField.setText(dataTable.getValueAt(row, 2).toString());
		priceTxtField.setText(dataTable.getValueAt(row, 3).toString());
    }

	private void clearInputField() {
		nameTxtField.setText("");
		developerTxtField.setText("");
		genreTxtField.setText("");
		priceTxtField.setText("");
		qtyTxtField.setText("");
		amountPriceTxtField.setText("");
	}

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

}
