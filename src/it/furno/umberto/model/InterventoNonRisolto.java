package it.furno.umberto.model;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class InterventoNonRisolto extends Intervento{

	public InterventoNonRisolto(RichiestaIntervento r, LocalDate dataEsecuzione, String tipologiaIntervento,
			String esito, String motivo) {
		super(r, dataEsecuzione, tipologiaIntervento, esito);
		// TODO Auto-generated constructor stub
		this.motivo= new SimpleStringProperty(motivo);
	}
	
	public void setMotivo(String motivo) {
		this.motivo.set(motivo);
	}
	
	public String getMotivo() {
		return this.motivo.get();
	}
	private SimpleStringProperty motivo;

}
