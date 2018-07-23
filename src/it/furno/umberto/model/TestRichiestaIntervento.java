package it.furno.umberto.model;

import java.util.ArrayList;

import it.furno.umberto.database.DAO;

public class TestRichiestaIntervento {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dao=dao.getInstance();
		
		ArrayList<Sportello> sportelli = new ArrayList<Sportello>();
		sportelli= dao.getListaSportelli();
		System.out.println(sportelli);
		
		ArrayList<Manutentore> manutentori= new ArrayList<>();
		manutentori=dao.getListaManutentori();
		System.out.println(manutentori);
		
		for(Manutentore m: manutentori) {
			if(m.getUsername().equals("metrocubo"))
				manutentore=m;
		}
		
		for(Sportello s: sportelli) {
			if(s.getCod().equals("955A1")) {
				sportello=s;
			}
		}
		
		
		RichiestaIntervento rs= new RichiestaIntervento(manutentore);
		
		LineaIntervento l1= new LineaIntervento(sportello, rs);
		LineaIntervento l2= new LineaIntervento(sportello, rs);
		rs.addLineaIntervento(l2);
		rs.addLineaIntervento(l1);
		rs.print();
		
		dao.aggiornaListaRichieste(rs);
		
		System.out.println(rs.getLineeIntervento());
		//System.out.println(java.sql.Date.valueOf(rs.getDate()));
	}
	
	static DAO dao;
	static Manutentore manutentore;
	static Sportello sportello;

}
