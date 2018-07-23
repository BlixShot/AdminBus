package it.furno.umberto.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LineaIntervento {
	
	public LineaIntervento(Sportello s, RichiestaIntervento r) {
		this.s= s;
		this.r= r;
		this.idSportello= new SimpleStringProperty(s.getCod());
		this.idRichiesta= new SimpleIntegerProperty(r.getId());
	}

	public String getIdSportello() {
		return this.idSportello.get();
	}
	
	public int getIdRichiesta() {
		return this.idRichiesta.get();
	}
	
	public Sportello getSportello() {
		return this.s;
	}
	
	public RichiestaIntervento getRichiestaIntervento() {
		return this.r;
	}
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineaIntervento [idSportello=" + idSportello.get() + ", idRichiesta=" + idRichiesta.get()+ "]";
	}



	private final SimpleStringProperty idSportello;
	private final SimpleIntegerProperty idRichiesta;
	private Sportello s;
	private RichiestaIntervento r;

}
