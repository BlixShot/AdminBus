package it.furno.umberto.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import it.furno.umberto.database.DAO;
import it.furno.umberto.model.LineaIntervento;
import it.furno.umberto.model.Manutentore;
import it.furno.umberto.model.Pezzo;
import it.furno.umberto.model.PezzoSostituito;
import it.furno.umberto.model.RichiestaIntervento;
import it.furno.umberto.model.Sportello;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerIntervento {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    public void setManutentore(String m) {
    	nomeManutentore=m;
    	System.err.println(m);
    }
    
    @FXML
    private TextField textData;

    @FXML
    private TextField textTipologia;


    @FXML
    private ChoiceBox<String> choiseStato;
    
    @FXML
    private Button buttonAggiungiPezzo;
    
    @FXML
    private Button buttonRegistraInt;

    @FXML
    private ChoiceBox<String> choicePezzi;

    @FXML
    private Button buttonOk;

    @FXML
    private ChoiceBox<String> choiceRichiesta;

    @FXML
    private TableColumn colonnaData;

    @FXML
    private TableColumn colonnaId;

    @FXML
    private TableColumn colonnaIdSportello;

    @FXML
    private TableColumn colonnaManutentore;

    @FXML
    private TableColumn colonnaStato;

    @FXML
    private TableColumn colonnaStatoSportello;

    @FXML
    private TableColumn colonnaUbicazione;

    @FXML
    private TableView<LineaIntervento> tableLineeIntervento;

    @FXML
    private TableView<RichiestaIntervento> tableRichiesta = new TableView<RichiestaIntervento>();
    
    @FXML
    private TextField txtQuantita;

    @FXML
    private TextArea txtMotivo;

    @FXML
    void initialize() {
    	assert buttonAggiungiPezzo != null : "fx:id=\"buttonAggiungiPezzo\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert buttonOk != null : "fx:id=\"buttonOk\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert buttonRegistraInt != null : "fx:id=\"buttonRegistraInt\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert choicePezzi != null : "fx:id=\"choicePezzi\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert choiceRichiesta != null : "fx:id=\"choiceRichiesta\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert choiseStato != null : "fx:id=\"choiseStato\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert colonnaData != null : "fx:id=\"colonnaData\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert colonnaId != null : "fx:id=\"colonnaId\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert colonnaIdSportello != null : "fx:id=\"colonnaIdSportello\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert colonnaManutentore != null : "fx:id=\"colonnaManutentore\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert colonnaStato != null : "fx:id=\"colonnaStato\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert colonnaStatoSportello != null : "fx:id=\"colonnaStatoSportello\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert tableLineeIntervento != null : "fx:id=\"tableLineeIntervento\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert tableRichiesta != null : "fx:id=\"tableRichiesta\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert textData != null : "fx:id=\"textData\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert textTipologia != null : "fx:id=\"textTipologia\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert txtMotivo != null : "fx:id=\"txtMotivo\" was not injected: check your FXML file 'FormIntervento.fxml'.";
        assert txtQuantita != null : "fx:id=\"txtQuantita\" was not injected: check your FXML file 'FormIntervento.fxml'.";


        model=model.getInstance();
        dao=dao.getInstance();
        
        buttonRegistraInt.setDisable(true);       
        txtMotivo.setEditable(false);
        
        choiseStato.getSelectionModel().selectedItemProperty().addListener(changeListener);
        
        
        /****INIZIALIZZA CHOICEBOX STATO***/
        ObservableList<String> oStato= FXCollections.observableArrayList();
        oStato.add("Risolto");
        oStato.add("Non risolto");
        choiseStato.setItems(oStato);
        
        setManutentore(nomeManutentore);
        
        
        /****INIZIALIZZA CHOICEBOX PEZZI USATI***/
        pezzi=dao.getListaPezzi();
        for(Pezzo p: pezzi) {
        	if(p.getGiacenza()>p.getGiacenzaMin()) {
        		oPezzi.add(p);
        	}
        }
        ObservableList<String> oPezziS= FXCollections.observableArrayList();
        for(Pezzo p: oPezzi) {
        	oPezziS.add(p.getNome());
        }     
        choicePezzi.setItems(oPezziS);
        
        /****INIZIALIZZA CHOICEBOX RICHIESTE INTERVENTO***/
        richieste = dao.getRichiesteInterventoManutenote("metrocubo");
        
        for(RichiestaIntervento r: richieste ) {
        	if(!r.getStato().equalsIgnoreCase("evasa")) {
        		idRichieste.add(String.valueOf((r.getId())));
        	}        	
        }  
        idR = FXCollections.observableArrayList(idRichieste);
        choiceRichiesta.setItems(idR);
        if(idR.isEmpty()) {
        	System.err.println("Nessuna richiesta asseganta");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Nessuna richiesta asseganta");
	        alert.setContentText("Nessuna richiesta asseganta nello stato di 'Aperta'");
	        alert.showAndWait();
	        buttonOk.setDisable(true);
	        buttonRegistraInt.setDisable(true);
	        buttonAggiungiPezzo.setDisable(true);
        }
                
        /*****************INIZIALIZZAZIONE TABELLA 1****************/   
        oRichieste = FXCollections.observableArrayList(rSelezionato);
        colonnaId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colonnaData.setCellValueFactory(new PropertyValueFactory<>("date"));
        colonnaStato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        colonnaManutentore.setCellValueFactory(new PropertyValueFactory<>("nomeManutentoreS"));
        tableRichiesta.setItems(oRichieste);
        tableRichiesta.setEditable(true);
        
        /*****************INIZIALIZZAZIONE TABELLA 1****************/
        oLineeIntervento=FXCollections.observableArrayList(lineeIntervento);  
        colonnaIdSportello.setCellValueFactory(new PropertyValueFactory<>("IdSportello"));
        //colonnaUbicazione.setCellValueFactory(new PropertyValueFactory<>("ubicazione"));
        colonnaStatoSportello.setCellValueFactory(new PropertyValueFactory<>("stato"));
        tableLineeIntervento.setItems(oLineeIntervento);
        tableLineeIntervento.setEditable(true);
    }
    
    @FXML
    void selezionaRichiesta(ActionEvent event) {
    	try {
    	for(RichiestaIntervento r: richieste) {
        	if(r.getId()==Integer.parseInt((String)choiceRichiesta.getValue())) {
        		rSelezionato.add(r);
        		oRichieste.add(r);
        		lineeIntervento=dao.getLineeIntervento(r.getId());
        		oLineeIntervento.addAll(dao.getLineeIntervento(r.getId()));
        		//System.out.println(lineeIntervento);
        		richiestaSelezionata=r;
        		for(LineaIntervento lll:lineeIntervento) {
        			richiestaSelezionata.addLineaIntervento(lll);
        		}
        	}      		
        }
    	
    	buttonOk.setDisable(true);
    	buttonRegistraInt.setDisable(false);
    	}
    	catch(Exception e) {
    		System.err.println("Seleziona richiesta");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Seleziona richiesta");
	        alert.setContentText("Seleziona richiesta di cui registrare l'intervento");
	        alert.showAndWait();
    	}
    }
    

    @FXML
    void aggiungiPezzo(ActionEvent event) {
    	try {
		    int q = Integer.parseInt(txtQuantita.getText());
		    for(Pezzo p: pezzi) {
		    	if(p.getNome().equalsIgnoreCase(choicePezzi.getValue())) {
		    		if(q>=0 && q<p.getGiacenza()) {
				    	PezzoSostituito ps = new PezzoSostituito(p.getNome(), p.getGiacenza(), p.getGiacenzaMin(), q);
				    	pezziSostituiti.add(ps);
				    	
				    	/**
				    	pezzi.remove(p);
				    	oPezzi.remove(p);
				    	ObservableList<String> oPezziS= FXCollections.observableArrayList();
				        for(Pezzo as: oPezzi) {
				        	oPezziS.add(as.getNome());
				        }  **/  
				        
				    	Alert alert = new Alert(AlertType.INFORMATION);
		    	        alert.setTitle("Pezzo registrato come usato");
		    	        alert.setContentText("Pezzo -" + p.getNome() +"- registrato come usato con quantita: " + q);
		    	        alert.showAndWait();
				    	
				    	
		    		}
		    		else {
		    			System.err.println("Inserisci intero");
		        		Alert alert = new Alert(AlertType.WARNING);
		    	        alert.setTitle("Inserisci intero");
		    	        alert.setContentText("Inserisci una quantità che sia maggiore di 0 e minore della giacenza disponibile del pezzo in magazziono che è pari a: "+p.getGiacenza());
		    	        alert.showAndWait();
		    		}
		    	}
		    	
	    	}
    	}catch(NumberFormatException e) {
    		System.err.println("Inserisci intero");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Inserisci intero");
	        alert.setContentText("Inserisci intero positivo");
	        alert.showAndWait();
    	}
    	
    }

    @FXML
    void creaIntervento(ActionEvent event) {
    	dataEsecuzione= textData.getText();
    	LocalDate today= LocalDate.now();
    	System.out.println(today);
    	try {
    		data= LocalDate.parse(dataEsecuzione, formatter);
    		if(data.isBefore(richiestaSelezionata.getDate()) || data.isAfter(today)) {
    			System.err.println("Data cronologicamente sbagliata");
    			Alert alert = new Alert(AlertType.WARNING);
    	        alert.setTitle("Data errata");
    	        alert.setContentText("Hai insericto una data di esecuzione cronologicamnete eraata.");
    	        alert.showAndWait();
    		}
    		else {
	    		tipologia= textTipologia.getText();
	    		motivo= txtMotivo.getText();
	    		try {
	    			esito=choiseStato.getValue();
	    			if(esito.equalsIgnoreCase("non risolto") && motivo.equals("")) {
	    				System.err.println("Definisci il motivo");
	    				Alert alert = new Alert(AlertType.WARNING);
		    	        alert.setTitle("Definisci esito");
		    	        alert.setContentText("Definisci il motivo per cui l'intervento non è stato risolto");
		    	        alert.showAndWait();
	    			}
	    			else {
	    				if(richiestaSelezionata.getStato().equalsIgnoreCase("Aperta")) {
		    				model.creaIntervento(richiestaSelezionata, data, tipologia, esito, pezziSostituiti, motivo);
		    				buttonRegistraInt.setDisable(true);
		    				buttonOk.setDisable(false);
		    				oRichieste.clear();
		    				oLineeIntervento.clear();
		    				oPezzi.clear();
		    				pezzi.clear();
		    			}
	    				else {
	    					Alert alert = new Alert(AlertType.WARNING);
			    	        alert.setTitle("Richiesta gia evasa");
			    	        alert.setContentText("Richiesta gia evasa");
			    	        alert.showAndWait();
			    	        buttonRegistraInt.setDisable(true);
		    				buttonOk.setDisable(false);
		    				oRichieste.clear();
		    				oLineeIntervento.clear();
	    				}
	    			}
	    		}catch(Exception e) {
	    			System.err.println("Definisci esito");
	    			Alert alert = new Alert(AlertType.WARNING);
	    	        alert.setTitle("Definisci esito");
	    	        alert.setContentText("Definisci esito");
	    	        alert.showAndWait();
	    		}
    		}
    	}catch(DateTimeParseException e) {
    		System.err.println(e + "Formato data non corretto");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Formato data non corretto");
	        alert.setContentText("Formato data non corretto");
	        alert.showAndWait();
    	} 	
    	
    }
    
    ChangeListener<String> changeListener = new ChangeListener<String>() {
   	 
        @Override
        public void changed(ObservableValue<? extends String> observable, //
                String oldValue, String newValue) {
            if (newValue != null && newValue.equalsIgnoreCase("Non risolto")) {
                txtMotivo.setEditable(true);
            }
            else {
                txtMotivo.setEditable(false);
            }
            
        }
    };
    

    
    private Model model;
    private DAO dao;
    //Manutentore manutentore;
    // ArrayList<Sportello> listaSportelli = new ArrayList<>();
    //ArrayList<Manutentore> manutentori= new ArrayList<>();
    ArrayList<RichiestaIntervento> richieste= new ArrayList<>();
    ObservableList<RichiestaIntervento> oRichieste;
    RichiestaIntervento richiestaSelezionata;
    ArrayList<RichiestaIntervento> rSelezionato = new ArrayList<>();
    ArrayList<LineaIntervento> lineeIntervento= new ArrayList<>();
    ObservableList<LineaIntervento> oLineeIntervento;
    ArrayList<Pezzo> pezzi= new ArrayList<>();
    ObservableList<Pezzo> oPezzi=FXCollections.observableArrayList();
    ArrayList<PezzoSostituito> pezziSostituiti= new ArrayList<>();
    String dataEsecuzione;
    String tipologia;
    LocalDate data;
    String esito;
    String motivo;
    String nomeManutentore;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ArrayList<String> idRichieste= new ArrayList<>();
    ObservableList<String> idR;
}
