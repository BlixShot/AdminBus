package it.furno.umberto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.tk.quantum.QuantumToolkit;

import it.furno.umberto.model.Magazzino;
import it.furno.umberto.model.Pezzo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;


public class ControllerMagazzino {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Pezzo> table=new TableView<>();
   
    @FXML
    private Button buttonOrdine;
    
    @FXML
    private TableColumn nameColonna;

    @FXML
    private TableColumn quantitaColonna;
    
    @FXML
    private TextField textFieldProdottoSelezionato;


    @FXML
    private TableColumn giacenzaColonna;
    
    @FXML
    private TextField textPreleva;
    
    @FXML
    private Button buttonPreleva;


    @FXML
    void initialize() {
    	assert buttonPreleva != null : "fx:id=\"buttonPreleva\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
    	assert nameColonna != null : "fx:id=\"nameColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert quantitaColonna != null : "fx:id=\"qauntitaColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert giacenzaColonna != null : "fx:id=\"giacenzaColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert textFieldProdottoSelezionato != null : "fx:id=\"textFieldProdottoSelezionato\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert textPreleva != null : "fx:id=\"textPreleva\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert buttonOrdine != null : "fx:id=\"buttonOrdine\" was not injected: check your FXML file 'FormMagazzino.fxml'."; 
        
        model=model.getInstance();
        lista=model.getListaPezzi();
         //System.out.println(magazzino.toString());
        /*************INIZIALIZZAZIONE TABELLA********************************/
        nameColonna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantitaColonna.setCellValueFactory(new PropertyValueFactory<>("giacenza"));
        giacenzaColonna.setCellValueFactory(new PropertyValueFactory<>("giacenzaMin"));
        
        d = FXCollections.observableArrayList(lista);
        table.setItems(d);
        table.setEditable(true);
    
       table.getSelectionModel().selectedIndexProperty()
        .addListener((observable, oldValue, newValue) -> {
            textFieldProdottoSelezionato.setText(d.get(newValue.intValue()).getNome());
        });
      
    }
    
    @FXML
    void preleva(ActionEvent event) throws ClassNotFoundException {
    	int richiesta;
    	int nuovaGiacenza = 0;
    	Pezzo p = null;
    	String nomePezzo=textFieldProdottoSelezionato.getText();
    	
    	//preleva dati input e verfica la correttezza
    	try {
    	richiesta = Integer.parseInt(textPreleva.getText());
    	//Delega il model per l'aggiornamento della giacenza
    	System.err.println("------------Lista con vecchia giacenza-----------------");
    	model.getListaPezzi();
    	System.out.println(model.getListaPezzi().toString());
    	model.effettuaPrelievo(nomePezzo, richiesta);
    	System.err.println("------------Lista con nuova giacenza-----------------");
    	System.out.println(model.getListaPezzi().toString());
    	
    	//aggiorna la view    	
    	for(Pezzo l: lista) {
    		if(l.getNome().equals(nomePezzo)) {
    			p=l;
    		}
    	}
    	
    	//Caso d'uso 12 - scenario 2
    	if(p.getGiacenza()>p.getGiacenzaMin() && richiesta<=p.getGiacenza()) {
    		nuovaGiacenza= p.getGiacenza()-richiesta;
    		p.setGiacenza(nuovaGiacenza);
    		table.refresh();
    		table.setVisible(false);
    		table.setVisible(true);
    		//scenario 1
    		if(nuovaGiacenza<p.getGiacenzaMin()) {
        		Alert alert = new Alert(AlertType.WARNING);
    	        alert.setTitle("Effettua Ordine");
    	        alert.setContentText("Effettuando il prelievo la quantità è scesa sotto la giacenza minima, quindi effettuare ordine");
    	        alert.showAndWait();
        	}
    	}
    	else { //scenario 3
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Effettua Ordine");
	        alert.setContentText("La quantità del pezzo richiesto è sotto la soglia minima, quindi impossibile prelevare e quindi effettuare ordine\nOppure è stata inserita una richiesta di prelievo superiore alla quantità disponibile");
	        alert.showAndWait();
    	}
    	}catch(NumberFormatException e) {
    		System.err.println("dato preleva inserito non valido");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Warning alert");
	        alert.setContentText("Inserisci un intero");
	        alert.showAndWait();
    		richiesta=0;
    	}

    }

    @FXML
    void ordine(ActionEvent event) throws IOException {
    	 Parent home_page_parent = FXMLLoader.load(getClass().getResource("/it/furno/umberto/view/FormOrdine.fxml"));
	        Scene home_page_scene = new Scene(home_page_parent);
	        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    
	               
	        	// app_stage.hide(); //optional
	            app_stage.setScene(home_page_scene);
	            app_stage.show();  
    }
    
    ObservableList<Pezzo> d;
    private Model model;
    private ArrayList<Pezzo> lista = new ArrayList<>();

}
