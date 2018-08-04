package it.furno.umberto.model;

import javafx.beans.property.SimpleIntegerProperty;

public class PezzoSostituito extends Pezzo{

	public PezzoSostituito(String nome, int giacenza, int giacenzaMin, int quantitaUsata) {
		super(nome, giacenza, giacenzaMin);
		// TODO Auto-generated constructor stub
		this.quantitaUsata= new SimpleIntegerProperty(quantitaUsata);
		this.idRichiesta=0;
	}
	
	public void setIDRichiesta(int id) {
		this.idRichiesta=id;
	}
	public int getQuantitaUsata() {
		return this.quantitaUsata.get();
	}
	
	public void setQuantitaUsata(int qunatitaUsata) {
		this.quantitaUsata.set(qunatitaUsata);
	}
	
	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#getNome()
	 */
	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return super.getNome();
	}

	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#setNome(java.lang.String)
	 */
	@Override
	public void setNome(String nome) {
		// TODO Auto-generated method stub
		super.setNome(nome);
	}

	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#getGiacenza()
	 */
	@Override
	public int getGiacenza() {
		// TODO Auto-generated method stub
		return super.getGiacenza();
	}

	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#setGiacenza(int)
	 */
	@Override
	public void setGiacenza(int giacenza) {
		// TODO Auto-generated method stub
		super.setGiacenza(giacenza);
	}

	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#getGiacenzaMin()
	 */
	@Override
	public int getGiacenzaMin() {
		// TODO Auto-generated method stub
		return super.getGiacenzaMin();
	}

	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#setGiacenzaMin(int)
	 */
	@Override
	public void setGiacenzaMin(int giacenzaMin) {
		// TODO Auto-generated method stub
		super.setGiacenzaMin(giacenzaMin);
	}

	/* (non-Javadoc)
	 * @see it.furno.umberto.model.Pezzo#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	private final SimpleIntegerProperty quantitaUsata;
	private int idRichiesta;
}
