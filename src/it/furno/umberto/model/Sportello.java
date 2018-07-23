package it.furno.umberto.model;

import javafx.beans.property.SimpleStringProperty;

public class Sportello {
	
	public Sportello(String cod, String ubicazione, String stato) {
		this.cod=new SimpleStringProperty(cod);
		this.ubicazione= new SimpleStringProperty(ubicazione);
		this.stato= new SimpleStringProperty(stato);
		this.assegnato= new SimpleStringProperty("false");
	}
	
	public void setStato(String stato) {
		this.stato.set(stato);
	}
	
	public String getCod() {
		return cod.get();
	}
	
	public String getUbicazione() {
		return ubicazione.get();
	}

	public String getStato() {
		return stato.get();
	}
	
	public String getAssegnato() {
		return this.assegnato.get();
	}
	
	public void setAssegnato(String assegnato) {
		this.assegnato.set(assegnato);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sportello [cod=" + cod.get() + ", ubicazione=" + ubicazione.get() + ", stato=" + stato.get() + ", assegnato=" + assegnato.get() + "]";
	}


	private final SimpleStringProperty cod;
	private final SimpleStringProperty ubicazione;
	private final SimpleStringProperty stato;
	private final SimpleStringProperty assegnato;
}
