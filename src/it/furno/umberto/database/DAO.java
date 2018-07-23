package it.furno.umberto.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;

import it.furno.umberto.controller.Model;
import it.furno.umberto.model.LineaIntervento;
import it.furno.umberto.model.LineaOrdine;
import it.furno.umberto.model.Manutentore;
import it.furno.umberto.model.Ordine;
import it.furno.umberto.model.Pezzo;
import it.furno.umberto.model.RichiestaIntervento;
import it.furno.umberto.model.Sportello;

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
	
	public ArrayList<Manutentore> getListaManutentori(){
		ArrayList<Manutentore> manutentori= new ArrayList<>();
		Manutentore manutentore;
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String sql= "select* from utenti where ruolo='manutentore'";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement statment = conn.prepareStatement(sql);
			ResultSet rs= statment.executeQuery();
			while(rs.next()) {
				manutentore = new Manutentore(rs.getString("username"), rs.getString("password"));
				manutentori.add(manutentore);
			}
			return manutentori;
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
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
	
	public ArrayList<Pezzo> getListaPezzi(){
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

	public void aggiornaGiacenza(Pezzo p) throws ClassNotFoundException {
		int giacenza = p.getGiacenza();
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "UPDATE pezzi SET giacenza = ? WHERE nome= ?";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement statment = conn.prepareStatement(sql);
			statment.setInt(1, giacenza);
			statment.setString(2, p.getNome());
			int rs=statment.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Ordine> trovaOrdini() {
		ArrayList<Ordine> ordini = new ArrayList<>();
		Ordine o = null;
		String sql = "select * from ordini";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn= DriverManager.getConnection(jdbcURL);
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				o = new Ordine(rs.getString("pezzo ordinato"), rs.getInt("quantità ordinata"));
				o.setID(rs.getInt("ID"));
				ordini.add(o);
			}
			
			return ordini;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void aggiornaListaOrdini(Ordine o) throws ClassNotFoundException {
		String n = o.getNomePezzo();
		int q = o.getQuantitaOrdine();
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "INSERT INTO `dao_sql`.`ordini` (`Pezzo Ordinato`, `Quantità Ordinata`, `ID`) VALUES (?, ?, ?)";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement statment = conn.prepareStatement(sql);
			statment.setString(1, n);
			statment.setInt(2, q);
			statment.setInt(3, o.getID());
			int rs=statment.executeUpdate();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Sportello> getListaSportelli(){
		ArrayList<Sportello> sportelli = new ArrayList<>();
		Sportello s = null;
		String sql = "select * from sportelli";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn= DriverManager.getConnection(jdbcURL);
			PreparedStatement st= conn.prepareStatement(sql);
			ResultSet rs=st.executeQuery();
			while(rs.next()) {
				s = new Sportello(rs.getString("id"), rs.getString("ubicazione"), rs.getString("stato"));
				s.setAssegnato(rs.getString("assegnato"));
				sportelli.add(s);
			}
			
			return sportelli;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean aggiornaListaOrdiniNew(Ordine o) throws ClassNotFoundException {		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "INSERT INTO `dao_sql`.`ordini2` (`ID`) VALUES (?)";
		String sql2= "INSERT INTO `dao_sql`.`linea ordine` (`IDOrdine`, `pezzo`, `quantita`) VALUES (?, ?, ?)";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement statment = conn.prepareStatement(sql);
			PreparedStatement statment2= conn.prepareStatement(sql2);
			statment.setInt(1, o.getID());
			int rs=statment.executeUpdate();
			
			for(LineaOrdine l: o.getLinea()) {
				
				statment2.setInt(1, l.getOrdine().getID());
				statment2.setString(2, l.getNomePezzo());
				statment2.setInt(3, l.getQuantita());
				int rs2=statment2.executeUpdate();
				//System.out.println(l);
			}
			conn.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void aggiornaListaRichieste(RichiestaIntervento rs) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `dao_sql`.`richieste intervento` (`Id`, `data`, `manutentore`, `stato`) VALUES (?, ? , ? , ?)";
		String sql2= "INSERT INTO `dao_sql`.`sportelli da riparare` (`ID Rchiesta`, `ID Sportello`) VALUES ( ? , ? )";
		String sql3 = "UPDATE `dao_sql`.`sportelli` SET `Assegnato`='true' WHERE  `ID`= ? ";
		String jdbcURL ="jdbc:mysql://localhost/dao_sql?user=root&password=&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Connection conn = DriverManager.getConnection(jdbcURL);
			PreparedStatement statment = conn.prepareStatement(sql);
			PreparedStatement statment2= conn.prepareStatement(sql2);
			PreparedStatement statment3= conn.prepareStatement(sql3);
			statment.setInt(1, rs.getId());
			statment.setString(2, java.sql.Date.valueOf(rs.getDate()).toString());
			//System.out.println(java.sql.Date.valueOf(rs.getDate()));
			statment.setString(3, rs.getNomeManutentore());
			statment.setString(4, rs.getStato());
			int resultSet = statment.executeUpdate();
			
			for(LineaIntervento l: rs.getLineeIntervento()) {
				
				statment2.setInt(1, l.getIdRichiesta());
				statment2.setString(2, l.getIdSportello());
				
				int rs2=statment2.executeUpdate();
				//System.out.println(l);
			}
			
			for(LineaIntervento l: rs.getLineeIntervento()) {
				Sportello s = l.getSportello();
				statment3.setString(1, s.getCod());
				int rs3= statment3.executeUpdate();
				
			}
			conn.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	

}
