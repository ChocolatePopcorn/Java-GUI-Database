package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ConnectionDb {

	public Connection connection;
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData resultSetMetaData;
	public PreparedStatement preparedStatement;

	public ConnectionDb() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fp_bootcamp", "root", "");
			statement = connection.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//load all game data
	public ResultSet getGamesData() {
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `game`");
			resultSet = preparedStatement.executeQuery();
			resultSetMetaData = resultSet.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultSet;
	}
	//load all receipt data
	public ResultSet getReceiptData() {
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM `struk`");
			resultSet = preparedStatement.executeQuery();
			resultSetMetaData = resultSet.getMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	//add a game
	public void insertGameData(String name, String developerName, String genre, int price) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `game` (`nama_game`,`pembuat_game`,`genre_game`,`harga_game`) " + "VALUES (?, ?, ?, ?) ");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, developerName);
			preparedStatement.setString(3, genre);
			preparedStatement.setInt(4, price);

			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//update game
	public void updateGameData(int id, String name, String developerName, String genre, int price) {
		try {
			preparedStatement = connection.prepareStatement("UPDATE `game` SET `nama_game` = ?, `pembuat_game` = ?, `genre_game` = ?, `harga_game` = ? WHERE `game_id` = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, developerName);
			preparedStatement.setString(3, genre);
			preparedStatement.setInt(4, price);
			preparedStatement.setInt(5, id);

			preparedStatement.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//delete game
	public void deleteGameData(int id) {
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM `game` WHERE `game_id` = ?");
			preparedStatement.setInt(1, id);

			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//add receipt
	public void insertReceiptData(int gameId, String name, String developer, String genre, int price, int qty, int amountPrice) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO `struk` (`game_id`, `nama_game`,`pembuat_game`,`genre_game`,`harga_game`, `kuantitas_game`, `uang_pembayaran`) " + "VALUES (?, ?, ?, ?, ?, ?, ?) ");
			preparedStatement.setInt(1, gameId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, developer);
			preparedStatement.setString(4, genre);
			preparedStatement.setInt(5, price);
			preparedStatement.setInt(6, qty);
			preparedStatement.setInt(7, amountPrice);

			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
