package it.furno.umberto.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import it.furno.umberto.model.Manutentore;
import it.furno.umberto.model.Sportello;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerHomeGestore {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonIntervento;

    @FXML
    private Button buttonLista;
    

    @FXML
    private ChoiceBox<String> choiseManutentore;

    @FXML
    private TableColumn colonnaID;

    @FXML
    private TableColumn colonnaStato;

    @FXML
    private TableColumn colonnaUbi;

    @FXML
    private TableView<Sportello> tableSportelli= new TableView<>();
    
    @FXML
    private TableView<Sportello> tableSportelliRiparazione = new TableView<>();

    @FXML
    private TableColumn colonnaUbiRip;
    
    @FXML
    private TableColumn colonnaIDRip;


    @FXML
    void initialize() {
    	  assert buttonAdd != null : "fx:id=\"buttonAdd\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert buttonIntervento != null : "fx:id=\"buttonIntervento\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert buttonLista != null : "fx:id=\"buttonLista\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert choiseManutentore != null : "fx:id=\"choiseManutentore\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert colonnaID != null : "fx:id=\"colonnaID\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert colonnaIDRip != null : "fx:id=\"colonnaIDRip\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert colonnaStato != null : "fx:id=\"colonnaStato\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert colonnaUbi != null : "fx:id=\"colonnaUbi\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert colonnaUbiRip != null : "fx:id=\"colonnaUbiRip\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert tableSportelli != null : "fx:id=\"tableSportelli\" was not injected: check your FXML file 'FormGestore.fxml'.";
          assert tableSportelliRiparazione != null : "fx:id=\"tableSportelliRiparazione\" was not injected: check your FXML file 'FormGestore.fxml'.";

        
        model=model.getInstance();
        lista=model.getListaSportelli();
        
        /**********************INIZIALIZZAZIONE CHOISEBOC*****************/
        manutentori=model.getManutentori();
        ArrayList<String> nomeM= new ArrayList<>();
        for(Manutentore m: manutentori){
        	nomeM.add(m.getUsername());
        }
        oManutentori= FXCollections.observableArrayList(nomeM);
        choiseManutentore.setItems(oManutentori);
        
        
        /*************INIZIALIZZAZIONE TABELLA********************************/
        
        colonnaID.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colonnaUbi.setCellValueFactory(new PropertyValueFactory<>("ubicazione"));
        colonnaStato.setCellValueFactory(new PropertyValueFactory<>("stato"));
        data = FXCollections.observableArrayList(lista);
        tableSportelli.setItems(data);
        tableSportelli.setEditable(true);
        
        /******************INIZIALIZZAZIONE TABELLA RIP********************/
        colonnaIDRip.setCellValueFactory(new PropertyValueFactory<>("cod"));
        colonnaUbiRip.setCellValueFactory(new PropertyValueFactory<>("ubicazione"));
        oSportelliRip=FXCollections.observableArrayList(sportelliRip);
        tableSportelliRiparazione.setItems(oSportelliRip);
        tableSportelliRiparazione.setEditable(true);
    }
    

    @FXML
    void addSportello(ActionEvent event) {
    	Sportello s = tableSportelli.getSelectionModel().getSelectedItem();
    	//System.out.println(s);
    	
    	if(s==null) {
    		System.err.println("Seleziona sportello");
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning alert");
    		alert.setHeaderText("Nessuno sportello selezionato");
    		alert.setContentText("Seleziona sportello");
    		alert.showAndWait(); 
    	}
    	else {
    		if(s.getAssegnato().equals("false") && s.getStato().equals("not ok")) {
	    		s.setAssegnato("true");
	    		oSportelliRip.add(s);
	        	sportelliRip.add(s);
    		}
    		else if(s.getAssegnato().equals("true")) {
    			System.err.println("Impossibile aggiungere sportello" );
                Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("");
        		alert.setHeaderText("Impossibile aggiungere sportello");
        		alert.setContentText("Lo sportello selezionato è gia stato aggiunto ad una richiesta di intervento");
        		alert.showAndWait();
    		}
    		else if(s.getStato().equals("ok")) {
    			System.err.println("Impossibile aggiungere sportello" );
                Alert alert = new Alert(AlertType.WARNING);
        		alert.setTitle("");
        		alert.setHeaderText("Impossibile aggiungere sportello");
        		alert.setContentText("Lo sportello selezionato è funzionante");
        		alert.showAndWait();
    		}
    	}
    	
    }

    @FXML
    void richiediIntervento(ActionEvent event) {
    	try {
    	String nome= (String)choiseManutentore.getValue();
        for(Manutentore m: manutentori) {
        	if(m.getUsername().equalsIgnoreCase(nome)) {
        		manutentore=m;
        	}
        }
    	//System.out.println(manutentore);
        if(!sportelliRip.isEmpty()) {
        	model.creaRichiestaIntervento(manutentore, sportelliRip);
        	System.out.println("Richiesta intervento inoltrata correttamente");
            Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Stato richiesta");
    		alert.setHeaderText("Stato richiesta");
    		alert.setContentText("Richiesta effettuata con successo,\na video la stampa con il riepilogo");
    		alert.showAndWait();
        }
        else {
        	Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning alert");
    		alert.setHeaderText("Nessuno sportello aggiunto");
    		alert.setContentText("Aggiungi sportello");
    		alert.showAndWait();
        }
         
    	}catch(Exception e) {
    		System.err.println("Nessun manutentore selezionato");
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning alert");
    		alert.setHeaderText("Nessun manutentore selezionato");
    		alert.setContentText("Seleziona manutentore");
    		alert.showAndWait(); 
    	}
    }

    @FXML
    void visualizzaInterventi(ActionEvent event) {
    }
    
    private Model model;
    Manutentore manutentore;
    ArrayList<Sportello> lista = new ArrayList<>();
    ArrayList<Manutentore> manutentori= new ArrayList<>();
    ArrayList<Sportello> sportelliRip= new ArrayList<>();
    ObservableList<Sportello> data;
    ObservableList<String> oManutentori;
    ObservableList<Sportello> oSportelliRip;

}

