package it.polito.tdp.dizionariograph;

import it.polito.tdp.dizionariograph.exceptions.EmptyFieldException;
import it.polito.tdp.dizionariograph.exceptions.NoGraphException;
import it.polito.tdp.dizionariograph.model.Model;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumeroLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnGradoMax;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	
    	String numLettereStr = this.txtNumeroLettere.getText();
    	
    	try {
	    	if(numLettereStr == null || numLettereStr.isEmpty())
	    		throw new EmptyFieldException();
	    	
	    	int numeroLettere = Integer.parseInt(numLettereStr);
	    	
	    	System.out.println(numLettereStr + " "+numeroLettere);
	    	this.model.createGraph(numeroLettere);
	    	
	    	this.txtResult.appendText("Grafo creato.\n");
	    	
    	}catch(EmptyFieldException efe) {
    		efe.printStackTrace();
    		this.txtResult.appendText("Campo numero lettere vuoto. Inserire un intero.\n");
    		return;
    	}catch(NumberFormatException nfe) {
    		nfe.printStackTrace();
    		this.txtResult.appendText("Valore inserito non valido. Inserire caratteri numerici interi.\n");
    		return;
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtResult.clear();
    	this.model.setGrafo(null);
    }

    @FXML
    void doTovaVicini(ActionEvent event) {
    	String numLettereStr = this.txtNumeroLettere.getText();
    	String parola = this.txtParola.getText();
    	
    	try {
    		if(model.getGrafo() ==null){
    			throw new NoGraphException();
    		}
    		if((model.getGrafo().vertexSet().isEmpty() && model.getGrafo().edgeSet().isEmpty())) {
    			this.txtResult.appendText("Il grafo è vuoto.\n");
    			return;
    		}
    		
    		if(numLettereStr == null || numLettereStr.isEmpty())
        		throw new EmptyFieldException();
    		
    		if(parola == null || parola.isEmpty())
    		throw new EmptyFieldException();
    	
    		int numeroLettere = Integer.parseInt(numLettereStr);
	    	
    		if(parola.length() != numeroLettere)
	    		throw new InvalidParameterException();
	    	
	    	List<String> vicini = this.model.displayNeighbours(parola);
	    	this.txtResult.appendText(String.format("Vicini di %s:.\n", parola));
	    	this.txtResult.appendText(vicini.toString()+"\n");
    	
    	}catch(EmptyFieldException efe) {
    		efe.printStackTrace();
    		this.txtResult.appendText("Campo parola vuoto. Inserire una parola.\n");
    		return;
    	}catch(NumberFormatException nfe) {
    		nfe.printStackTrace();
    		this.txtResult.appendText("Valore inserito non valido. Inserire caratteri numerici interi.\n");
    		return;
    	}catch(InvalidParameterException npe) {
    		npe.printStackTrace();
    		this.txtResult.appendText("Inserire una parola di lunghezza uguale al campo lunghezza parole.\n");
    		return;
    	}catch(NoGraphException nge) {
    		nge.printStackTrace();
    		this.txtResult.appendText("Nessun grafo trovato. Creare un grafo.\n");
    		return;
    	}
    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	
    	try {
	    	if(model.getGrafo() ==null ){
				throw new NoGraphException();
			}
	    	
	    	if((model.getGrafo().vertexSet().isEmpty() && model.getGrafo().edgeSet().isEmpty())) {
    			this.txtResult.appendText("Il grafo è vuoto.\n");
    			return;
    		}
	    	
	    	int deg = this.model.findMaxDegree();
    		this.txtResult.appendText("Vertex with max degree: '"+model.getMaxDegreeVertex()+ "' with a degree of: "+deg );
    		
    	}catch(NoGraphException nge) {
    		nge.printStackTrace();
    		this.txtResult.appendText("Nessun grafo trovato. Creare un grafo.\n");
    		return;
    	}
    }

    @FXML
    void initialize() {
        assert txtNumeroLettere != null : "fx:id=\"txtNumeroLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGradoMax != null : "fx:id=\"btnGradoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
	
	public void setModel(Model model) {
		this.model = model;
	}
	
}
