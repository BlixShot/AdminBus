package it.furno.umberto.model;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Intervento {
	
	public Intervento(RichiestaIntervento r, LocalDate dataEsecuzione, String tipologiaIntervento, String esito) {
		this.r=r;
		this.iDRichiesta= new SimpleIntegerProperty(r.getId());
		this.dataEsecuzione=dataEsecuzione;
		this.tipologiaIntervento= new SimpleStringProperty(tipologiaIntervento);
		this.esito= new SimpleStringProperty(esito);
		pezziSostituiti=new ArrayList<PezzoSostituito>();
	}
	
	public RichiestaIntervento getRichiesta() {
		return this.r;
	}
	
	public int getIdRichiesta() {
		return this.iDRichiesta.get();
	}
	
	public void setDataEsecuzione(LocalDate date) {
		this.dataEsecuzione=date;
	}
	
	public LocalDate getDataEsecuzione() {
		return this.dataEsecuzione;
	}
	
	public void setTipologiaIntervento(String tipologia) {
		this.tipologiaIntervento.set(tipologia);
	}
	
	public String getTipologia() {
		return this.tipologiaIntervento.get();
	}
	
	public String getEsito() {
		return this.esito.get();
	}
	
	public void setEsito(String esito) {
		this.esito.set(esito);
	}
	
	public void addPezzo(PezzoSostituito p) {
		pezziSostituiti.add(p);
	}
	
	public ArrayList<PezzoSostituito> getPezziSostituiti(){
		return this.pezziSostituiti;
	}
	
	public void print(PrintStream ps) {
		ps.println("ID RIchiesta riferita: "+iDRichiesta.get() );
		ps.println("Data esecuzione intervento: " + dataEsecuzione);
		ps.println("Tipologia intervento: " +tipologiaIntervento.get());
		ps.println("Pezzi usati/sostituiti: ");
		for(PezzoSostituito p: pezziSostituiti) {
			ps.println("nome: " + p.getNome());
			ps.println("quantita usata: " + p.getQuantitaUsata());
		}
		ps.println("Sportelli riparati:");
		for(LineaIntervento l: r.getLineeIntervento()) {
			if(l.getStato().equalsIgnoreCase("risolto")) {
				ps.println("-"+l.getIdSportello());
			}
		}
		
		ps.println("Sportelli non riparati;");
		for(LineaIntervento l: r.getLineeIntervento()) {
			if(l.getStato().equalsIgnoreCase("non risolto")) {
				ps.println("-"+l.getIdSportello());
			}
		}
		
		ps.println("Esito: " + esito.get());
	}
	
	public void print(){
		this.print(System.out);
	}
	private final SimpleIntegerProperty iDRichiesta;
	private LocalDate dataEsecuzione;
	private final SimpleStringProperty tipologiaIntervento;
	private final SimpleStringProperty esito;
	private ArrayList<PezzoSostituito> pezziSostituiti;
	private RichiestaIntervento r;
}
