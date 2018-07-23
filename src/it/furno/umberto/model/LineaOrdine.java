package it.furno.umberto.model;

import java.io.PrintStream;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LineaOrdine {
	
	public LineaOrdine(String nomePezzo, int quantita) {
		this.ordine=null;
		this.nomePezzo= new SimpleStringProperty(nomePezzo);
		this.quantita=new SimpleIntegerProperty(quantita);
	}
	
	public void setNomePezzo(String nomePezzo) {
		this.nomePezzo.set(nomePezzo);
	}
	
	public void setQuantita(int quantita) {
		this.quantita.set(quantita);
	}
	
	public String getNomePezzo() {
		return this.nomePezzo.get();
	}
	
	public int getQuantita() {
		return this.quantita.get();
	}
	
	public Ordine getOrdine(){
		return ordine;
	}
	
	public void setOrdine(Ordine o){
		ordine= o;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineaOrdine [nomePezzo=" + nomePezzo.get() + ", quantita=" + quantita.get() + "\n";
	}
	
	public void print(PrintStream ps){
		ps.println(nomePezzo.get());
		ps.println(quantita.get());
		
	}
	
	public void print(){
		this.print(System.out);
	}



	private final SimpleStringProperty nomePezzo;
	private final SimpleIntegerProperty quantita;
	private Ordine ordine;

}
