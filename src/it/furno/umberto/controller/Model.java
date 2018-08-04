package it.furno.umberto.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import it.furno.umberto.database.DAO;
import it.furno.umberto.model.Intervento;
import it.furno.umberto.model.LineaIntervento;
import it.furno.umberto.model.LineaOrdine;
import it.furno.umberto.model.Magazzino;
import it.furno.umberto.model.Manutentore;
import it.furno.umberto.model.Ordine;
import it.furno.umberto.model.Pezzo;
import it.furno.umberto.model.PezzoSostituito;
import it.furno.umberto.model.RichiestaIntervento;
import it.furno.umberto.model.Sportello;
import javafx.collections.ObservableList;

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
	
	public Ordine effettuaOrdineNew(ArrayList<LineaOrdine> listaLinee) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Ordine o = new Ordine();
		for(LineaOrdine l: listaLinee) {
			l.setOrdine(o);
			o.addLinea(l);
			//System.out.println(l.getOrdine());
		}
		dao.aggiornaListaOrdiniNew(o);
		//System.out.println(o);
		return o;
	}
	
	public ArrayList<Sportello> getListaSportelli() {
		ArrayList<Sportello> sportelli = new ArrayList<Sportello>();
		sportelli= dao.getListaSportelli();
		return sportelli;
	}


	private DAO dao;
	private Magazzino magazzino;
	
	public ArrayList<Manutentore> getManutentori() {
		ArrayList<Manutentore> m = dao.getListaManutentori();
		return m;
	}

	public void creaRichiestaIntervento(Manutentore manutentore, ArrayList<Sportello> sportelliRip) {
		
		RichiestaIntervento rs= new RichiestaIntervento(manutentore);
		
		for(Sportello sportello: sportelliRip) {
			sportello.setAssegnato("true");
			LineaIntervento l1= new LineaIntervento(sportello, rs);
			rs.addLineaIntervento(l1);
		}
		
		manutentore.addIntervento(rs);
		
		rs.print();
		
		dao.aggiornaListaRichieste(rs);
		
		
	}

	public void creaIntervento(RichiestaIntervento richiestaSelezionata, LocalDate data, String tipologia, String esito,
			ArrayList<PezzoSostituito> pezziSostituiti, String motivo) {
		// TODO Auto-generated method stub
		Intervento intervento = new Intervento(richiestaSelezionata, data, tipologia, esito);
		for(PezzoSostituito p: pezziSostituiti) {
			intervento.addPezzo(p);
		}
		
		for(LineaIntervento t: richiestaSelezionata.getLineeIntervento()) {
			for(Sportello s: dao.getListaSportelli()) {
				if(s.getCod().equals(t.getIdSportello())) {
					t.setSportello(s);
				}
			}
		}
		
		System.out.println("*****Richiesta Selezionata**********");
		richiestaSelezionata.print();
			
		if(intervento.getEsito().equalsIgnoreCase("risolto")) {
			richiestaSelezionata.setStato("evasa");
			for(LineaIntervento l: richiestaSelezionata.getLineeIntervento()) {
				l.setStato("risolto");
			}
			System.out.println("*****Riepilogo Intervento**********");
			intervento.print();
			dao.registraIntervento(intervento);
			dao.setRichiestaEvasa(richiestaSelezionata);
		}
		else {
			System.out.println("*****Riepilogo Intervento**********");
			intervento.print();
			dao.registraInterventoNonRisolto(intervento, motivo);
		}
		
	}

	String motivo="";
	
	public ArrayList<RichiestaIntervento> getRichiesteInterventoManutenote(String nomeManuentore) {
		return dao.getRichiesteInterventoManutenote(nomeManuentore);
	}
}
