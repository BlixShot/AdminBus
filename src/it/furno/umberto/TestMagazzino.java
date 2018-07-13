package it.furno.umberto;

import java.util.ArrayList;
import java.util.Arrays;

import it.furno.umberto.controller.Controller;
import it.furno.umberto.model.Pezzo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TestMagazzino extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("view/FormMagazzino.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	private final TableView<Pezzo> table = new TableView<>();
	private  ArrayList<Pezzo> persone = new ArrayList<>(Arrays.asList(new Pezzo("motore",2,2)));
    private final ObservableList<Pezzo> data =
            FXCollections.observableArrayList(persone
            );
    final HBox hb = new HBox();
    private static Controller c;
    
    
    
    
    
    
    
    
    

	public static void main(String[] args) {
		launch(args);
	
	}
	
	@Override
    public void start(Stage stage) {
		
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(550);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("NOME");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("nome"));
 
        TableColumn lastNameCol = new TableColumn("GIACENZA");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("giacenza"));
 
        TableColumn emailCol = new TableColumn("GIACENZAMIN");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("giacenzaMin"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");
 
        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Pezzo(
                    addFirstName.getText(),
                    Integer.parseInt(addLastName.getText()),
                    Integer.parseInt(addEmail.getText())));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
            for(Pezzo p: persone) {
            	if(p.getNome().equals("a")) {
            		p.setNome("b");
            		//data.add(p);
            	}
            }
        });
 
        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
        hb.setSpacing(3);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
       // vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
}
	
**/	
}
