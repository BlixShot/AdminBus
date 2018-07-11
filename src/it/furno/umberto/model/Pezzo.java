package it.furno.umberto.model;

public class Pezzo {
	
	
	
	public Pezzo(String nome, int giacenza, int giacenzaMin) {
		super();
		this.nome = nome;
		this.giacenza = giacenza;
		this.giacenzaMin = giacenzaMin;
	}
	
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the giacenza
	 */
	public int getGiacenza() {
		return giacenza;
	}
	/**
	 * @param giacenza the giacenza to set
	 */
	public void setGiacenza(int giacenza) {
		this.giacenza = giacenza;
	}
	/**
	 * @return the giacenzaMin
	 */
	public int getGiacenzaMin() {
		return giacenzaMin;
	}
	/**
	 * @param giacenzaMin the giacenzaMin to set
	 */
	public void setGiacenzaMin(int giacenzaMin) {
		this.giacenzaMin = giacenzaMin;
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
		return "Pezzo [nome=" + nome + ", giacenza=" + giacenza + ", giacenzaMin=" + giacenzaMin + "]";
	}




	private String nome;
	private int giacenza;
	private int giacenzaMin;

}
