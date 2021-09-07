package com.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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

public class View extends JFrame implements ActionListener, MouseInputListener{
	
	private Vector<Game> gamesData;
	private ConnectionDb connectionDb;

	private JPanel headerPanel, midPanel, contentPanel, formPanel, tablePanel, botPanel, bot1Panel, bot2Panel,
	idLblPanel, nameLblPanel, developerLblPanel, genreLblPanel, priceLblPanel,
	idTxtFieldPanel, nameTxtFieldPanel, developerTxtFieldPanel, genreTxtFieldPanel, priceTxtFieldPanel;
	private JLabel headerLbl, idLbl, nameLbl, developerLbl, genreLbl, priceLbl;
	private JTextField idTxtField, nameTxtField, developerTxtField, genreTxtField, priceTxtField;

	private JTable dataTable;
	private JScrollPane scrollPane;
	private JButton addBtn, updateBtn, deleteBtn, clearBtn, backBtn;

	private Vector<Object> column, row;
	private Vector<Vector<Object>> data;

	private String name, developer, genre, priceStr;
	private String nameUpdated, developerUpdated, genreUpdated, priceStrUpdated;
	private int price, priceUpdated, id;

	public View(Vector<Game> gamesData, ConnectionDb connectionDb) {
		this.gamesData = gamesData;
		this.connectionDb = connectionDb;


		//Header
		headerPanel = new JPanel();
		headerPanel.setBorder(new EmptyBorder(20, 0, 30, 0));
		headerLbl = new JLabel("CRUD Games");
		headerLbl.setFont(new Font("", Font.BOLD, 16));
		headerPanel.add(headerLbl);


		//Mid
		midPanel = new JPanel();
		contentPanel = new JPanel(new GridLayout(1,2));

		//left side
		formPanel = new JPanel(new GridLayout(5,2));
		
		idLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		idLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		idTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		idLbl = new JLabel("Game ID: ");
		idLbl.setFont(new Font("", Font.PLAIN, 14));
		idLblPanel.add(idLbl);
		idTxtField = new JTextField();
		idTxtField.setEditable(false);
		idTxtField.setPreferredSize(new Dimension(130, 25));
		idTxtFieldPanel.add(idTxtField);


		nameLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		nameLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		nameTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		nameLbl = new JLabel("Game Name: ");
		nameLbl.setFont(new Font("", Font.PLAIN, 14));
		nameLblPanel.add(nameLbl);
		nameTxtField = new JTextField();
		nameTxtField.setPreferredSize(new Dimension(130, 25));
		nameTxtFieldPanel.add(nameTxtField);


		developerLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		developerLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		developerTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		developerLbl = new JLabel("Developer Name: ");
		developerLbl.setFont(new Font("", Font.PLAIN, 14));
		developerLblPanel.add(developerLbl);
		developerTxtField = new JTextField();
		developerTxtField.setPreferredSize(new Dimension(130, 25));
		developerTxtFieldPanel.add(developerTxtField);
		

		genreLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		genreLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		genreTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		genreLbl = new JLabel("Game Genre: ");
		genreLbl.setFont(new Font("", Font.PLAIN, 14));
		genreLblPanel.add(genreLbl);
		genreTxtField = new JTextField();
		genreTxtField.setPreferredSize(new Dimension(130, 25));
		genreTxtFieldPanel.add(genreTxtField);


		priceLblPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		priceLblPanel.setBorder(new EmptyBorder(0, 30, 0, 0));
		priceTxtFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		
		priceLbl = new JLabel("Game Price: ");
		priceLbl.setFont(new Font("", Font.PLAIN, 14));
		priceLblPanel.add(priceLbl);
		priceTxtField = new JTextField();
		priceTxtField.setPreferredSize(new Dimension(130, 25));
		priceTxtFieldPanel.add(priceTxtField);

		formPanel.add(idLblPanel);
		formPanel.add(idTxtFieldPanel);
		formPanel.add(nameLblPanel);
		formPanel.add(nameTxtFieldPanel);
		formPanel.add(developerLblPanel);
		formPanel.add(developerTxtFieldPanel);
		formPanel.add(genreLblPanel);
		formPanel.add(genreTxtFieldPanel);
		formPanel.add(priceLblPanel);
		formPanel.add(priceTxtFieldPanel);
			 

		//right side
		column = new Vector<Object>();
		column.add("ID");
		column.add("Name");
		column.add("Developer");
		column.add("Genre");
		column.add("Price");

		data = new Vector<Vector<Object>>();
		for(var user: gamesData) {
			row = new Vector<Object>();
			row.add(user.getId());
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
		bot1Panel.setBorder(new EmptyBorder(0, 80, 5, 0));
		
		addBtn = new JButton("Add");
		addBtn.setFont(new Font("", Font.PLAIN, 12));
		addBtn.addActionListener(this);
		updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("", Font.PLAIN, 12));
		updateBtn.addActionListener(this);
		deleteBtn = new JButton("Delete");
		deleteBtn.setFont(new Font("", Font.PLAIN, 12));
		deleteBtn.addActionListener(this);
		clearBtn = new JButton("Clear");
		clearBtn.setFont(new Font("", Font.PLAIN, 12));
		clearBtn.addActionListener(this);
		
		bot1Panel.add(addBtn);
		bot1Panel.add(updateBtn);
		bot1Panel.add(deleteBtn);
		bot1Panel.add(clearBtn);
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
		idLblPanel.setBackground(Color.decode("#93B5C6"));
		idTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		nameLblPanel.setBackground(Color.decode("#93B5C6"));
		nameTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		developerLblPanel.setBackground(Color.decode("#93B5C6"));
		developerTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		genreLblPanel.setBackground(Color.decode("#93B5C6"));
		genreTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		priceLblPanel.setBackground(Color.decode("#93B5C6"));
		priceTxtFieldPanel.setBackground(Color.decode("#93B5C6"));
		formPanel.setBackground(Color.decode("#93B5C6"));
		botPanel.setBackground(Color.decode("#93B5C6"));
		bot1Panel.setBackground(Color.decode("#93B5C6"));
		bot2Panel.setBackground(Color.decode("#93B5C6"));
		contentPanel.setBackground(Color.decode("#93B5C6"));
		tablePanel.setBackground(Color.decode("#93B5C6"));
		


		settings();
	}

