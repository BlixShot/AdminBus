package it.furno.umberto.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sun.javafx.tk.quantum.QuantumToolkit;

import it.furno.umberto.model.LineaOrdine;
import it.furno.umberto.model.Magazzino;
import it.furno.umberto.model.Ordine;
import it.furno.umberto.model.Pezzo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


public class ControllerMagazzino {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Pezzo> table=new TableView<>();
    
    @FXML
    private Button buttonOrdineNew;
    
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
    private TableView<LineaOrdine> tabellaOrdini= new TableView<>();
    
    @FXML
    private TableColumn colonnaNomeO;

    @FXML
    private TableColumn colonnaQauntitaO;
    
    @FXML
    private Button aggiungiAllaLista;

    @FXML
    void initialize() {
    	assert buttonOrdineNew != null : "fx:id=\"buttonOrdineNew\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
    	assert aggiungiAllaLista != null : "fx:id=\"aggiungiAllaLista\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert buttonOrdine != null : "fx:id=\"buttonOrdine\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert buttonPreleva != null : "fx:id=\"buttonPreleva\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert colonnaNomeO != null : "fx:id=\"colonnaNomeO\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert colonnaQauntitaO != null : "fx:id=\"colonnaQauntitaO\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert giacenzaColonna != null : "fx:id=\"giacenzaColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert nameColonna != null : "fx:id=\"nameColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert quantitaColonna != null : "fx:id=\"quantitaColonna\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert tabellaOrdini != null : "fx:id=\"tabellaOrdini\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert textFieldProdottoSelezionato != null : "fx:id=\"textFieldProdottoSelezionato\" was not injected: check your FXML file 'FormMagazzino.fxml'.";
        assert textPreleva != null : "fx:id=\"textPreleva\" was not injected: check your FXML file 'FormMagazzino.fxml'."; 
        
        model=model.getInstance();
        lista=model.getListaPezzi();
         //System.out.println(magazzino.toString());
        /*************INIZIALIZZAZIONE TABELLA********************************/
        nameColonna.setCellValueFactory(new PropertyValueFactory<>("nome"));
        quantitaColonna.setCellValueFactory(new PropertyValueFactory<>("giacenza"));
        giacenzaColonna.setCellValueFactory(new PropertyValueFactory<>("giacenzaMin"));
         buttonOrdine.setDisable(true);
        d = FXCollections.observableArrayList(lista);
        table.setItems(d);
        table.setEditable(true);
    
       table.getSelectionModel().selectedIndexProperty()
        .addListener((observable, oldValue, newValue) -> {
            textFieldProdottoSelezionato.setText(d.get(newValue.intValue()).getNome());
        });
       
       /*******************INIZIALIZZAZIONE TABELLA LINEAORDINE********************/
       colonnaNomeO.setCellValueFactory(new PropertyValueFactory<>("nomePezzo"));
       colonnaQauntitaO.setCellValueFactory(new PropertyValueFactory<>("quantita"));
       /*****TEST LIST LINEA
       Ordine asd= new Ordine();
       LineaOrdine a= new LineaOrdine("evf", 3);
       //a.setOrdine(asd);
       listaLinee.add(a);***********/
       linee= FXCollections.observableArrayList(listaLinee);
       tabellaOrdini.setItems(linee);
       tabellaOrdini.setEditable(true);
       //colonnaQauntitaO.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }
    
    @FXML
    void preleva(ActionEvent event) throws ClassNotFoundException {
    	int richiesta;
    	int nuovaGiacenza = 0;
    	Pezzo p = null;
    	String nomePezzo=textFieldProdottoSelezionato.getText();
    	
    	try {
    		richiesta= Integer.parseInt(textPreleva.getText());
    		
    /***************************Input validi, procediamo******************************/
        	if(richiesta>0 && !nomePezzo.equals("")) {
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
            		Alert alert = new Alert(AlertType.INFORMATION);
        	        alert.setTitle("OK");
        	        alert.setContentText("prelievo effettuato");
        	        alert.showAndWait();
            		//scenario 1
            		if(nuovaGiacenza<p.getGiacenzaMin()) {
                		Alert alert1 = new Alert(AlertType.WARNING);
            	        alert1.setTitle("Effettua Ordine");
            	        alert1.setContentText("Effettuando il prelievo la quantità è scesa sotto la giacenza minima, quindi effettuare ordine minimo di :" + (p.getGiacenzaMin()-p.getGiacenza()) + " pezzi");
            	        alert1.showAndWait();
                	}
            	}
            	else if(!(p.getGiacenza()>p.getGiacenzaMin())){ //scenario 3
            		Alert alert = new Alert(AlertType.WARNING);
        	        alert.setTitle("Effettua Ordine");
        	        alert.setContentText("La quantità del pezzo richiesto '" + p.getNome() +"' è sotto la soglia minima, quindi impossibile prelevare e quindi effettuare ordine di quantità minima pari a '" + (p.getGiacenzaMin()-p.getGiacenza()+1)+"'");
        	        alert.showAndWait();
            	}
            	else if(!(richiesta<=p.getGiacenza())){
            		Alert alert = new Alert(AlertType.WARNING);
        	        alert.setTitle("Effettua Ordine");
        	        alert.setContentText("è stata inserita una richiesta di prelievo superiore alla quantità disponibile");
        	        alert.showAndWait();
            	}
        	}
        	else {
        		System.out.println("false");
        		Alert alert = new Alert(AlertType.WARNING);
    	        alert.setTitle("Effettua Ordine");
    	        alert.setContentText("Seleziona nome pezzo e inserisci una quantità da prelevare corretta emaggiore di zero");
    	        alert.showAndWait();
        	}
    	
    	}catch(NumberFormatException e) {
    		System.err.println("dato preleva inserito non valido");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Warning alert");
	        alert.setContentText("Inserisci un intero maggiore di 0 e minore di 2147483647");
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
    
    @FXML
    void addLinea(ActionEvent event) {
    	int giacenzaMin;
    	int giacenza;
    	int quantitaSuggerita=0;
    	String nome;
     
    	try {
	    	nome= table.getSelectionModel().getSelectedItem().getNome();
	    	giacenza = table.getSelectionModel().getSelectedItem().getGiacenza();
	    	giacenzaMin= table.getSelectionModel().getSelectedItem().getGiacenzaMin();
	    	quantitaSuggerita=(giacenzaMin-giacenza)+1;
	    	if(quantitaSuggerita<0)
	    		quantitaSuggerita=1;
	    	//System.out.println(nome + quantitaSuggerita);
	 
	    	//display("Quantità per: "+nome, "Quantita suggerita: " + quantitaSuggerita);
	    	quantitaSuggerita=(display("Quantità per: "+nome, "Quantita minima ordine: " + quantitaSuggerita +"\nInserisci un eventuale incremento della quantita da ordinare:", quantitaSuggerita));
	    	
	    	//System.out.println(display("Quantità per: "+nome, "Quantita suggerita: " + quantitaSuggerita));
	    	//creaArraylistLineeOrdine nel model
	    	LineaOrdine l = new LineaOrdine(nome, quantitaSuggerita);
	    	listaLinee.add(l);
	    	linee.add(l);
    	}
    	catch(Exception e) {
    		System.out.println("seleziona items");
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("seleziona items");
	        alert.setContentText("seleziona items");
	        alert.showAndWait();
    	}
    	
    	//System.out.println(listaLinee);
    }
    
    @FXML
    void creaOrdineNew(ActionEvent event) throws ClassNotFoundException {
    	if(!listaLinee.isEmpty()) {
	    	Ordine o = model.effettuaOrdineNew(listaLinee);
	    	//System.out.println(listaLinee);
	    	linee.clear();
	    	listaLinee.clear();
	    	Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Ordine completato");
	        alert.setContentText("ORDINE EFFETTUATO --> ID " + o.getID() + "\nListalinee: \n" + o.getLinea());
	        alert.showAndWait();
	        o.print();
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Ordine completato");
	        alert.setContentText("Seleziona i pezzi prima di effettuare ordine");
	        alert.showAndWait();
    	}
    	
    }
    
    public static int display(String title, String message, int quantitaSuggerita){
	      Stage window = new Stage();
	      
	      window.initModality(Modality.APPLICATION_MODAL);
	      window.setTitle(title);
	      window.setMinWidth(400);

	      Label label = new Label();
	      label.setText(message);

	      TextField edit= new TextField();
	      edit.setId("edit");
	      
	      Button yButton = new Button("OK");
	      yButton.setId("OK");
	      yButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String v = edit.getText();
				try {
					if(Integer.parseInt(edit.getText())>0) {
						valore= Integer.parseInt(v)+quantitaSuggerita;
					}
					else {
						valore=quantitaSuggerita;
						Alert alert = new Alert(AlertType.WARNING);
	        	        alert.setTitle("Valore non valido");
	        	        alert.setContentText("Hai inserito un valore non valido, la quantita da ordinare è stata mantenuta a quella minima");
	        	        alert.showAndWait();
					}
				}
				catch(Exception e) {
					valore=quantitaSuggerita;
					Alert alert = new Alert(AlertType.WARNING);
        	        alert.setTitle("Valore non valido");
        	        alert.setContentText("Hai inserito un valore non valido, la quantita da ordinare è stata mantenuta a quella minima");
        	        alert.showAndWait();
				}
				window.close();
			}
		});	      

	      VBox layout = new VBox(10);
	      layout.getChildren().addAll(label, edit, yButton);
	      layout.setAlignment(Pos.CENTER);
	      Scene scene = new Scene(layout);
	      window.setScene(scene);
	      window.showAndWait();

	      return valore;
	      
	  }
    
    ObservableList<LineaOrdine> linee;
    private ArrayList<LineaOrdine> listaLinee= new ArrayList<>();
    ObservableList<Pezzo> d;
    private Model model;
    private ArrayList<Pezzo> lista = new ArrayList<>();
    static int valore = 0;

}
