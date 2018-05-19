package it.polito.tdp.dizionariograph.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WordDAO {

	/*
	 * Ritorna tutte le parole di una data lunghezza
	 */
	public List<String> getAllWordsFixedLength(int length) {

		String sql = "SELECT nome FROM parola WHERE LENGTH(nome) = ?;";
		List<String> parole = new ArrayList<String>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, length);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				parole.add(res.getString("nome"));
			}

			return parole;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error Connection Database");
		}
	}
	public List<String> getAllWordsDifferingForOnlyOneChar(String word) throws SQLException{
		String sql = "select * from parola where ";
		int length = word.length();
		
		for(int i=0; i<length; i++) {
			if(i<length-1)
				sql += "nome LIKE ? OR ";
			else
				sql += "nome LIKE ?";
		}
		
		List<String> likes = this.getAllCharsSubstituted(word);
		List<String>res = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			int j=1;
			for(String s : likes) {
				st.setString(j, s);
				j++;
			}
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res.add(rs.getString("nome"));
			}
			
			conn.close();
		}catch(SQLException sqle) {
			throw sqle;
		}
		
		return res;		
	}
	

	private List<String> getAllCharsSubstituted(String word) {
		int length = word.length();
		String tmp;
		char[] wordArr = word.toCharArray();
		char subst;
		List<String> likes = new ArrayList<>();
		
		for(int i =0; i<length; i++) {
			subst = wordArr[i];
			wordArr[i] = '_';
			tmp = new String(wordArr);
			likes.add(tmp);
			wordArr[i]= subst;
		}
		return likes;
	}

	
}
