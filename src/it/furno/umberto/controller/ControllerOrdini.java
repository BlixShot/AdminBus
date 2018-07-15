package it.furno.umberto.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.furno.umberto.model.Ordine;
import it.furno.umberto.model.Pezzo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class ControllerOrdini {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonConferma;

    @FXML
    private ChoiceBox<String> choicePezzo;
    
    @FXML
    private TableColumn colonnaID;

    @FXML
    private TableColumn colonnaPezzo;

    @FXML
    private TableColumn colonnaQuantita;

    @FXML
    private TableView<Ordine> tableOrdini = new TableView<>();

    @FXML
    private TextField txtQuantita;
    
    private ArrayList<Pezzo> listaPezzi = new ArrayList<>();
    private ObservableList<String> nomePezzi = FXCollections.observableArrayList();

    ObservableList<Ordine> d;
    private Model model;
    private ArrayList<Ordine> lista = new ArrayList<>();
    private String nomePezzo;
    private int quantitaOrdine;
    

    @FXML
    void confermaOrdine(ActionEvent event) throws ClassNotFoundException {
    	Ordine nuovoOrdine = null;
    	nomePezzo=null;
    	try {
    		nomePezzo=choicePezzo.getValue();
    	}catch(Exception e) {
    		System.err.println("seleziona pezzo");
    		nomePezzo=null;
    	}
    	
    	try {
    		quantitaOrdine=Integer.parseInt(txtQuantita.getText());
    	}catch(Exception e) {
    		System.err.println("inserisci intero");
    		quantitaOrdine =0;
    	}
    	
    	if(nomePezzo!=null && quantitaOrdine!=0) {
		    model.effettuaOrdine(nomePezzo, quantitaOrdine);
		    lista=model.getListaOrdini();
		    for(Ordine o: lista) {
		    	if(o.getNomePezzo().equals(nomePezzo)) {
		    		nuovoOrdine=o;
		    	}
		    }
		    d.addAll(nuovoOrdine);
		    tableOrdini.refresh();
			tableOrdini.setVisible(false);
			tableOrdini.setVisible(true);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Stato ordine");
			alert.setContentText("Ordine effettuato");
	        alert.showAndWait();
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Dati non validi");
	        alert.setContentText("Seleziona pezzo da ordinare e corretta quantità");
	        alert.showAndWait();
    	}
    }

    @FXML
    void initialize() {
        assert buttonConferma != null : "fx:id=\"buttonConferma\" was not injected: check your FXML file 'FormOrdine.fxml'.";
        assert choicePezzo != null : "fx:id=\"choicePezzo\" was not injected: check your FXML file 'FormOrdine.fxml'.";
        assert colonnaPezzo != null : "fx:id=\"colonnaPezzo\" was not injected: check your FXML file 'FormOrdine.fxml'.";
        assert colonnaQuantita != null : "fx:id=\"colonnaQuantita\" was not injected: check your FXML file 'FormOrdine.fxml'.";
        assert colonnaID != null : "fx:id=\"colonnaID\" was not injected: check your FXML file 'FormOrdine.fxml'.";
        assert tableOrdini != null : "fx:id=\"tableOrdini\" was not injected: check your FXML file 'FormOrdine.fxml'.";
        assert txtQuantita != null : "fx:id=\"txtQuantita\" was not injected: check your FXML file 'FormOrdine.fxml'.";

        
        model=model.getInstance();
        lista=model.getListaOrdini();
        
        /*******************INIZIALIZZAZIONE CHOICEBOE*************************/
        listaPezzi=model.getListaPezzi();
        for(Pezzo p: listaPezzi) {
        	nomePezzi.add(p.getNome());
        }
        choicePezzo.setItems(nomePezzi);
        
        /*************INIZIALIZZAZIONE TABELLA********************************/
        colonnaID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colonnaPezzo.setCellValueFactory(new PropertyValueFactory<>("nomePezzo"));
        colonnaQuantita.setCellValueFactory(new PropertyValueFactory<>("quantitaOrdine"));
        d = FXCollections.observableArrayList(lista);
        tableOrdini.setItems(d);
        tableOrdini.setEditable(true);

    }

}