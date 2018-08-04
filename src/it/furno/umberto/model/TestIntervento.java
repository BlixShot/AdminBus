package it.furno.umberto.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import it.furno.umberto.database.DAO;

public class TestIntervento {
	
	public static void main(String[] args) {
		dao=dao.getInstance();
		//getRichiesteIntervento relative al manutentore
		
		ArrayList<RichiestaIntervento> richieste= new ArrayList<>();
		richieste= dao.getRichiesteInterventoManutenote(nomeManutentore);
		
		System.out.println("************************Richieste di intervento affidate*****************");
		for(RichiestaIntervento r: richieste) {
			System.out.println(r.getId());
			System.out.println(r.getDate());
			System.out.println(r.getNomeManutentoreS());
			System.out.println(r.getStato());
		}
		
		System.out.println("****Richiesta selezionata******");
		for(RichiestaIntervento r: richieste) {
			if(r.getId()==10544) {
				ri=r;
			}
		}
		System.out.println(ri.getId() +" " + ri.getDate() +" "+ ri.getStato());
		
		System.out.println("Sportelli assegnati:");
		//scelta la richiesta di intervento prelevo le linee richieste
		ArrayList<LineaIntervento> l= new ArrayList<LineaIntervento>();
		l= dao.getLineeIntervento(ri.getId());
		for(LineaIntervento t: l) {
			System.out.println(t.getIdSportello()+" " + t.getStato());
			//t.setSportello(new Sportello(t.getIdSportello(), "" ,t.getStato()));
			for(Sportello s: dao.getListaSportelli()) {
				if(s.getCod().equals(t.getIdSportello())) {
					t.setSportello(s);
				}
			}
		}
		
		for(LineaIntervento t : l) {
			ri.addLineaIntervento(t);
		}
		
		for(Pezzo p: dao.getListaPezzi()) {
			if(p.getNome().equals("display")) {
				PezzoSostituito pezzo1= new PezzoSostituito(p.getNome(), p.getGiacenza(), p.getGiacenzaMin(), 4);
				pezzisostituiti.add(pezzo1);
			}
		}
		
		
		//scelgo di dire di aver riparato lo sportello 955A3
		
		LocalDate dataEsecuzione = LocalDate.parse("2018-07-25", format);
		String tipologiaIntervento="sostituzione cartuccia";
		String esito = "non risolto";
		
		intervento = new Intervento(ri, dataEsecuzione, tipologiaIntervento, esito);
		for(LineaIntervento t: l) {
			if(t.getIdSportello().equalsIgnoreCase("955A3")) {
				//sportello = t.getSportello();
				//sportello.setStato("ok");
				//sportello.setAssegnato("false");
				t.setStato("risolto");
			}
			
			
		}
		
		for(PezzoSostituito p: pezzisostituiti) {
			p.setIDRichiesta(intervento.getIdRichiesta());
			intervento.addPezzo(p);
		}
		
		System.out.println("-------------Riepilogo intervento-----------------");
		intervento.print();
		
		dao.registraIntervento(intervento);
		
		if(intervento.getEsito().equals("risolto")) {
			intervento.getRichiesta().setStato("Evasa");
			for(LineaIntervento l1 : l) {
				l1.setStato("risolto");
				l1.setStato("ok");
				l1.getSportello().setAssegnato("false");
			}
			dao.setRichiestaEvasa(intervento.getRichiesta());
		}
		else if(intervento.getEsito().equals("non risolto")) {
			dao.setLineaRichiestaEvasa(l);
			String motivo="dfgh";
			InterventoNonRisolto inN= new InterventoNonRisolto(ri, intervento.getDataEsecuzione(), intervento.getTipologia(), intervento.getEsito(), motivo);
			for(PezzoSostituito p: intervento.getPezziSostituiti()){
				inN.addPezzo(p);
			}
			System.out.println(inN + inN.getPezziSostituiti().toString());
		}
	}

	static String nomeManutentore= "metrocubo";
	static DAO dao;
	static Manutentore manutentore;
	static Sportello sportello=null;
	static ArrayList<Sportello> sportelliAssegnati= new ArrayList<>();
	static RichiestaIntervento ri;
	static Intervento intervento;
	static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static ArrayList<PezzoSostituito> pezzisostituiti= new ArrayList<>();
}
