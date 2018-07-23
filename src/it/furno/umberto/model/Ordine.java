package it.furno.umberto.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Ordine {

	public Ordine(String nomePezzo, int quantitaOrdine) {
		this.nomePezzo = new SimpleStringProperty(nomePezzo);
		this.quantitaOrdine = new SimpleIntegerProperty(quantitaOrdine);
		this.customerID = new SimpleIntegerProperty( ID_GENERATOR.getAndIncrement()+rand.nextInt(1000));
	}
	
	public Ordine() {
		this.customerID = new SimpleIntegerProperty( ID_GENERATOR.getAndIncrement()+rand.nextInt(1000));
		this.lineeOrdine= new ArrayList<LineaOrdine>();
	}
	
	
	/**
	 * @return the nomePezzo
	 */
	public String getNomePezzo() {
		return nomePezzo.get();
	}
	/**
	 * @return the quantitaOrdine
	 */
	public int getQuantitaOrdine() {
		return quantitaOrdine.get();
	}
	
	public int getID() {
		return customerID.get();
	}
	
	
	public void setNomePezzo(String nomePezzo) {
		this.nomePezzo.set(nomePezzo);
	}
	
	public void setQuantitaOrdine(int quantitaOrdine) {
		this.quantitaOrdine.set(quantitaOrdine);
	}
	
	public void setID(int id) {
		this.customerID.set(id);
	}
	
	public void setList(ArrayList l) {
		this.lineeOrdine=l;
	}
	
	public ArrayList<LineaOrdine> getLinea(){
		return lineeOrdine;
	}
	
	public void addLinea(LineaOrdine l) {
		lineeOrdine.add(l);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ordine [ID="+ customerID.get() +" Lista linee: "+ lineeOrdine +"]";
	}
	
	public void print (PrintStream ps) {
		ps.println("ID: "+customerID.get());
		
		System.out.println("Linee: ");
		for(LineaOrdine temp: lineeOrdine){
		ps.println(temp.getNomePezzo());
		ps.println(temp.getQuantita());

		}
	}
	
	public void print(){
		this.print(System.out);
	}


	private /*final*/ SimpleStringProperty nomePezzo;
	private /*final*/ SimpleIntegerProperty quantitaOrdine;
	private SimpleIntegerProperty customerID;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private Random rand = new Random();
	private ArrayList<LineaOrdine> lineeOrdine;

}
