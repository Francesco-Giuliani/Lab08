package it.polito.tdp.dizionariograph.db;

import java.sql.SQLException;

public class TestDAO {
	
	public static void main(String[] args) throws SQLException {
		
		WordDAO wd = new WordDAO();
		
		System.out.println(wd.getAllWordsFixedLength(4));
		System.out.println(wd.getAllWordsDifferingForOnlyOneChar("abaco"));
		
	}

}
