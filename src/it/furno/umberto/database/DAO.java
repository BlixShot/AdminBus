package it.furno.umberto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.furno.umberto.controller.Controller;
import it.furno.umberto.model.Pezzo;

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
			ResultSet rs = st.executeQuery("select* from utenti where username='" + username + "' and password='" + password + "' and ruolo='" + ruolo + "'");
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

	public Pezzo trovaPezzo(String nome) {
			
		Pezzo result = null;
		String sql = "select * from pezzi where nome=?";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement statment = conn.prepareStatement(sql);
			statment.setString(1, nome);
			ResultSet rs=statment.executeQuery();
			if(rs.next()) {
				result = new Pezzo(rs.getString("nome"), rs.getInt("giacenza"), rs.getInt("giacenzaMin"));
			}
			else
				result = null;
			conn.close();
			return result;	
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}		
		}
	
	public ArrayList<Pezzo> trovaPezzi(){
		ArrayList<Pezzo> pezzi = new ArrayList<>();
		Pezzo p = null;
		String sql = "select * from pezzi";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn= DriverManager.getConnection(jdbcURL);
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				p = new Pezzo(rs.getString("nome"), rs.getInt("giacenza"), rs.getInt("giacenzaMin"));
				pezzi.add(p);
			}
			
			return pezzi;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
