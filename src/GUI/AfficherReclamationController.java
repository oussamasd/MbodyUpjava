/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.reclamationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherReclamationController extends AdminDashboardController {

    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private TableView<Reclamation> tableact;
    @FXML
    private TableColumn<Reclamation, String> Nom;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TextField search;
    reclamationService servact = new reclamationService();
    
    ObservableList<Reclamation>  ActList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         System.out.println("hihi");
        loadDate();
    }    
    
    
    
    private void refreshTable() {
        try {
           
            
           ActList.addAll(servact.afficherpersonne());
            
            
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
     private void loadDate() {
        
        
        
        
       
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        refreshTable();
   
     }

 

      @FXML
    private void goAddReclamation(ActionEvent event) {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficheActback.fxml"));
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AjouterReclamation.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }
    
    
    
    
   
}
