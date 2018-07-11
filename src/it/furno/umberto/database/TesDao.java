package it.furno.umberto.database;



public class TesDao {
	public static void main(String[] args) {
		DAO dao = new DAO();
		//System.out.println(dao.cercaUtente("", "", ""));
		System.out.println(dao.trovaPezzo("motore").toString());
		System.out.println(dao.trovaPezzi());
	}

}
