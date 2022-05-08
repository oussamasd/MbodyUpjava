/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.TypeReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.TypeReclamationService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherTypeRecController extends AdminDashboardController{

    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private TextField search;
    @FXML
    private TableView<TypeReclamation> tableact;
    @FXML
    private TableColumn<TypeReclamation, String> Nom;
    TypeReclamationService servact = new TypeReclamationService();
      ObservableList<TypeReclamation>  ActList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDate();
    }    

      
    private void refreshTable() {
        try {
           
            
           ActList.addAll(servact.afficherType());
            
            
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
    
     private void loadDate() {
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        refreshTable();
   
     }
      @FXML
    private void goAddTypeReclamation(ActionEvent event) {
           // FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficheActback.fxml"));
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AjouterTypeRec.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }
        
  
    
}
