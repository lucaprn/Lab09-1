/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.dao.MetroDAO;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
	
	Model model;
	MetroDAO dao;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBoxPartenza"
    private ComboBox<Fermata> comboBoxPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="comboBoxArrivo"
    private ComboBox<Fermata> comboBoxArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcola"
    private Button btnCalcola; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void DoCalcolaPercorso(ActionEvent event) {
    	model.creaGrafo();
    	Fermata f1 = new Fermata(this.comboBoxPartenza.getValue());
    	Fermata f2 = new Fermata(this.comboBoxArrivo.getValue());

    	model.getMinimunPath(f1, f2);
    	String tmp = this.model.toStringCalcolaPercorso();
    	this.txtRisultato.setText(tmp);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboBoxPartenza != null : "fx:id=\"comboBoxPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert comboBoxArrivo != null : "fx:id=\"comboBoxArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }

	public void setModel(Model model) {
		dao=new MetroDAO();
		this.model = model;
		this.comboBoxPartenza.getItems().addAll(this.dao.getAllFermate());
		this.comboBoxArrivo.getItems().addAll(this.dao.getAllFermate());
	
	}
    
    
}
