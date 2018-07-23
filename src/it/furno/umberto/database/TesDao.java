package it.furno.umberto.database;

import it.furno.umberto.controller.Model;
import it.furno.umberto.model.Ordine;
import it.furno.umberto.model.Pezzo;
import it.furno.umberto.model.Sportello;

public class TesDao {
	public static void main(String[] args) throws ClassNotFoundException {
		DAO dao = new DAO();
		//System.out.println(dao.cercaUtente("", "", ""));
		//System.out.println(dao.trovaPezzo("motore").toString());
		//System.out.println(dao.trovaPezzi());
		//dao.aggiornaGiacenza(new Pezzo("fanale",3, 3), 5);
		System.out.println(dao.trovaOrdini());
		dao.aggiornaListaOrdini(new Ordine("adfg", 8));
		
		System.out.println(dao.getListaSportelli());
		 m = m.getInstance();
		 System.out.println(m.getListaSportelli());
	}
	
	static Model m;

}
