package it.furno.umberto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import it.furno.umberto.controller.Controller;

public class DAO {
	
	/*********SINGLETON******************/
	private static DAO instance;
	public static DAO getInstance(){
		if(instance==null)
			instance=new DAO();
		return instance;
	}
	
	public DAO() {
		
	}
	
	public boolean cercaUtente(String username, String password, String ruolo) throws ClassNotFoundException {
		boolean trovato=false;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("connessione aperta");
			Statement st= conn.createStatement();
			ResultSet rs = st.executeQuery("select* from utenti where username='" + username + "'");
			if(rs.next())
				trovato=true;
			else
				trovato = false;
			
			conn.close();
			
			return trovato;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trovato;
		
		
	}

}
