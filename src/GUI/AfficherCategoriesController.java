/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categories;
import entities.Reclamation;
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
import services.CategoriesService;
import services.reclamationService;

/**
 * FXML Controller class
 *
 * @author Mehdi AZZAZ
 */
public class AfficherCategoriesController extends AdminDashboardController {

    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
    @FXML
    private TableView<Categories> tableact;
    @FXML
    private TableColumn<Categories, Integer> id;
    @FXML
    private TableColumn<Categories, String> Nom;
   
    @FXML
    private TableColumn<Categories, String> type;
    @FXML
    private TableColumn<Categories, String> description;
    
     @FXML
    private TextField search;
    
    CategoriesService servact = new CategoriesService();
    
    ObservableList<Categories>  ActList = FXCollections.observableArrayList();

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
           
            
           ActList.addAll(servact.afficherCategories());
            
            
         tableact.setItems(ActList);
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        
    }
     private void loadDate() {
        
        
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        refreshTable();
   
     }

    @FXML
    private void goAccueil(ActionEvent event) {
    }

    @FXML
    private void goUser(ActionEvent event) {
    }

    @FXML
    private void goActivite(ActionEvent event) {
    }

    @FXML
    private void goExercice(ActionEvent event) {
    }

    @FXML
    private void goAbonnement(ActionEvent event) {
    }

    @FXML
    private void goOffer(ActionEvent event) {
    }

    @FXML
    private void goProduit(ActionEvent event) {
    }

    @FXML
    private void goCAtmehdi(ActionEvent event) {
    }

    @FXML
    private void goReclamation(ActionEvent event) {
    }

    @FXML
    private void goTypeReclamation(ActionEvent event) {
    }

    @FXML
    private void goEntraineur(ActionEvent event) {
    }

    @FXML
    private void goCatAymen(ActionEvent event) {
    }

    @FXML
    private void goAddReclamation(ActionEvent event) {
          try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AjouterCategories.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }

    @FXML
    private void goCategories(ActionEvent event) {
          try{
                 Parent root  = FXMLLoader.load(getClass().getResource("/gui/AjouterCategories.fxml"));
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
    }
    
    
}
