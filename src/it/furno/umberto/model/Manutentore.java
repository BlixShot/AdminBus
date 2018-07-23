package it.furno.umberto.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Manutentore {
	
	public Manutentore(String username, String password) {
		this.username=new SimpleStringProperty(username);
		this.password= new SimpleStringProperty(password);
		this.interventiAssegnati=new ArrayList<>();
	}
	
	public String getUsername() {
		return this.username.get();
	}
	
	public void addIntervento(RichiestaIntervento r) {
		interventiAssegnati.add(r);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Manutentore [username=" + username.get() + ", password=" + password.get() + "]";
	}



	private final SimpleStringProperty username;
	private final SimpleStringProperty password;
	private ArrayList<RichiestaIntervento> interventiAssegnati;

}
