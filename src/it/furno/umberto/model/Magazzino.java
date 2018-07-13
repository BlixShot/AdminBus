package it.furno.umberto.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Magazzino {
	
	private ArrayList<Pezzo> elenco = new ArrayList<>();
	private static int giacenzaMin;
	private static Magazzino instance;
	
	public Magazzino(int giacenzamin) {
		this.elenco=new ArrayList<Pezzo>();
		this.giacenzaMin=giacenzamin;
	}
	
	public static Magazzino getInstance() {
		if(instance==null)
			instance=new Magazzino(giacenzaMin);
		return instance;
	}

	/**
	 * @return the elenco
	 */
	public ArrayList<Pezzo> getElenco() {
		return elenco;
	}

	/**
	 * @param elenco the elenco to set
	 */
	public void setElenco(ArrayList<Pezzo> elenco) {
		this.elenco = elenco;
	}

	/**
	 * @return the giacenzaMin
	 */
	public static int getGiacenzaMin() {
		return giacenzaMin;
	}

	/**
	 * @param giacenzaMin the giacenzaMin to set
	 */
	public static void setGiacenzaMin(int giacenzaMin) {
		Magazzino.giacenzaMin = giacenzaMin;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Magazzino [elenco=" + elenco + "]";
	}
	
	public int searchPezzoByTipo(String tipo) {
		boolean trovato=false;
		int i=0;
		while(i<elenco.size() && !trovato) {
			if(elenco.get(i).getNome().equalsIgnoreCase(tipo))
				trovato=true;
			else
				i++;
		}
		if(trovato)
			return i;
		else
			return 0;
		//throw new PezzoNonPresenteException();
	}
	
	/**public ArrayList<Pezzo> aggiornaMagazzino(Pezzo p, int quantità) {
		int i;
		i=this.searchPezzoByTipo(p.getTipo());
		elenco.get(i).setGiacenza(p.getGiacenza()-quantità);
		return elenco;
	}**/
	

}
