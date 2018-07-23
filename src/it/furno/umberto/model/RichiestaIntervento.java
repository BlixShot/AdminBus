package it.furno.umberto.model;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.LocalDateStringConverter;

public class RichiestaIntervento {
	
	public RichiestaIntervento(Manutentore m) {
		this.stato= new SimpleStringProperty("Aperta");
		this.dataEmissione= dataEmissione.now();
		this.id= new SimpleIntegerProperty(ID_GENERATOR.getAndIncrement()+rand.nextInt(10000));
		lineeIntervento= new ArrayList<LineaIntervento>();
		this.manutentore=m;
	}
	
	public Manutentore getManutentore() {
		return this.manutentore;
	}
	
	public String getNomeManutentore() {
		return this.manutentore.getUsername();
	}
	
	public void setStato(String stato) {
		this.stato.set(stato);
	}
	
	public String getStato() {
		return this.stato.get();
	}
	
	public int getId() {
		return this.id.get();
	}
	
	public LocalDate getDate() {
		return this.dataEmissione;
	}
	
	public ArrayList<LineaIntervento> getLineeIntervento(){
		return lineeIntervento;
	}
	
	public void addLineaIntervento(LineaIntervento l) {
		lineeIntervento.add(l);
	}
	
	public void print(PrintStream ps) {
		ps.println("ID Richiesta: "+id.get());
		ps.println("Data Emissione: "+dataEmissione);
		ps.println("Stato: "+stato.get());
		ps.println("Manutentore: "+manutentore.getUsername());
		ps.println("Sportelli: ");
		for(LineaIntervento l: lineeIntervento) {
			ps.println("-"+l.getIdSportello());
			//ps.println(l.getIdRichiesta());
		}
		
	}
	
	public void print(){
		this.print(System.out);
	}
	
	private final SimpleIntegerProperty id;
	//private final SimpleStringProperty ubicazione;
	private final SimpleStringProperty stato;
	private LocalDate dataEmissione;
	private Manutentore manutentore;
	private ArrayList<LineaIntervento> lineeIntervento;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(10000);
	private Random rand = new Random();

}
