package it.furno.umberto.model;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Ordine {

	public Ordine(String nomePezzo, int quantitaOrdine) {
		this.nomePezzo = new SimpleStringProperty(nomePezzo);
		this.quantitaOrdine = new SimpleIntegerProperty(quantitaOrdine);
		this.customerID = new SimpleIntegerProperty( ID_GENERATOR.getAndIncrement()+rand.nextInt(1000));
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ordine [ID="+ customerID.get() + ", nomePezzo=" + nomePezzo.get() + ", quantitaOrdine=" + quantitaOrdine.get() + "]";
	}

	private final SimpleStringProperty nomePezzo;
	private final SimpleIntegerProperty quantitaOrdine;
	private SimpleIntegerProperty customerID;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private Random rand = new Random();

}
