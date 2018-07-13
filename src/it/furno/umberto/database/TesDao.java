package it.furno.umberto.database;

import it.furno.umberto.model.Pezzo;

public class TesDao {
	public static void main(String[] args) throws ClassNotFoundException {
		DAO dao = new DAO();
		//System.out.println(dao.cercaUtente("", "", ""));
		System.out.println(dao.trovaPezzo("motore").toString());
		System.out.println(dao.trovaPezzi());
		dao.aggiornaGiacenza(new Pezzo("fanale",3, 3), 5);
	}

}
