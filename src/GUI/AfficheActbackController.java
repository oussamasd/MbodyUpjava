/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ActivityService;

/**
 * FXML Controller class
 *
 * @author oussa
 */
public class AfficheActbackController extends AdminDashboardController{
    
     @FXML
    private TableView<Activitie> tableact;
    @FXML
    private TableColumn<Activitie, Integer> idact;
    @FXML
    private TableColumn<Activitie, String> nomact;
    @FXML
    private TableColumn<Activitie, String> dateact;
    @FXML
    private TableColumn<Activitie, String> tempact;
    @FXML
    private TableColumn<Activitie, String> descact;
    @FXML
    private TableColumn<Activitie, Integer> nbrplace;
    
    ActivityService servact = new ActivityService();
    
    ObservableList<Activitie>  ActList = FXCollections.observableArrayList();
   
    
      /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("hihi");
        loadDate();
    }
    
     @FXML
    private void refreshTable() {
        try {
           
            
           ActList.addAll(servact.afficheractivities());
            
            
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
     private void loadDate() {
        
        
        
        
        idact.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomact.setCellValueFactory(new PropertyValueFactory<>("nom_Act"));
        dateact.setCellValueFactory(new PropertyValueFactory<>("date_Act"));
        tempact.setCellValueFactory(new PropertyValueFactory<>("temp_act"));
        descact.setCellValueFactory(new PropertyValueFactory<>("description_Act"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        refreshTable();
   
     }
     
       @FXML
    private void goAddActivite(ActionEvent event) {
        // FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficheActback.fxml"));
        try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AjouterAct.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
         
         
        
      
     
    }
}
