package it.furno.umberto.controller;

import java.util.ArrayList;

import it.furno.umberto.database.DAO;
import it.furno.umberto.model.Magazzino;
import it.furno.umberto.model.Ordine;
import it.furno.umberto.model.Pezzo;

public class Model {
	
	/*********SINGLETON******************/
	private static Model instance;
	public static Model getInstance(){
		if(instance==null)
			instance=new Model();
		return instance;
	}
	
	/***********CONSTRUCTOR****************/
	public Model(){
		dao = dao.getInstance();
		magazzino=magazzino.getInstance();
	}
	
	/*****************Methods***************/
	
	/**
	Verifica che le credenziali passate dal ControllerHome siano corrette collegandosi al DAO 
	@param
	@return boolean
	@throws ClassException
	*/
	//------------L'utente dovrebbe essere cercato in locale nel magazzino----------
	boolean verificaCredenziali(String username, String password, String ruolo) throws ClassNotFoundException {
		boolean trovato = false;
		if(dao.cercaUtente(username, password, ruolo))
			trovato=true;
		return trovato;
	}
	
	public ArrayList<Pezzo> getListaPezzi() {
		ArrayList<Pezzo> pezzi = new ArrayList<Pezzo>();
		pezzi= dao.getListaPezzi();
		magazzino.setElenco(pezzi);
		/***for(Pezzo p: pezzi) {
			System.out.println(p.toString());
		}**/
		return pezzi;
	}
	
	/**
	Effettua il prelievo di una certa quantita di un pezzo e aggiorna la giacenza sia nel database che nel magazino
	@param
	@return void
	@throws ClassException
	*/
	public void effettuaPrelievo(String nomePezzo, int richiesta) throws ClassNotFoundException {
		//magazzino.searchPezzoByTipo(nomePezzo);
		Pezzo p = dao.trovaPezzo(nomePezzo);
		int giacenzaAttuale = p.getGiacenza();
		if(p.getGiacenza()>p.getGiacenzaMin() && richiesta<=giacenzaAttuale) {
			p.setGiacenza(giacenzaAttuale - richiesta);
			dao.aggiornaGiacenza(p);
		}
		
		magazzino.setElenco(getListaPezzi());
	}
	
	public ArrayList<Ordine> getListaOrdini() {
		ArrayList<Ordine> ordini = new ArrayList<Ordine>();
		ordini= dao.trovaOrdini();
		return ordini;
	}
	
	/**
	Crea un nuovo Ordine e invoca aggiornaOrdini del DAO 
	@param
	@return Ordine
	@throws ClassException
	*/
	public Ordine effettuaOrdine(String nomePezzo, int quantita) throws ClassNotFoundException {
		Ordine o = new Ordine(nomePezzo, quantita);
		dao.aggiornaListaOrdini(o);
		return o;
	}


	private DAO dao;
	private Magazzino magazzino;

}
