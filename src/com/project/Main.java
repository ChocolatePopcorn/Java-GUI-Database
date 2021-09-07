package com.project;

import java.util.Vector;

import com.config.ConnectionDb;
import com.utility.Game;

/*
1. buat tampilan view (CRUD) 
 - form edit"

2. buat tampilan transaction
 - jtable (ga spesifik)
 - form
 - jbutton tombol cetak, back


*/


public class Main {

    public Vector<Game> gamesData = new Vector<Game>();
    private ConnectionDb connectionDb;
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        connectionDb = new ConnectionDb();
        new Menu(gamesData, connectionDb);
    }
    
}
