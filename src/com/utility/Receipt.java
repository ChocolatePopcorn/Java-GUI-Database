package com.utility;

public class Receipt {
    private int id;
    private int gameId;
    private String name;
    private String developerName;
    private String genre;
    private int price;
    private int quantity;
    private int amountPaid;

    public Receipt(int id, int gameId, String name, String developerName, String genre, int price, int quantity, int amountPaid) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.developerName = developerName;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.amountPaid = amountPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }


    
    

}
