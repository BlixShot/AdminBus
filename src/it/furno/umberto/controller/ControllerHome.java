package it.furno.umberto.controller;
 import it.furno.umberto.view.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class ControllerHome {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField ruolo;


    @FXML
    void conferma(ActionEvent event) throws IOException, ClassNotFoundException {
    	System.out.println("You clicked me!");
    	c=c.getInstance();
    	if(c.verificaCredenziali(username.getText(), password.getText(), ruolo.getText())) {
	        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/it/furno/umberto/view/FormMagazzino.fxml"));
	        Scene home_page_scene = new Scene(home_page_parent);
	        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	           
	               // app_stage.hide(); //optional
	                app_stage.setScene(home_page_scene);
	                app_stage.show();  
	                
	        System.out.println("Utente: " + username.getText() + " trovato\n");
	            
    	}
    	else
    		System.out.println("utente non trovato");
    }

    @FXML
    void initialize() {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert username != null : "fx:id=\"id\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'FormHome.fxml'.";
        assert ruolo != null : "fx:id=\"ruolo\" was not injected: check your FXML file 'FormHome.fxml'.";


    }
    
    private Controller c;

   
}
