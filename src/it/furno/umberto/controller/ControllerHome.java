package it.furno.umberto.controller;
 import it.furno.umberto.view.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ControllerHome {
	
	ObservableList<String> ruoli = FXCollections.observableArrayList("capomeccanico", "gestore", "manutentore");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ChoiceBox choise;

    @FXML
    private Button button;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField ruolo;
    
    @FXML
    private Label attempts;
    
    int i =3;
   
    @FXML
    /**
    Passa le credenziali inserite nella view al Model
    @param the parameters used by the method
    @return the value returned by the method
    @throws what kind of exception does this method throw
    */
    void conferma(ActionEvent event) throws IOException, ClassNotFoundException {
    	boolean trovato;
    	
    	System.out.println("Tasto accedi cliccato");
    	model=model.getInstance();
    	
    	//Controller verifica la validità degli input
    	String usern = username.getText();
    	String pass = password.getText();
    	String r = ruolo.getText();
    	
    	
    	if(i!=0){
    		
	    	if(usern.length()!=0 && pass.length()!=0) {
	    		ruolo.setText((String) choise.getValue());	        	
	        	//Delega il model per verificare che l'utente sia registrato
	        	trovato=model.verificaCredenziali(username.getText(), password.getText(), ruolo.getText());
	        	
	        	//Aggiorna la view
	        	if(trovato && choise.getValue().equals("capomeccanico")) {
	    	        avviaHomeMagazzino(event);  
	        	}
	        	else if(trovato && choise.getValue().equals("gestore")) {
	        		avviaHomeGestore(event);
	        	}
	        	else if(trovato && choise.getValue().equals("manutentore")) {
	        		avviaHomeManutentore(event, usern);
	        	}
	        	else {
	        		allertUtenteNonRegistrato();
	        		i--;
	        		attempts.setText("Numero di tentativi rimasti: " + i);
	        	}
	    	}	    	
	    	else {
	    		System.out.println("inserisci credenziali");
	    		Alert alert = new Alert(AlertType.WARNING);
	    		alert.setTitle("Warning alert");
	    		alert.setHeaderText("Stato credenziali");
	    		alert.setContentText("Inserire tutte le credenziali");
	    		alert.showAndWait();   
	    		i--;    	
	    		attempts.setText("Numero di tentativi rimasti: " + i);
	    	}
    	}
    	
    	else {    	
	    	System.out.println(i);
	    	Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Warning alert");
	        alert.setHeaderText("Stato Autenticazione");
	        alert.setContentText("Tenativi esauriti, non puoi piu effettuare il login");
	        alert.showAndWait(); 
    	}
    }
    
    public void avviaHomeMagazzino(ActionEvent event) throws IOException {
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("/it/furno/umberto/view/FormMagazzino.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    
               
        	// app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();  
                
        System.out.println("Utente: " + username.getText() + " trovato\n");
    }
    
    public void avviaHomeGestore(ActionEvent event) throws IOException {
    	Parent home_page_parent = FXMLLoader.load(getClass().getResource("/it/furno/umberto/view/FormGestore.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    
               
        	// app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();  
                
        System.out.println("Utente: " + username.getText() + " trovato\n");
    }
    
    public void avviaHomeManutentore(ActionEvent event, String m) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("/it/furno/umberto/view/FormIntervento.fxml"));
    	Parent home_page_parent = loader.load();
    	ControllerIntervento c = loader.getController();
        c.setManutentore(m);   
        Scene home_page_scene = new Scene(home_page_parent);
        //OK System.err.println(m);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    
               
        	// app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();  
                
        System.out.println("Utente: " + username.getText() + " trovato\n");
        app_stage.show();  
    }
    
    public void allertUtenteNonRegistrato() {
    	System.out.println("utente non trovato");
    	Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning alert");
        alert.setHeaderText("Stato credenziali");
        alert.setContentText("Utente non registrato nel database");
        alert.showAndWait();
    }
    
    
    @FXML
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert username != null : "fx:id=\"id\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert ruolo != null : "fx:id=\"ruolo\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert choise != null : "fx:id=\"choise\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert attempts != null : "fx:id=\"attempts\" was not injected: check your FXML file 'FormHome.fxml'.";
        choise.setValue("capomeccanico");
        choise.setItems(ruoli);

    }
    

    
    private Model model;
   
}