	private void settings() {
		setTitle("Edit Game");
		// setLocationRelativeTo(null);
		setLocation(200, 0);
		setSize(1000, 670);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	   if(e.getSource() == addBtn) {
			if(checkInputAddData()) {
				int checkReply = JOptionPane.showConfirmDialog(this, "Are you sure you want to add " + name + " ?", "Add Game Confirmation", JOptionPane.YES_NO_OPTION);
				if(checkReply == JOptionPane.YES_OPTION) {	
					price = Integer.valueOf(priceStr);		
					
					//add data to vector
					Game addNewGame = new Game(0, name, developer, genre, price);
					gamesData.add(addNewGame);
					
					// add data to database
					connectionDb.insertGameData(name, developer, genre, price);

					//go to menu and delete curr vector
					gamesData.clear();
					setVisible(false);
					new Menu(gamesData, connectionDb);
				}
			}
			else clearInputField();
	   }
	   else if(e.getSource() == updateBtn) {
		   if(checkSelectedData()) {
				int checkReply = JOptionPane.showConfirmDialog(this, "Are you sure you want to update " + name + " ?", "Add Games Confirmation", JOptionPane.YES_NO_OPTION);
				if(checkReply == JOptionPane.YES_OPTION) {
					id = Integer.valueOf(idTxtField.getText());
					nameUpdated = nameTxtField.getText();
					developerUpdated = developerTxtField.getText();
					genreUpdated = genreTxtField.getText();
					priceStrUpdated = priceTxtField.getText();
					priceUpdated = Integer.valueOf(priceStrUpdated);

					//update by id as a PK
					connectionDb.updateGameData(id, nameUpdated, developerUpdated, genreUpdated, priceUpdated);

					//go to menu and delete curr vector
					gamesData.clear();
					setVisible(false);
					new Menu(gamesData, connectionDb);
				}
		   }
		   else clearInputField();
	   }
	   else if(e.getSource() == deleteBtn) {
			if(checkSelectedData()) {
				int checkReply = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + name + " ?", "Add Games Confirmation", JOptionPane.YES_NO_OPTION);
				if(checkReply == JOptionPane.YES_OPTION) {
					//delete by id as a PK
					id = Integer.valueOf(idTxtField.getText());
					connectionDb.deleteGameData(id);
					
					//go to menu and delete curr vector
					gamesData.clear();
					setVisible(false);
					new Menu(gamesData, connectionDb);
				}
			}
			else clearInputField();
	   }
	   else if(e.getSource() == clearBtn) {
		   clearInputField();
	   }
	   else if(e.getSource() == backBtn) {
		   //go back to menu and clear curr vector
		   gamesData.clear();
		   setVisible(false);
		   new Menu(gamesData, connectionDb);
	   }
		
	}


	private boolean checkSelectedData() {
		name = nameTxtField.getText();
		developer = developerTxtField.getText();
		genre = genreTxtField.getText();
		priceStr = priceTxtField.getText();

		if(name.isBlank() || developer.isBlank() || genre.isBlank() || priceStr.isBlank()) {
			JOptionPane.showMessageDialog(this, "Please click from table to delete!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(name == null || developer == null || genre == null || priceStr == null) {
			JOptionPane.showMessageDialog(this, "Please click from table to delete!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void clearInputField() {
		idTxtField.setText("");
		nameTxtField.setText("");
		developerTxtField.setText("");
		genreTxtField.setText("");
		priceTxtField.setText("");
	}

	private boolean checkInputAddData() {
		String idStr = idTxtField.getText();
		name = nameTxtField.getText();
		developer = developerTxtField.getText();
		genre = genreTxtField.getText();
		priceStr = priceTxtField.getText(); 
		String checkPriceInput = checkPrice(priceStr);

		if(name.isBlank()) {
			JOptionPane.showMessageDialog(this, "The game's name must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
 		} else if(developer.isBlank()) {
	 		JOptionPane.showMessageDialog(this, "The game's developer name must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
			 return false; 
		} else if(genre.isBlank()) {
			JOptionPane.showMessageDialog(this, "The game's genre must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
			return false; 
		} else if(!checkPriceInput.equals("true")) {
			JOptionPane.showMessageDialog(this, "The game's price " + checkPriceInput + "!", "Error", JOptionPane.ERROR_MESSAGE);
			return false; 
 		} else if(!idStr.isBlank()) {
			JOptionPane.showMessageDialog(this, "Can't add an existing game!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else return true;
	}

	private String checkPrice(String priceStr) {
		if(priceStr.isBlank()) return "must be filled";
		if(priceStr.equals("0")) return "can't be '0'";
		else {
			for(var input : priceStr.toCharArray()) {
				if(Character.isAlphabetic(input)) return "can't contain alphabet";
			}	
			return "true";
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == dataTable) {
			int row = dataTable.getSelectedRow();
			idTxtField.setText(dataTable.getValueAt(row, 0).toString());
			nameTxtField.setText(dataTable.getValueAt(row, 1).toString());
			developerTxtField.setText(dataTable.getValueAt(row, 2).toString());
			genreTxtField.setText(dataTable.getValueAt(row, 3).toString());
			priceTxtField.setText(dataTable.getValueAt(row, 4).toString());
		}
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
