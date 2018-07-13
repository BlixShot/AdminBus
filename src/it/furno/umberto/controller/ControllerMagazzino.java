package it.furno.umberto.controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class ControllerMagazzino {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    /**
    @FXML
    private ListView<String> listId = new ListView<String>();
 
    @FXML
    private ListView<Integer> listG= new ListView<Integer>();
    
    @FXML
    private ListView<Integer> listQ= new ListView<Integer>();
     **/
    
    @FXML
    private TableView<Pezzo> table=new TableView<>();
   
    
    @FXML
    private TableColumn nameColonna;

    @FXML
    private TableColumn qauntitaColonna;
    
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
    	 //assert listG != null : "fx:id=\"listG\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
         //assert listId != null : "fx:id=\"listId\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
         //assert listQ != null : "fx:id=\"listQ\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
    	assert buttonPreleva != null : "fx:id=\"buttonPreleva\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
    	assert nameColonna != null : "fx:id=\"nameColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert qauntitaColonna != null : "fx:id=\"qauntitaColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert giacenzaColonna != null : "fx:id=\"giacenzaColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert textFieldProdottoSelezionato != null : "fx:id=\"textFieldProdottoSelezionato\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert textPreleva != null : "fx:id=\"textPreleva\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
         
         c=c.getInstance();
         list=c.getListaPezzi();
         //System.out.println(magazzino.toString());
        
        /**ObservableList<String> items = FXCollections.observableArrayList ();
        for(Pezzo p : list) {
        	items.add(p.getNome());
        }
        
        ObservableList<Integer> items2 = FXCollections.observableArrayList ();
        for(Pezzo p : list) {
        	items2.add(p.getGiacenza());
        }
        
        ObservableList<Integer> items3 = FXCollections.observableArrayList ();
        for(Pezzo p : list) {
        	items3.add(p.getGiacenzaMin());
        }
        
        //ObservableList<String> items =FXCollections.observableArrayList (c.getListaPezzi().toString());
        listId.setItems(items);
        listQ.setItems(items2);
        listG.setItems(items3);**/
        
        nameColonna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        qauntitaColonna.setCellValueFactory(new PropertyValueFactory<>("giacenza"));
        giacenzaColonna.setCellValueFactory(new PropertyValueFactory<>("giacenzaMin"));
        
        d = FXCollections.observableArrayList(list);
        table.setItems(d);
    
       table.getSelectionModel().selectedIndexProperty()
        .addListener((observable, oldValue, newValue) -> {
            textFieldProdottoSelezionato.setText(d.get(newValue.intValue()).getNome());
        });
      
    }
    
    @FXML
    void preleva(ActionEvent event) throws ClassNotFoundException {
    	//preleva dati input e verfica
    	int richiesta = Integer.parseInt(textPreleva.getText());
    	String nomePezzo=textFieldProdottoSelezionato.getText();
    	
    	//chiedi al model di effettuare l'operazione
    	c.aggiornaGiacenza(nomePezzo, richiesta);
    	
    	//aggiorna la view
    	
    	
    	for(Pezzo p : list) {
            if(p.getNome().equals(nomePezzo))
               p.setGiacenza(2);
    	}
    	list=c.getListaPezzi();
            
    	//table.setItems(d);
    	
    }
    
    ObservableList<Pezzo> d;
    private Controller c;
    private ArrayList<Pezzo> list = new ArrayList<>();

}
