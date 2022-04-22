/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Abonnement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.AbonnementService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterAbonneController extends AdminDashboardController {
    
     @FXML
    private TableView<Abonnement> tababon;
    @FXML
    private TableColumn<Abonnement, Integer> id;
    @FXML
    private TableColumn<Abonnement, String> nom;
    @FXML
    private TableColumn<Abonnement, Float> prix;
    @FXML
    private TableColumn<Abonnement, String> categories;
    @FXML
    private TableColumn<Abonnement, String> description;
     @FXML
    private Label Anom;
    @FXML
    private TextField tnom;
    @FXML
    private TextField Acat;
    @FXML
    private TextField Ades;
    @FXML
    private TextField Aprix;

    AbonnementService servact = new AbonnementService();
    
    ObservableList<Abonnement>  ActList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
     @FXML
private void AjouterAbonn(ActionEvent event) {
        Abonnement a = new Abonnement();
        a.setNom(nom.getText());
        a.setCategorie(categories.getText());
        a.setDescription(description.getText());
        a.setPrix(Float.parseFloat(prix.getText()));
        AbonnementService sp = new AbonnementService();
        try {
            sp.ajouterAbonnement(a);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succées");
            alert.setContentText("Abonnement ajoutée");
            alert.show();
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
