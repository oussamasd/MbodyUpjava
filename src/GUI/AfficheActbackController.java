/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Activitie;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    @FXML
    private GridPane ll;
    @FXML
    private TextField search;
    @FXML
    private Pane addbutton;
    @FXML
    private Label titrepage;
   
    
      /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       // loadDate();
        searchact();

    }
    
    private void refreshTable(List<Activitie> la) {
   
           //ActList.addAll(servact.afficheractivities());
           tableact.getItems().clear();
            ActList.addAll(la);
            
         tableact.setItems(ActList);
            
 
    }
     private void loadDate(List<Activitie> la) {
        
        
        
        
        idact.setCellValueFactory(new PropertyValueFactory<>("id"));    
        nomact.setCellValueFactory(new PropertyValueFactory<>("nom_Act"));
        dateact.setCellValueFactory(new PropertyValueFactory<>("date_Act"));
        tempact.setCellValueFactory(new PropertyValueFactory<>("temp_act"));
        descact.setCellValueFactory(new PropertyValueFactory<>("description_Act"));
        nbrplace.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        if(la!=null){
        
        refreshTable(la);
        }
        
   
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
    @FXML
    private void But_ModifAbon(ActionEvent event) {
        
        
        Activitie a = tableact.getSelectionModel().getSelectedItem();
       
        System.out.println(a);
           try{
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierAct.fxml"));
                 Parent root = loader.load();
                 ModifierActController mdc = loader.getController();
                 mdc.reciveAct(a);
                 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                 scene = new Scene(root);
                 stage.setScene(scene);
                 stage.show();

        }catch(IOException ex){
            System.out.println(ex);
        
        }
         
        
        /*
        
        if( Validchamp(tnom) &&  Validchamp(Acat) &&  Validchamp(Adesc)){  
        Abonnement a = tababon.getSelectionModel().getSelectedItem();

       a.setNom(tnom.getText());
        a.setCategorie(Acat.getText());
        a.setDescription(Adesc.getText());
        a.setPrix(Float.parseFloat(Aprix.getText()));

        // M.setImage(imagecomp);
        AbonnementService sp = new AbonnementService();

        try {

            sp.ModifierAbonnementAb(a);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
            alert.setTitle("Modifier !");
            alert.setContentText("Modifier avec succés");
          

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
            alert.setTitle("fail !");
            alert.setContentText("failed ");

        }
     
   
     
        } 
        */
    }

    

    @FXML
    private void searchh(InputMethodEvent event) {
        System.out.println(search.getText());
    }
    
    public  void searchact(){
         try {
             List<Activitie> allact = servact.afficheractivities();
         
    
     search.textProperty().addListener((observable, oldValue, newValue) -> {
   // System.out.println(newValue.toString());
   if( newValue.isEmpty()){
       //loadDate();
        
             loadDate(allact);
        
       
   }else{
   
       System.out.println(newValue);
       List<Activitie> a = new ArrayList();
       a = allact.stream().filter(item-> {
           
           
            int index = item.getNom_Act().toUpperCase ().indexOf(newValue.toUpperCase ());
            int index2 = item.getDescription_Act().toUpperCase ().indexOf(newValue.toUpperCase ());
            int index3 = item.getDate_Act().toUpperCase ().indexOf(newValue.toUpperCase ());
             int index4 = Integer.toString(item.getId()).toUpperCase ().indexOf(newValue.toUpperCase ());

               
              if((index ==-1)&&(index2==-1)&&(index3==-1)&&(index4==-1) ) 
              {
              return false;
              }else{
              
                  return true ;
              }
               
               
               
                       }
       
       ).collect(Collectors.toList());
       
      
       loadDate(a);
   
   }
   
});
         
             loadDate(allact);
        
      } catch (SQLException ex) {
             System.out.println(ex);
         }  
    
    }
  
   

  
   
    
    
    
}
