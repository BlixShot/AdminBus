package it.furno.umberto.controller;

import it.furno.umberto.database.DAO;

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
	

	private DAO dao;
}
