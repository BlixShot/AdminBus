package it.furno.umberto.controller;

import java.util.ArrayList;

import it.furno.umberto.database.DAO;
import it.furno.umberto.model.Pezzo;

public class Controller {
	
	/*********SINGLETON******************/
	private static Controller instance;
	public static Controller getInstance(){
		if(instance==null)
			instance=new Controller();
		return instance;
	}
	
	public Controller(){
		dao = dao.getInstance();
		//magazzino=magazzino.getInstance();
	}
	
	boolean verificaCredenziali(String username, String password, String ruolo) throws ClassNotFoundException {
		boolean trovato = false;
		//if(username.equals("umberto") && password.equals("furno") && ruolo.equals("capomeccanico"))
		//	trovato=true;
		//return trovato;
		if(dao.cercaUtente(username, password, ruolo))
			trovato=true;
		return trovato;
	}
	
	public ArrayList<Pezzo> getListaPezzi() {
		ArrayList<Pezzo> pezzi = new ArrayList<Pezzo>();
		pezzi= dao.trovaPezzi();
		return pezzi;
	}

	private DAO dao;
}
