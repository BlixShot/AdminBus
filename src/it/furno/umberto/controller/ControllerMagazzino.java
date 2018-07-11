package it.furno.umberto.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.furno.umberto.model.Pezzo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class ControllerMagazzino {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listId = new ListView<String>();
 
    @FXML
    private ListView<Integer> listG= new ListView<Integer>();
    
    @FXML
    private ListView<Integer> listQ= new ListView<Integer>();
    
  
    @FXML
    void initialize() {
    	 assert listG != null : "fx:id=\"listG\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
         assert listId != null : "fx:id=\"listId\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
         assert listQ != null : "fx:id=\"listQ\" was not injected: check your FXML file 'FormMagazzino.fxml'.";

        c=c.getInstance();
        list=c.getListaPezzi();
        
        ObservableList<String> items = FXCollections.observableArrayList ();
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
        listG.setItems(items3);
        
    }
    
    private Controller c;
    private ArrayList<Pezzo> list = new ArrayList<>();

}
