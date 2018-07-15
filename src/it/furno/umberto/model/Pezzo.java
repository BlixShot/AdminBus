package it.furno.umberto.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pezzo {
	
	
	
	public Pezzo(String nome, int giacenza, int giacenzaMin) {
		this.nome = new SimpleStringProperty(nome);
		this.giacenza = new SimpleIntegerProperty(giacenza);
		this.giacenzaMin = new SimpleIntegerProperty( giacenzaMin);
	}
	
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome.get();
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome.set(nome);
	}
	/**
	 * @return the giacenza
	 */
	public int getGiacenza() {
		return giacenza.get();
	}
	/**
	 * @param giacenza the giacenza to set
	 */
	public void setGiacenza(int giacenza) {
		this.giacenza.set(giacenza);;
	}
	/**
	 * @return the giacenzaMin
	 */
	public int getGiacenzaMin() {
		return giacenzaMin.get();
	}
	/**
	 * @param giacenzaMin the giacenzaMin to set
	 */
	public void setGiacenzaMin(int giacenzaMin) {
		this.giacenzaMin.set(giacenzaMin);
	}
	
	


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pezzo other = (Pezzo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nPezzo [nome=" + nome + ", giacenza=" + giacenza + ", giacenzaMin=" + giacenzaMin + "]";
	}




	private final SimpleStringProperty nome;
	private final SimpleIntegerProperty giacenza;
	private final SimpleIntegerProperty giacenzaMin;

}
