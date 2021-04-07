package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox checkStudente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    @FXML
    void handleCheck(ActionEvent event) {
    	String matr = txtMatricola.getText();
    	Integer m;
    	try {
    		m = Integer.parseInt(matr);
    	} catch(NumberFormatException nfe) {
    		txtResult.setText("Inserire una matricola fatta solo di numeri");
    		return;
    	}
    	Studente s = model.getStudentebyMatr(m);
    	if(s==null) {
    		txtResult.setText("La matricola inserita non appartiene a nessuno studente");
    		return;
    	}
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    }

    @FXML
    void cercaCorsi(ActionEvent event) {
    	txtResult.clear();
    	String matr = txtMatricola.getText();
    	Integer m;
    	try {
    		m = Integer.parseInt(matr);
    	} catch(NumberFormatException nfe) {
    		txtResult.setText("Inserire una matricola fatta solo di numeri");
    		return;
    	}
    	Studente s = model.getStudentebyMatr(m);
    	if(s==null) {
    		txtResult.setText("La matricola inserita non appartiene a nessuno studente");
    		return;
    	}
    	List<Corso> lista = model.getCorsiPerStudente(m);
    	
    	//se non seleziono corso
    	if(comboCorsi.getValue() == null || comboCorsi.getValue().getNome().equals("")) {
        	for(Corso c : lista) {
        		txtResult.appendText(c.toString2()+"\n");
        	}
    	}
    	//se seleziono corso
    	else {
    		Corso c = comboCorsi.getValue();
    		if(lista.contains(c)) {
    			txtResult.setText("Lo studente è iscritto a questo corso");
        		return;
    		}
    		else {
    			txtResult.setText("Lo studente NON è iscritto a questo corso");
        		return;
    		}
    	}
    }

    @FXML
    void cercaIscrittiCorso(ActionEvent event) {
    	txtResult.clear();
    	Corso c = comboCorsi.getValue();
    	if(c==null || c.getNome().equals("")) {
    		txtResult.setText("Devi selezionare un corso");
    		return;
    	}
    	List<Studente> lista = model.getStudentiIscrittiCorso(c.getCodins());
    	for(Studente s : lista) {
    		txtResult.appendText(s.toString()+"\n");
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	txtResult.clear();
    	Corso c = comboCorsi.getValue();
    	if(c == null ||  c.getNome().equals("")) {
    		txtResult.setText("Devi selezionare un corso");
    		return;
    	}
    	String matr = txtMatricola.getText();
    	Integer m;
    	try {
    		m = Integer.parseInt(matr);
    	} catch(NumberFormatException nfe) {
    		txtResult.setText("Inserire una matricola fatta solo di numeri");
    		return;
    	}
    	Studente s = model.getStudentebyMatr(m);
    	if(s==null) {
    		txtResult.setText("La matricola inserita non appartiene a nessuno studente");
    		return;
    	}
    	List<Corso> lista = model.getCorsiPerStudente(m);
    	if(lista.contains(c)) {
    		txtResult.setText("Lo studente risulta gia iscritto a questo corso");
    		return;
    	}
    	else {
    		if(model.inscriviStudenteACorso(m, c.getCodins())) {
    			txtResult.setText("Iscrizione avvenuta con successo");
    		}
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtResult.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMatricola.clear();
    	checkStudente.setSelected(false);
    }


    @FXML
    void initialize() {
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkStudente != null : "fx:id=\"checkStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	comboCorsi.getItems().add(new Corso(null, null, "", null));
    	comboCorsi.getItems().addAll(model.getTuttiICorsi());
    }
    
}
