package it.furno.umberto.database;



public class TesDao {
	public static void main(String[] args) {
		DAO dao = new DAO();
		try {
			System.out.println(dao.cercaUtente("", "", ""));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
